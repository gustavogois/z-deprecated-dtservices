package pt.gois.dtServices.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import pt.gois.dtServices.business.ProcessoExternoSBLocal;
import pt.gois.dtServices.business.SolicitanteSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.entity.Concelho;
import pt.gois.dtServices.entity.Distrito;
import pt.gois.dtServices.entity.EnderecoVW;
import pt.gois.dtServices.entity.Historico;
import pt.gois.dtServices.entity.Imagem;
import pt.gois.dtServices.entity.Imovel;
import pt.gois.dtServices.entity.ProcessoExterno;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoDeEstado;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoExternoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessoExternoSBLocal sb;

	@EJB
	private SolicitanteSBLocal sbSolicitante;

	@EJB
	private pt.gois.dtServices.business.EnderecoSBLocal sbEndereco;

	@EJB
	private pt.gois.dtServices.business.ImagemSBLocal sbImagem;

	@EJB
	private pt.gois.dtServices.business.TipoDeEstadoSBLocal sbTipoDeEstado;
	
	@EJB
	private pt.gois.dtServices.business.HistoricoSBLocal sbHistorico;
	
	ProcessoExterno processoExterno;
	
	Integer idEstadoAtual;

	EnderecoVW endereco;

	MapModel geoModel;

	String centerGeoMap = "39.1708764,-8.629546,8";

	String descricao;
	
	Imagem selectedImage;

	public List<TipoDeEstado> getNovosEstados() {
		ArrayList<TipoDeEstado> novosEstados = new ArrayList<TipoDeEstado>();
		if(getProcessoExterno().getTipoDeEstado().getId() != null) {
			novosEstados.add(sbTipoDeEstado.findById(getProcessoExterno().getTipoDeEstado().getId()));
		}
		novosEstados.addAll(sbTipoDeEstado.findNextStates(TipoDeEstadoSBLocal.PROCESSO_EXTERNO, getProcessoExterno().getTipoDeEstado().getId()));
		return novosEstados;
	}
	
	public void handleFileUpload(FileUploadEvent event) {
		try{
			Imagem imagem = new Imagem();
	
			UploadedFile file = event.getFile();
			imagem.setDescricao(file.getFileName());
			imagem.getImovels().add(processoExterno.getImovel());
			imagem.setImagem(file.getContents());
			imagem.setFilename(file.getFileName());
			imagem.setSize(file.getSize());
			imagem.setMimeType(file.getContentType());
			sbImagem.create(imagem);
	
			addMessage(event.getFile().getFileName() + " is uploaded.");
		}catch(Exception e){
			addError(e);
		}
	}

	public StreamedContent getImagem(Imagem imagem) {
		byte[] bin = sbImagem.getBin(imagem.getId());
		return new DefaultStreamedContent(new ByteArrayInputStream(bin), imagem.getMimeType(), imagem.getFilename());
	}

	@PostConstruct
	public void init() {
		geoModel = new DefaultMapModel();
	}

	public List<Solicitante> getSolicitantes() {
		return sbSolicitante.findAll();
	}

	public void onEnderecoSelect(SelectEvent event) {
		Imovel imovel = getProcessoExterno().getImovel();
		EnderecoVW end = (EnderecoVW) event.getObject();
		end = sbEndereco.findById(end.getId());
		this.setEndereco(end);

		imovel.setRuaPorta(end.getRuaPorta());
		imovel.setComplemento(end.getComplemento());
		imovel.setLocalidade(end.getLocalidade());
		imovel.setDistrito(end.getDistrito());
		imovel.setConcelho(end.getConcelho());
		imovel.setCodigoPostal(end.getCodigoPostal());
	}

	public String create() {
		processoExterno = new ProcessoExterno();
		sb.create(processoExterno);
		return "processoExternoEdit";
	}

	public String save() {
		ProcessoExterno processoExterno = getProcessoExterno();

		boolean novo = false;
		if (processoExterno.getId() != null) {
			sb.save(processoExterno);
		} else {
			sb.create(processoExterno);
			novo = true;
		}
		
		if(!idEstadoAtual.equals(getProcessoExterno().getTipoDeEstado().getId())) {
			
			processoExterno = sb.findById(getProcessoExterno().getId());
			Historico historico = new Historico();
			historico.setIdObjeto(processoExterno.getId());
			historico.setTipoObjeto(TipoDeEstadoSBLocal.PROCESSO_EXTERNO);
			historico.setData(new Date());
			String descricao;
			if(novo) {
				descricao = "Processo Externo criado";
			} else {
				descricao = "Estado do Processo Externo alterado para: " + processoExterno.getTipoDeEstado().getNome();
			}
			historico.setDescricao(descricao);
			sbHistorico.create(historico);
		}

		
		return "processoExternoList";

	}
	
	public List<Historico> getHistorico() {
		
		return sbHistorico.findByObjectAndType(getId(), TipoDeEstadoSBLocal.PROCESSO_EXTERNO);
	}

	public ProcessoExterno getProcessoExterno() {
		if (processoExterno == null) {
			Integer id = getId();
			if (id != null) {
				processoExterno = sb.findById(getId());
				endereco = new EnderecoVW();
				Imovel imovel = processoExterno.getImovel();
				if (!StringUtils.isEmpty(imovel.getLatitude()) && !StringUtils.isEmpty(imovel.getLongitude())) {
					LatLng latlng = new LatLng(new Double(imovel.getLatitude()), new Double(imovel.getLongitude()));
					geoModel.addOverlay(new Marker(latlng, imovel.getCrp()));
				}
				endereco.setCodigoPostal(imovel.getCodigoPostal());
				
				idEstadoAtual = processoExterno.getTipoDeEstado().getId();

				
			} else {
				processoExterno = new ProcessoExterno();
				processoExterno.setSolicitante(new Solicitante());
				Imovel imovel = new Imovel();
				imovel.setConcelho(new Concelho());
				imovel.setDistrito(new Distrito());
				processoExterno.setImovel(imovel);
				imovel.setProcessoExterno(processoExterno);
				
				processoExterno.setTipoDeEstado(sbTipoDeEstado.findById(TipoDeEstadoSBLocal.PE_CRIADO));
				idEstadoAtual = TipoDeEstadoSBLocal.PE_CRIADO;
				
			}
		}
		return processoExterno;
	}

	public List<Imagem> getImages() {
		return sb.getImages(getProcessoExterno().getId());
	}

	public void delete(ProcessoExterno processoExterno) {
		sb.delete(processoExterno);
	}
	
	public void deleteImage(Imagem imagem){
		sbImagem.delete(imagem);
	}
	
	public void updateImage(Imagem imagem){
		sbImagem.save(imagem);
	}

	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();

		Imovel imovel = processoExterno.getImovel();
		imovel.setLatitude(latlng.getLat());
		imovel.setLongitude(latlng.getLng());
	}

	public void onGeocode(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();

		if (results != null && !results.isEmpty()) {
			LatLng latlng = results.get(0).getLatLng();
			centerGeoMap = latlng.getLat() + "," + latlng.getLng();

			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
			}
			if (results.size() == 1) {
				processoExterno.getImovel().setLatitude(latlng.getLat());
				processoExterno.getImovel().setLongitude(latlng.getLng());
			}
		}
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		Marker marker = (Marker) event.getOverlay();

		processoExterno.getImovel().setLatitude(marker.getLatlng().getLat());
		processoExterno.getImovel().setLongitude(marker.getLatlng().getLng());
	}

	public EnderecoVW getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoVW endereco) {
		this.endereco = endereco;
	}

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
	}

	public void validateEndereco(FacesContext context, UIComponent toValidate, Object value) throws Exception {

	}

	public void validateCrp(FacesContext context, UIComponent toValidate, Object value) throws Exception {

	}

	public void validateInquilino(FacesContext context, UIComponent toValidate, Object value) throws Exception {

	}

	public void setSb(ProcessoExternoSBLocal sb) {
		this.sb = sb;
	}

	public ProcessoExternoSBLocal getSb() {
		return this.sb;
	}

	public SolicitanteSBLocal getSbSolicitante() {
		return sbSolicitante;
	}

	public void setSbSolicitante(SolicitanteSBLocal sbSolicitante) {
		this.sbSolicitante = sbSolicitante;
	}

	public pt.gois.dtServices.business.EnderecoSBLocal getSbEndereco() {
		return sbEndereco;
	}

	public void setSbEndereco(pt.gois.dtServices.business.EnderecoSBLocal sbEndereco) {
		this.sbEndereco = sbEndereco;
	}

	public MapModel getGeoModel() {
		return geoModel;
	}

	public void setGeoModel(MapModel geoModel) {
		this.geoModel = geoModel;
	}

	public String getCenterGeoMap() {
		return centerGeoMap;
	}

	public void setCenterGeoMap(String centerGeoMap) {
		this.centerGeoMap = centerGeoMap;
	}

	public void setProcessoExterno(ProcessoExterno processoExterno) {
		this.processoExterno = processoExterno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public pt.gois.dtServices.business.ImagemSBLocal getSbImagem() {
		return sbImagem;
	}

	public void setSbImagem(pt.gois.dtServices.business.ImagemSBLocal sbImagem) {
		this.sbImagem = sbImagem;
	}

	public Imagem getSelectedImage() {
		return selectedImage;
	}

	public void setSelectedImage(Imagem selectedImage) {
		this.selectedImage = selectedImage;
	}
	
}
