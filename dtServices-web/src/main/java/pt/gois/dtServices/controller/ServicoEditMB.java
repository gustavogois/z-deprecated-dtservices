package pt.gois.dtServices.controller;

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
import javax.faces.validator.ValidatorException;

import pt.gois.dtServices.business.HistoricoSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.entity.Historico;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.TipoDeEstado;
import pt.gois.dtServices.entity.TipoServicoSolicitante;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ServicoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ServicoSBLocal sb;
	
	@EJB
	private pt.gois.dtServices.business.ProcessoInternoSBLocal sbProcessoInterno;
	
	@EJB
	private pt.gois.dtServices.business.TipoServicoSolicitanteSBLocal sbTipoServicoSolicitante;
	
	@EJB
	private pt.gois.dtServices.business.TipoDeEstadoSBLocal sbTipoDeEstado;
	
	@EJB
	HistoricoSBLocal sbHistorico;
	
	Servico servico;
	Integer idProcessoInterno;
	Integer idEstadoAtual;
	
	boolean existeTipoServicoSolicitante=true;
	

	public List<ProcessoInterno> getProcessosInterno() {
		return sbProcessoInterno.findAll();
	}
	
	public List<TipoDeEstado> getNovosEstados() {
		ArrayList<TipoDeEstado> novosEstados = new ArrayList<TipoDeEstado>();
		if(getServico().getTipoDeEstado().getId() != null) {
			novosEstados.add(sbTipoDeEstado.findById(getServico().getTipoDeEstado().getId()));
		}
		novosEstados.addAll(sbTipoDeEstado.findNextStates(TipoDeEstadoSBLocal.SERVICOS, getServico().getTipoDeEstado().getId()));
		return novosEstados;
	}
	
	public List<TipoServicoSolicitante> getTiposServicoSolicitante() {
		
		ProcessoInterno processoInterno = sbProcessoInterno.findById(idProcessoInterno);
		
		Integer idSolicitante = processoInterno.getProcessoExterno().getSolicitante().getId();
		
		SearchPageCtrl<TipoServicoSolicitante> searchPageCtrl = new SearchPageCtrl<TipoServicoSolicitante>();
		searchPageCtrl.getFilters().put("obj.solicitante.id", idSolicitante);
		List<TipoServicoSolicitante> tss = sbTipoServicoSolicitante.find(searchPageCtrl).getRows();
		
		existeTipoServicoSolicitante = true;
		if(tss == null || tss.size() == 0){
			existeTipoServicoSolicitante = false;
		}
		
		return tss;
		
	}
	
	public void onServicoChange() {
		TipoServicoSolicitante tss = sbTipoServicoSolicitante.findById(servico.getTipoServicoSolicitante().getId());
		servico.setValor(tss.getValor());
	}
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<Servico> searchPageCtrl = new SearchPageCtrl<Servico>();
		searchPageCtrl.getFilters().put("nome", value);
		List<Servico> servicos = sb.find(searchPageCtrl).getRows();
		if (servicos != null && servicos.size() > 0 ) {
			if( servicos.size() == 1 && ( servicos.get(0).getId() == servico.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public void validateValor(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public String create() {
		servico = new Servico();
		//servico.setSolicitante(new Solicitante());
		sb.create(servico);
		return "ServicoEdit";
	}
	
	public String save(){
		Servico servico = getServico();
		servico.setProcessoInterno(sbProcessoInterno.findById(idProcessoInterno));
		if( servico.getId() != null ){
			sb.save( servico );
		}else{
			sb.create( servico );
		}
		
		if(!idEstadoAtual.equals(servico.getTipoDeEstado().getId())) {
			servico = sb.findById(servico.getId());
			Historico historico = new Historico();
			historico.setIdObjeto(servico.getId());
			historico.setData(new Date());
			historico.setDescricao("Estado do serviço " + servico.getId() + " alterado para: " + servico.getTipoDeEstado().getNome());
			sbHistorico.create(historico);
		}
		
		return "/pages/processoInterno/processoInternoEdit?faces-redirect=true&id=" + idProcessoInterno;
	}
	
	public void delete( Servico Servico ){
		sb.delete(Servico);
	}
	
	public Servico getServico() {
		if( servico == null ){
			Integer id = getId();
			if( id != null ){
				servico = sb.findById( getId() );
				idEstadoAtual = servico.getTipoDeEstado().getId();
			}else{
				servico = new Servico();
				servico.setProcessoInterno(new ProcessoInterno());
				servico.setTipoServicoSolicitante(new TipoServicoSolicitante());
				servico.setTipoDeEstado(sbTipoDeEstado.findById(TipoDeEstadoSBLocal.SRV_CRIADO));
				idEstadoAtual = TipoDeEstadoSBLocal.SRV_CRIADO;

			}
		}
		return servico;
	}
	
	public void setServico(Servico Servico) {
		this.servico = Servico;
	}

	public pt.gois.dtServices.business.ServicoSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.ServicoSBLocal sb) {
		this.sb = sb;
	}
	public Integer getIdProcessoInterno() {
		return idProcessoInterno;
	}

	public void setIdProcessoInterno(Integer idProcessoInterno) {
		this.idProcessoInterno = idProcessoInterno;
	}

	public boolean isNotExisteTipoServicoSolicitante() {
		return ! existeTipoServicoSolicitante;
	}
	
	public boolean isExisteTipoServicoSolicitante() {
		return existeTipoServicoSolicitante;
	}

	public void setExisteTipoServicoSolicitante(boolean existeTipoServicoSolicitante) {
		this.existeTipoServicoSolicitante = existeTipoServicoSolicitante;
	}
}
