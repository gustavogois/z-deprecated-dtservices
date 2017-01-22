package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import pt.gois.dtServices.business.HistoricoSBLocal;
import pt.gois.dtServices.business.ServicoSBLocal;
import pt.gois.dtServices.business.TipoEstadoSBLocal;
import pt.gois.dtServices.business.TipoServicoSB;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.EstadoProcesso;
import pt.gois.dtServices.entity.EstadoServico;
import pt.gois.dtServices.entity.Historico;
import pt.gois.dtServices.entity.Processo;
import pt.gois.dtServices.entity.ProcessoView;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.TipoEstado;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ProcessoSBLocal sb;
	
	@EJB
	private pt.gois.dtServices.business.ProcessoViewSBLocal sbProcView;
	
	@EJB
	private TipoServicoSBLocal sbTipoServico;
	
	@EJB
	private ServicoSBLocal sbServico;

	@EJB
	private TipoEstadoSBLocal sbTipoEstado;
	
	@EJB
	private HistoricoSBLocal sbHistorico;
	
	@ManagedProperty(value="#{userSessionMB}")
	private UserSessionMB userSessionMB;

	
	Processo processo;
	Servico servico;
	TipoServico tipoServico;
	
	Integer idProcessoExterno;
	EstadoProcesso estadoProcesso;

	String nomeEstadoAtual;
	String acao;
	Calendar data;

	
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
		if(nomeEstadoAtual == null || nomeEstadoAtual.equals("")) {
			Integer id = getProcesso().getId();  
			if(id != null) {
				ProcessoView procView = sbProcView.findById(id);
				nomeEstadoAtual = procView.getNomeEstado();
			}
		}			
		return nomeEstadoAtual;
	}

	public List<TipoEstado> getEstadosServico() throws Exception {

		List<TipoEstado> estados = sbTipoEstado.findByGroup(TipoEstadoSBLocal.SERVICOS);
		
		return estados;
	}
	
	public List<TipoServico> getServicos() throws Exception {

		List<TipoServico> servicos = sbTipoServico.findAll();
		return servicos;
	}
	
	public PaginatedDataModel<Servico> getServicoByProcesso() throws Exception{
		processo = getProcesso();
		SearchPageCtrl<Servico> searchPageCtrl = new SearchPageCtrl<Servico>();
		searchPageCtrl.getFilters().put("obj.processo.id", processo.getId());
		
		return new PaginatedDataModel<Servico>(searchPageCtrl, sbServico);
	}

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<Processo> searchPageCtrl = new SearchPageCtrl<Processo>();
		searchPageCtrl.getFilters().put("nome", value);
		List<Processo> processos = sb.find(searchPageCtrl).getRows();
		if (processos != null && processos.size() > 0 ) {
			if( processos.size() == 1 && ( processos.get(0).getId() == processo.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public String save(){
		
		Processo Processo = getProcesso();
		
		if(isFaturando()) {
			sb.salvar(Processo, TipoEstadoSBLocal.PI_AGUARDANDO_PAGAMENTO, data, userSessionMB.getUser());
		} else if(isPagando()) {
			sb.salvar(Processo, TipoEstadoSBLocal.PI_PAGO, data, userSessionMB.getUser());
		} else if(!isEditing()){
			sb.salvar(Processo, TipoEstadoSBLocal.PI_CRIADO, data, userSessionMB.getUser()); 
		} else { 
			sb.save(getProcesso());
		}
		
		return "/pages/processoExterno/processoExternoEdit?faces-redirect=true&id=" + idProcessoExterno;
	}

	public List<Historico> getHistorico() {
		
		return sbHistorico.findByObjectAndType(getId(), TipoEstadoSBLocal.PROCESSO_INTERNO);
	}

	
	public void delete( ProcessoView processo ){
		try {
			
			sb.delete(sb.findById(processo.getId()));
		} catch(EJBException e) {
			if(sb.isCauseException(TipoServicoSB.CONSTRAINT_VIOLATION_EXCEPTION, e)) {
				String mensagem = "Não é possível excluir este Processo Interno. Existem Serviços associados.";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "System Error"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, TipoServicoSBLocal.MSG_FATAL_ERRO, 
						"Erro desconhecido na exclusão do Solicitante"));
			}
		}
	}
	
	public Processo getProcesso() {
		if( processo == null ){
			Integer id = getId();
			if( id != null ){
				processo = sb.findById( getId() );
			}else{
				processo = new Processo();
				
				servico = new Servico();
				servico.setEstadoServicos(new ArrayList<EstadoServico>());
				
				tipoServico = new TipoServico();
				
				processo.setEstadoProcessos(new ArrayList<EstadoProcesso>());
				data = Calendar.getInstance();
				
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

	public Integer getIdProcessoExterno() {
		return idProcessoExterno;
	}

	public void setIdProcessoExterno(Integer idProcessoExterno) {
		this.idProcessoExterno = idProcessoExterno;
	}
	
	public EstadoProcesso getEstadoProcesso() {
		if(estadoProcesso == null) {
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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
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
