package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pt.gois.dtServices.business.ServicoViewSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.ServicoView;
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
	private ServicoViewSBLocal sbServicoView;
	
	@EJB
	private pt.gois.dtServices.business.ProcessoInternoSBLocal sbProcessoInterno;
	@EJB
	private pt.gois.dtServices.business.TipoServicoSolicitanteSBLocal sbTipoServicoSolicitante;
	
	@ManagedProperty(value="#{userSessionMB}")
	private UserSessionMB userSessionMB;

	
	Servico servico;
	Integer idProcessoInterno;
	
	String nomeEstadoAtual;
	String observacaoEstado;

	String acao;
	Date datap;

	public List<TipoServicoSolicitante> getTiposServicoSolicitante() {
		
		ProcessoInterno processoInterno = sbProcessoInterno.findById(idProcessoInterno);
		
		Integer idSolicitante = processoInterno.getProcessoExterno().getSolicitante().getId();
		
		SearchPageCtrl<TipoServicoSolicitante> searchPageCtrl = new SearchPageCtrl<TipoServicoSolicitante>();
		searchPageCtrl.getFilters().put("solicitante.id", idSolicitante);
		List<TipoServicoSolicitante> tss = sbTipoServicoSolicitante.find(searchPageCtrl).getRows();
		
		return tss;
		
	}
	
	public void onServicoChange() {
		TipoServicoSolicitante tss = sbTipoServicoSolicitante.findById(servico.getTipoServicoSolicitante().getId());
		servico.setValor(tss.getValor());
	}
	
	private Calendar getDataCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(datap);
		return calendar;
	}
	
	private void adicionaNovoEstado(Servico servico) {
		
		EstadosServico novoEstado = new EstadosServico();
		TiposDeEstado tipo;
		
		if(isStarting()) {
			tipo = new TiposDeEstado(TipoDeEstadoSBLocal.SRV_EM_EXECUCAO);
		} else if(isSuspending()) {
			tipo = new TiposDeEstado(TipoDeEstadoSBLocal.SRV_SUSPENSO);
		} else if(isFinalizing()) {
			tipo = new TiposDeEstado(TipoDeEstadoSBLocal.SRV_FINALIZADO);
		} else if(!isEditing()){
			tipo = new TiposDeEstado(TipoDeEstadoSBLocal.SRV_CRIADO);
		} else {
			return;
		}
		
		novoEstado.setTiposDeEstado(tipo);
		novoEstado.setDtInicio(getDataCalendar());
		novoEstado.setUser(userSessionMB.getUser());
		novoEstado.setObservacoes(observacaoEstado);
		servico.getEstadosServicos().add(novoEstado);
	}
	
	public String save(){
		
		servico = getServico();
		
		adicionaNovoEstado(servico);
		
		sb.salvar(servico, userSessionMB.getUser());
		
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
	
	public void delete( ServicoView servicoView ){
		
		sb.delete(sb.findById(servicoView.getId()));
	}
	
	public Servico getServico() {
		if( servico == null ){
			Integer id = getId();
			if( id != null ){
				servico = sb.findByIdWithEstadosServico(getId() );
			}else{
				initServico(servico);
			}
		}
		return servico;
	}
	
	private void initServico(Servico servico) {
		servico.setProcessoInterno(sbProcessoInterno.findById( idProcessoInterno ) );
		servico.setTipoServicoSolicitante(new TipoServicoSolicitante());
		servico.setEstadosServicos(new ArrayList<EstadosServico>());
	}
	
	public String getNomeEstadoAtual() {
		if(nomeEstadoAtual == null) {
			Integer id = getServico().getId();  
			if(id != null) {
				ServicoView servView = sbServicoView.findById(id);
				nomeEstadoAtual = servView.getNomeEstado();
			}
		}			
		return nomeEstadoAtual;
	}

	public boolean canStart(ServicoView servico) {
		return sb.canStart(sb.findById(servico.getId()));
	}
	
	public boolean canSuspend(ServicoView servico) {
		return sb.canSuspend(sb.findById(servico.getId()));
	}
	
	public boolean canFinalize(ServicoView servico) {
		return sb.canFinalize(sb.findById(servico.getId()));
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	public UserSessionMB getUserSessionMB() {
		return userSessionMB;
	}

	public void setUserSessionMB(UserSessionMB userSessionMB) {
		this.userSessionMB = userSessionMB;
	}
	public Date getDatap() {
		return datap;
	}

	public void setDatap(Date datap) {
		this.datap = datap;
	}

	public void setNomeEstadoAtual(String nomeEstadoAtual) {
		this.nomeEstadoAtual = nomeEstadoAtual;
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
	
	public String getObservacaoEstado() {
		return observacaoEstado;
	}

	public void setObservacaoEstado(String observacaoEstado) {
		this.observacaoEstado = observacaoEstado;
	}
}
