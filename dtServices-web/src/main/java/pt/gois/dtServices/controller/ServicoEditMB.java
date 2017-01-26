package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import pt.gois.dtServices.business.ProcessoSBLocal;
import pt.gois.dtServices.business.ServicoViewSBLocal;
import pt.gois.dtServices.business.TipoEstadoSBLocal;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.business.UserSBLocal;
import pt.gois.dtServices.entity.EstadoServico;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.ServicoView;
import pt.gois.dtServices.entity.TipoEstado;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.entity.User;
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
	private ProcessoSBLocal sbProcesso;
	
	@EJB
	private TipoServicoSBLocal sbTipoServico;
	
	@EJB
	private UserSBLocal sbUser;
	
	@ManagedProperty(value="#{userSessionMB}")
	private UserSessionMB userSessionMB;

	
	Servico servico;
	Integer idProcesso;
	String codExternoProcesso;
	String codInternoProcesso;
	
	

	String nomeEstadoAtual;
	String observacaoEstado;

	String acao;
	Date datap;
	
	List<EstadoServico> estadosServicos;

	public List<EstadoServico> getEstadosServicos() {
		if(estadosServicos == null) {
			Servico servico = sb.findByIdWithEstadosServico(getServico().getId());
			estadosServicos = servico.getEstadoServicos();
		}
		return estadosServicos;
	}

	public String getUsername(Integer userId) {
		User user = sbUser.findById(userId);
		return user.getName();
	}
	
	public void setEstadosServicos(List<EstadoServico> estadosServicos) {
		this.estadosServicos = estadosServicos;
	}

	public List<TipoServico> getTiposServico() {
		
		SearchPageCtrl<TipoServico> searchPageCtrl = new SearchPageCtrl<TipoServico>();
		List<TipoServico> tiposServico = sbTipoServico.find(searchPageCtrl).getRows();
		
		return tiposServico;
		
	}
	
	public void onServicoChange() {
		TipoServico tss = sbTipoServico.findById(servico.getTipoServico().getId());
		servico.setValor(tss.getValor());
	}
	
	private Date getDataCalendar() {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(datap);
//		return calendar;
		return datap;
	}
	
	private void adicionaNovoEstado(Servico servico) {
		
		EstadoServico novoEstado = new EstadoServico();
		TipoEstado tipo;
		
		if(isStarting()) {
			tipo = new TipoEstado(TipoEstadoSBLocal.SRV_EM_EXECUCAO);
		} else if(isSuspending()) {
			tipo = new TipoEstado(TipoEstadoSBLocal.SRV_SUSPENSO);
		} else if(isFinalizing()) {
			tipo = new TipoEstado(TipoEstadoSBLocal.SRV_FINALIZADO);
		} else if(!isEditing()){
			tipo = new TipoEstado(TipoEstadoSBLocal.SRV_CRIADO);
			datap = new Date();
		} else {
			return;
		}
		
		novoEstado.setTipoEstado(tipo);
		novoEstado.setDataInicio(getDataCalendar());
		novoEstado.setUserId(userSessionMB.getUser().getId());
		novoEstado.setObservacoes(observacaoEstado);
		novoEstado.setServico(servico);
		servico.getEstadoServicos().add(novoEstado);
	}
	
	public String save(){
		
		servico = getServico();
		
		adicionaNovoEstado(servico);
		
		sb.salvar(servico, userSessionMB.getUser());
		
		return "/pages/processo/processoEdit?faces-redirect=true&id=" + getServico().getProcesso().getId();
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
				servico = new Servico();
				servico.setProcesso(sbProcesso.findById( idProcesso ) );
				servico.setTipoServico(new TipoServico());
				servico.setEstadoServicos(new ArrayList<EstadoServico>());
			}
		}
		return servico;
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
	public Integer getIdProcesso() {
		return idProcesso;
	}

	public void setIdProcesso(Integer idProcesso) {
		this.idProcesso = idProcesso;
	}
	
	public String getObservacaoEstado() {
		return observacaoEstado;
	}

	public void setObservacaoEstado(String observacaoEstado) {
		this.observacaoEstado = observacaoEstado;
	}
	public String getCodExternoProcesso() {
		return codExternoProcesso;
	}

	public void setCodExternoProcesso(String codExternoProcesso) {
		this.codExternoProcesso = codExternoProcesso;
	}

	public String getCodInternoProcesso() {
		return codInternoProcesso;
	}

	public void setCodInternoProcesso(String codInternoProcesso) {
		this.codInternoProcesso = codInternoProcesso;
	}

}
