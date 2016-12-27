package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;
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
	private pt.gois.dtServices.business.TipoDeEstadoSBLocal sbTiposDeEstado;
	
	@EJB
	private HistoricoSBLocal sbHistorico;

	
	Servico servico;
	Integer idProcessoInterno;
	
	EstadosServico estadoServico;
	String nomeEstadoAtual;
	String acao;
	Date data;
	
	boolean existeTipoServicoSolicitante=true;
	
	public List<ProcessoInterno> getProcessosInterno() {
		return sbProcessoInterno.findAll();
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
		servico = getServico();
		
		if(isStarting()) {
			sb.salvar(servico, TipoDeEstadoSBLocal.SRV_EM_EXECUCAO, data);
		} else if(isSuspending()) {
			sb.salvar(servico, TipoDeEstadoSBLocal.SRV_SUSPENSO, data);
		} else if(isFinalizing()) {
			sb.salvar(servico, TipoDeEstadoSBLocal.SRV_FINALIZADO, data);
		} else if(!isEditing()){
			sb.salvar(servico, TipoDeEstadoSBLocal.SRV_CRIADO, data);
		} else {
			sb.save(servico);
		}
		
		return "/pages/processoInterno/processoInternoEdit?faces-redirect=true&id=" + idProcessoInterno;
	}
	
	public boolean isCreating() {
		return (acao == null || acao == "");
	} 

	public boolean isStarting() {
		return (acao != null && acao.equals("START"));
	} 
	
	public boolean isSuspending() {
		return (acao != null && acao.equals("SUSPEND"));
	}
	
	public boolean isFinalizing() {
		return (acao != null && acao.equals("END"));
	}
	
	public boolean isEditing() {
		return (acao != null && acao.equals("EDIT"));
	}
	
	public void delete( Servico Servico ){
		sb.delete(Servico);
	}
	
	public Servico getServico() {
		if( servico == null ){
			Integer id = getId();
			if( id != null ){
				servico = sb.findById( getId() );
			}else{
				servico = new Servico();
				servico.setProcessoInterno(sbProcessoInterno.findById( idProcessoInterno ) );
				servico.setTipoServicoSolicitante(new TipoServicoSolicitante());
				servico.setEstadosServicos(new ArrayList<EstadosServico>());
				data = new Date();
			}
		}
		return servico;
	}
	
	public EstadosServico getEstadoServico() {
		if(estadoServico == null) {
			estadoServico = new EstadosServico();
		}
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
	
	public String getNomeEstadoAtual(Servico servico) {
		return sb.retornaNomeEstadoAtual(servico);
	}

	public String getNomeEstadoAtual() {
		if(nomeEstadoAtual == null || nomeEstadoAtual == "") {
			servico = getServico();
			if(servico.getId() != null) {
				nomeEstadoAtual = sb.retornaNomeEstadoAtual(servico);
			} else {
				nomeEstadoAtual = "Em criação";
			}
		}
		return nomeEstadoAtual;
	}
	
	public boolean canStart(Servico servico) {
		return sb.canStart(servico);
	}
	
	public boolean canSuspend(Servico servico) {
		return sb.canSuspend(servico);
	}
	
	public boolean canFinalize(Servico servico) {
		return sb.canFinalize(servico);
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

}
