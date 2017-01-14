package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

import pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal;
import pt.gois.dtServices.business.HistoricoSBLocal;
import pt.gois.dtServices.business.ServicoSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.business.TipoServicoSB;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.EntidadeDeFacturacao;
import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.Historico;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.entity.TiposDeEstado;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoInternoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ProcessoExternoSBLocal sbProcessoExterno;
	
	@EJB
	private pt.gois.dtServices.business.ProcessoInternoSBLocal sb;
	
	@EJB
	private EntidadeDeFacturacaoSBLocal sbEntidade;

	@EJB
	private TipoServicoSBLocal sbTipoServico;
	
	@EJB
	private ServicoSBLocal sbServico;

	@EJB
	private TipoDeEstadoSBLocal sbTipoEstado;
	
	@EJB
	private HistoricoSBLocal sbHistorico;
	
	@ManagedProperty(value="#{userSessionMB}")
	private UserSessionMB userSessionMB;

	
	ProcessoInterno processoInterno;
	Servico servico;
	TipoServico tipoServico;
	
	Integer idProcessoExterno;
	EstadosProcesso estadoProcesso;

	String nomeEstadoAtual;
	String acao;
	Date data;

	
	public boolean canEdit(ProcessoInterno processo) {
		return sb.canEdit(processo);
	}
	
	public boolean canFaturar(ProcessoInterno processo) {
		return sb.canFaturar(processo);
	}
	
	public boolean canPagar(ProcessoInterno processo) {
		return sb.canPagar(processo);
	}
	
	public String getNomeEstadoAtual(ProcessoInterno processo) {
		return sb.retornaNomeEstadoAtual(processo);
	}

	public String getNomeEstadoAtual() {
		if(nomeEstadoAtual == null || nomeEstadoAtual == "") {
			processoInterno = getProcessoInterno();
			if(processoInterno.getId() != null) {
				nomeEstadoAtual = sb.retornaNomeEstadoAtual(processoInterno);
			} else {
				nomeEstadoAtual = "Em criação";
			}
		}
		return nomeEstadoAtual;
	}
	
	public List<TiposDeEstado> getEstadosServico() throws Exception {

		List<TiposDeEstado> estados = sbTipoEstado.findByGroup(TipoDeEstadoSBLocal.SERVICOS);
		
		return estados;
	}
	
	public List<TipoServico> getServicos() throws Exception {

		List<TipoServico> servicos = sbTipoServico.findAll();
		return servicos;
	}
	
	public List<EntidadeDeFacturacao> getEntidades() throws Exception {

		List<EntidadeDeFacturacao> entidades = sbEntidade.findAll();
		return entidades;
	}

	public PaginatedDataModel<Servico> getServicoByProcesso() throws Exception{
		processoInterno = getProcessoInterno();
		SearchPageCtrl<Servico> searchPageCtrl = new SearchPageCtrl<Servico>();
		searchPageCtrl.getFilters().put("obj.processo.id", processoInterno.getId());
		
		return new PaginatedDataModel<Servico>(searchPageCtrl, sbServico);
	}

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<ProcessoInterno> searchPageCtrl = new SearchPageCtrl<ProcessoInterno>();
		searchPageCtrl.getFilters().put("nome", value);
		List<ProcessoInterno> processos = sb.find(searchPageCtrl).getRows();
		if (processos != null && processos.size() > 0 ) {
			if( processos.size() == 1 && ( processos.get(0).getId() == processoInterno.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public String create() {
		processoInterno = new ProcessoInterno();
		sb.create(processoInterno);
		return "processoInternoEdit";
	}
	
	public String save(){
		
		ProcessoInterno processoInterno = getProcessoInterno();
		
		if(isFaturando()) {
			sb.salvar(processoInterno, TipoDeEstadoSBLocal.PI_AGUARDANDO_PAGAMENTO, data, userSessionMB.getUser());
		} else if(isPagando()) {
			sb.salvar(processoInterno, TipoDeEstadoSBLocal.PI_PAGO, data, userSessionMB.getUser());
		} else if(!isEditing()){
			sb.salvar(processoInterno, TipoDeEstadoSBLocal.PI_CRIADO, data, userSessionMB.getUser()); 
		} else { 
			sb.save(getProcessoInterno());
		}
		
		return "/pages/processoExterno/processoExternoEdit?faces-redirect=true&id=" + idProcessoExterno;
	}

	public List<Historico> getHistorico() {
		
		return sbHistorico.findByObjectAndType(getId(), TipoDeEstadoSBLocal.PROCESSO_INTERNO);
	}

	
	public void delete( ProcessoInterno processo ){
		try {
			sb.delete(processo);
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
	
	public ProcessoInterno getProcessoInterno() {
		if( processoInterno == null ){
			Integer id = getId();
			if( id != null ){
				processoInterno = sb.findById( getId() );
			}else{
				processoInterno = new ProcessoInterno();
				processoInterno.setProcessoExterno( sbProcessoExterno.findById( getIdProcessoExterno() ) );
				
				servico = new Servico();
				servico.setEstadosServicos(new ArrayList<EstadosServico>());
				
				tipoServico = new TipoServico();
				
				processoInterno.setEstadosProcesso(new ArrayList<EstadosProcesso>());
				data = new Date();
				
			}
		}
		return processoInterno;
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
	
	public void setProcessoInterno(ProcessoInterno processo) {
		this.processoInterno = processo;
	}

	public pt.gois.dtServices.business.ProcessoInternoSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.ProcessoInternoSBLocal sb) {
		this.sb = sb;
	}
	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public EntidadeDeFacturacaoSBLocal getSbEntidade() {
		return sbEntidade;
	}

	public void setSbEntidade(EntidadeDeFacturacaoSBLocal sbEntidade) {
		this.sbEntidade = sbEntidade;
	}

	public TipoServico getTipoServico() {
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public pt.gois.dtServices.business.ProcessoExternoSBLocal getSbProcessoExterno() {
		return sbProcessoExterno;
	}

	public void setSbProcessoExterno(pt.gois.dtServices.business.ProcessoExternoSBLocal sbProcessoExterno) {
		this.sbProcessoExterno = sbProcessoExterno;
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

	public TipoDeEstadoSBLocal getSbTipoEstado() {
		return sbTipoEstado;
	}

	public void setSbTipoEstado(TipoDeEstadoSBLocal sbTipoEstado) {
		this.sbTipoEstado = sbTipoEstado;
	}

	public void setSb(pt.gois.dtServices.business.ProcessoInternoSBLocal sb) {
		this.sb = sb;
	}

	public Integer getIdProcessoExterno() {
		return idProcessoExterno;
	}

	public void setIdProcessoExterno(Integer idProcessoExterno) {
		this.idProcessoExterno = idProcessoExterno;
	}
	
	public EstadosProcesso getEstadoProcesso() {
		if(estadoProcesso == null) {
			estadoProcesso = new EstadosProcesso();
		}
		return estadoProcesso;
	}

	public void setEstadoProcesso(EstadosProcesso estadoProcesso) {
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

}
