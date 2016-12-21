package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import pt.gois.dtServices.business.HistoricoSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.Historico;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.TipoServicoSolicitante;
import pt.gois.dtServices.entity.TiposDeEstado;
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
	private pt.gois.dtServices.business.TipoDeEstadoSBLocal sbTiposDeEstado;
	
	@EJB
	private HistoricoSBLocal sbHistorico;

	
	Servico servico;
	Integer idProcessoInterno;
	
	EstadosServico estadoServico;
	
	boolean existeTipoServicoSolicitante=true;
	
	public List<ProcessoInterno> getProcessosInterno() {
		return sbProcessoInterno.findAll();
	}
	
	public List<TiposDeEstado> getNovosEstados() {
		ArrayList<TiposDeEstado> novosEstados = new ArrayList<TiposDeEstado>();
//		if(getServico().getTiposDeEstado().getId() != null) {
//			novosEstados.add(sbTiposDeEstado.findById(getServico().getTiposDeEstado().getId()));
//		}
//		novosEstados.addAll(sbTiposDeEstado.findNextStates(TipoDeEstadoSBLocal.SERVICOS, getServico().getTiposDeEstado().getId()));
		return novosEstados;
	}
	
	public List<TipoServicoSolicitante> getTiposServicoSolicitante() {
		
		ProcessoInterno processoInterno = sbProcessoInterno.findById(idProcessoInterno);
		
		Integer idSolicitante = processoInterno.getProcessoExterno().getSolicitante().getId();
		
		SearchPageCtrl<TipoServicoSolicitante> searchPageCtrl = new SearchPageCtrl<TipoServicoSolicitante>();
		searchPageCtrl.getFilters().put("solicitante.id", idSolicitante);
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
		// servico.setProcessoInterno(sbProcessoInterno.findById(idProcessoInterno));
		
		verificaMudancaDeEstado(servico);
		
		sb.salvar( servico, idProcessoInterno );
		
		return "/pages/processoInterno/processoInternoEdit?faces-redirect=true&id=" + idProcessoInterno;
	}
	
	private void verificaMudancaDeEstado(Servico servico) {
		
		if(servico.getId() == null) {
			TiposDeEstado tipo = new TiposDeEstado();
			tipo.setId(TipoDeEstadoSBLocal.PI_CRIADO);
			estadoServico.setTiposDeEstado(tipo);
			estadoServico.setServico(servico);
			servico.getEstadosServicos().add(estadoServico);
		}
	}

	
	public void delete( Servico Servico ){
		sb.delete(Servico);
	}
	
	public Servico getServico() {
		if( servico == null ){
			Integer id = getId();
			if( id != null ){
				servico = sb.findById( getId() );
				estadoServico = servico.retornaEstadoAtual();
			}else{
				servico = new Servico();
				servico.setProcessoInterno(new ProcessoInterno());
				servico.setTipoServicoSolicitante(new TipoServicoSolicitante());
				servico.setEstadosServicos(new ArrayList<EstadosServico>());
				estadoServico = new EstadosServico();
				
			}
		}
		return servico;
	}
	
	public EstadosServico getEstadoServico() {
		return estadoServico;
	}

	public void setEstadoServico(EstadosServico estadoServico) {
		this.estadoServico = estadoServico;
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
	
	public List<Historico> getHistorico() {
		
		return sbHistorico.findByObjectAndType(getId(), TipoDeEstadoSBLocal.PROCESSO_INTERNO);
	}
	public String getNomeEstadoAtual() {
		servico = getServico();
		if(servico.getId() != null) {
			return sb.retornaNomeEstadoAtual(servico.getId());
		} else {
			return "";
		}
	}

}
