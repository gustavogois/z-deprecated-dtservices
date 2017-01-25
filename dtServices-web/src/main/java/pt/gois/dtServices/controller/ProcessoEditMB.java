package pt.gois.dtServices.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import pt.gois.dtServices.business.EnderecoSBLocal;
import pt.gois.dtServices.business.HistoricoSBLocal;
import pt.gois.dtServices.business.ServicoSBLocal;
import pt.gois.dtServices.business.TipoEstadoSBLocal;
import pt.gois.dtServices.business.TipoServicoSB;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.EnderecoVW;
import pt.gois.dtServices.entity.EstadoProcesso;
import pt.gois.dtServices.entity.EstadoServico;
import pt.gois.dtServices.entity.Historico;
import pt.gois.dtServices.entity.Imagem;
import pt.gois.dtServices.entity.Imovel;
import pt.gois.dtServices.entity.Processo;
import pt.gois.dtServices.entity.ProcessoView;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoEstado;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.SolicitanteSBLocal sbSolicitante;

	@EJB
	private pt.gois.dtServices.business.ProcessoSBLocal sb;

	@EJB
	private pt.gois.dtServices.business.ProcessoViewSBLocal sbProcView;

	@EJB
	private TipoServicoSBLocal sbTipoServico;

	@EJB
	private ServicoSBLocal sbServico;

	@EJB
	private EnderecoSBLocal sbEndereco;
	
	@EJB
	private TipoEstadoSBLocal sbTipoEstado;

	@EJB
	private HistoricoSBLocal sbHistorico;

	@EJB
	private pt.gois.dtServices.business.ImagemSBLocal sbImagem;

	@ManagedProperty(value = "#{userSessionMB}")
	private UserSessionMB userSessionMB;

	Processo processo;
	Servico servico;
	TipoServico tipoServico;

	Integer idprocesso;
	EstadoProcesso estadoProcesso;

	String nomeEstadoAtual;
	String acao;
	Date data;

	EnderecoVW endereco;

	MapModel geoModel;

	String centerGeoMap = "39.1708764,-8.629546,8";

	int zoomLevel = 13;

	String descricao;

	Imagem selectedImage;

	public void handleFileUpload(FileUploadEvent event) {
		try {
			Imagem imagem = new Imagem();

			UploadedFile file = event.getFile();
			imagem.setDescricao(file.getFileName());
			imagem.getImovels().add(processo.getImovel());
			imagem.setImagem(file.getContents());
			imagem.setFilename(file.getFileName());
			//imagem.setSize(file.getSize());
			imagem.setMimeType(file.getContentType());
			sbImagem.create(imagem);

			addMessage(event.getFile().getFileName() + " is uploaded.");
		} catch (Exception e) {
			addError(e);
		}
	}

	public void onEnderecoSelect(SelectEvent event) {
		Imovel imovel = getProcesso().getImovel();
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

	public StreamedContent getImagem(Imagem imagem) {
		byte[] bin = sbImagem.getBin(imagem.getId());
		return new DefaultStreamedContent(new ByteArrayInputStream(bin), imagem.getMimeType(), imagem.getFilename());
	}

	@PostConstruct
	public void init() {
		geoModel = new DefaultMapModel();
	}

	public List<Imagem> getImages() {
		return sb.getImages(getProcesso().getId());
	}

	public void deleteImage(Imagem imagem) {
		sbImagem.delete(imagem);
	}

	public void updateImage(Imagem imagem) {
		sbImagem.save(imagem);
	}

	public void onPointSelect(PointSelectEvent event) {
		LatLng latlng = event.getLatLng();

		Imovel imovel = processo.getImovel();
		imovel.setLatitude(latlng.getLat());
		imovel.setLongitude(latlng.getLng());

		geoModel.getMarkers().clear();
		geoModel.addOverlay(new Marker(latlng));
		centerGeoMap = latlng.getLat() + "," + latlng.getLng();
	}

	public void onGeocode(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();

		if (results != null && !results.isEmpty()) {
			LatLng latlng = results.get(0).getLatLng();
			centerGeoMap = latlng.getLat() + "," + latlng.getLng();

			geoModel.getMarkers().clear();
			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				geoModel.addOverlay(new Marker(result.getLatLng(), result.getAddress()));
				centerGeoMap = latlng.getLat() + "," + latlng.getLng();
			}
			if (results.size() == 1) {
				processo.getImovel().setLatitude(latlng.getLat());
				processo.getImovel().setLongitude(latlng.getLng());
			}
		}
	}

	public void onMarkerSelect(OverlaySelectEvent event) {
		Marker marker = (Marker) event.getOverlay();

		processo.getImovel().setLatitude(marker.getLatlng().getLat());
		processo.getImovel().setLongitude(marker.getLatlng().getLng());
	}

	public void onStateChange(StateChangeEvent event) {
		setZoomLevel(event.getZoomLevel() + 1);
		LatLng center = event.getCenter();
		setCenterGeoMap(center.getLat() + "," + center.getLng());
	}

	public EnderecoVW getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoVW endereco) {
		this.endereco = endereco;
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

	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public List<Solicitante> getSolicitantes() {
		return sbSolicitante.findAll();
	}

	public boolean canEdit(ProcessoView processo) {
		return sb.canEdit(processo);
	}

	public boolean canFaturar(ProcessoView processo) {
		return sb.canFaturar(processo);
	}

	public boolean canPagar(ProcessoView processo) {
		return sb.canPagar(processo);
	}

	public String getNomeEstadoAtual() {
		if (nomeEstadoAtual == null || nomeEstadoAtual.equals("")) {
			Integer id = getProcesso().getId();
			if (id != null) {
				ProcessoView procView = sbProcView.findById(id);
				if(procView != null) {
					nomeEstadoAtual = procView.getNomeEstado();
				}
			}
		}
		return nomeEstadoAtual;
	}

	public List<TipoServico> getServicos() throws Exception {

		List<TipoServico> servicos = sbTipoServico.findAll();
		return servicos;
	}

	public PaginatedDataModel<Servico> getServicoByProcesso() throws Exception {
		processo = getProcesso();
		SearchPageCtrl<Servico> searchPageCtrl = new SearchPageCtrl<Servico>();
		searchPageCtrl.getFilters().put("obj.processo.id", processo.getId());

		return new PaginatedDataModel<Servico>(searchPageCtrl, sbServico);
	}

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm", FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<Processo> searchPageCtrl = new SearchPageCtrl<Processo>();
		searchPageCtrl.getFilters().put("nome", value);
		List<Processo> processos = sb.find(searchPageCtrl).getRows();
		if (processos != null && processos.size() > 0) {
			if (processos.size() == 1 && (processos.get(0).getId() == processo.getId())) {
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists", FacesMessage.SEVERITY_ERROR));
		}
	}

	public String save() {

		Processo processo = getProcesso();

		if (isFaturando()) {
			sb.salvar(processo, TipoEstadoSBLocal.PI_AGUARDANDO_PAGAMENTO, data, userSessionMB.getUser());
		} else if (isPagando()) {
			sb.salvar(processo, TipoEstadoSBLocal.PI_PAGO, data, userSessionMB.getUser());
		} else if (!isEditing()) {
			sb.salvar(processo, TipoEstadoSBLocal.PI_CRIADO, data, userSessionMB.getUser());
		} else {
			sb.save(getProcesso());
		}
		addMessage("Processo salvo");
		return "/pages/processo/processoEdit?faces-redirect=true&id=" + processo.getId();
	}

	public void delete(ProcessoView processo) {
		try {

			sb.delete(sb.findById(processo.getId()));
		} catch (EJBException e) {
			if (sb.isCauseException(TipoServicoSB.CONSTRAINT_VIOLATION_EXCEPTION, e)) {
				String mensagem = "Não é possível excluir este Processo Interno. Existem Serviços associados.";
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "System Error"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,
						TipoServicoSBLocal.MSG_FATAL_ERRO, "Erro desconhecido na exclusão do Solicitante"));
			}
		}
	}

	public Processo getProcesso() {
		if (processo == null) {
			Integer id = getId();
			if (id != null) {
				processo = sb.findById(getId());
			} else {
				processo = new Processo();

				servico = new Servico();
				servico.setEstadoServicos(new ArrayList<EstadoServico>());

				tipoServico = new TipoServico();

				processo.setEstadoProcessos(new ArrayList<EstadoProcesso>());
				data = new Date();

				processo.setSolicitante(new Solicitante());
				
				processo.setImovel(new Imovel());
			}
		}
		return processo;
	}

	public boolean isCreating() {
		return (acao == null || acao == "");
	}

	public boolean isEditing() {
		return (acao != null && acao.equals("EDIT"));
	}

	public boolean isFaturando() {
		return (acao != null && acao.equals("FATURAR"));
	}

	public boolean isPagando() {
		return (acao != null && acao.equals("PAGAR"));
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public pt.gois.dtServices.business.ProcessoSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.ProcessoSBLocal sb) {
		this.sb = sb;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public TipoServicoSBLocal getSbTipoServico() {
		return sbTipoServico;
	}

	public void setSbTipoServico(TipoServicoSBLocal sbTipoServico) {
		this.sbTipoServico = sbTipoServico;
	}

	public ServicoSBLocal getSbServico() {
		return sbServico;
	}

	public void setSbServico(ServicoSBLocal sbServico) {
		this.sbServico = sbServico;
	}

	public TipoEstadoSBLocal getSbTipoEstado() {
		return sbTipoEstado;
	}

	public void setSbTipoEstado(TipoEstadoSBLocal sbTipoEstado) {
		this.sbTipoEstado = sbTipoEstado;
	}

	public void setSb(pt.gois.dtServices.business.ProcessoSBLocal sb) {
		this.sb = sb;
	}

	public Integer getIdprocesso() {
		return idprocesso;
	}

	public void setIdprocesso(Integer idprocesso) {
		this.idprocesso = idprocesso;
	}

	public EstadoProcesso getEstadoProcesso() {
		if (estadoProcesso == null) {
			estadoProcesso = new EstadoProcesso();
		}
		return estadoProcesso;
	}

	public void setEstadoProcesso(EstadoProcesso estadoProcesso) {
		this.estadoProcesso = estadoProcesso;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setNomeEstadoAtual(String nomeEstadoAtual) {
		this.nomeEstadoAtual = nomeEstadoAtual;
	}

	public UserSessionMB getUserSessionMB() {
		return userSessionMB;
	}

	public void setUserSessionMB(UserSessionMB userSessionMB) {
		this.userSessionMB = userSessionMB;
	}
}
