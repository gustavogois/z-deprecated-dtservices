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

import pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal;
import pt.gois.dtServices.business.HistoricoSBLocal;
import pt.gois.dtServices.business.ServicoSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.EntidadeDeFacturacao;
import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.Historico;
import pt.gois.dtServices.entity.ProcessoExterno;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.TipoDeEstado;
import pt.gois.dtServices.entity.TipoServico;
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
	private TipoDeEstadoSBLocal sbTipoDeEstado;
	
	@EJB
	private TipoServicoSBLocal sbTipoServico;
	
	@EJB
	private ServicoSBLocal sbServico;

	@EJB
	private TipoDeEstadoSBLocal sbTipoEstado;
	
	@EJB
	private HistoricoSBLocal sbHistorico;
	
	ProcessoInterno processoInterno;
	Servico servico;
	TipoServico tipoServico;
	
	Integer idProcessoExterno;
	
	EstadosProcesso estadoProcesso;
	
	public List<TipoDeEstado> getNovosEstados() {
		ArrayList<TipoDeEstado> novosEstados = new ArrayList<TipoDeEstado>();
		if(getProcessoInterno().retornaEstadoAtual().getId() != null) {
			novosEstados.add(sbTipoDeEstado.findById(getProcessoInterno().retornaEstadoAtual().getId()));
		}
		novosEstados.addAll(sbTipoDeEstado.findNextStates(TipoDeEstadoSBLocal.PROCESSO_INTERNO, 
				getProcessoInterno().retornaEstadoAtual().getId()));
		return novosEstados;
	}

	public List<TipoDeEstado> getEstadosServico() throws Exception {

		List<TipoDeEstado> estados = sbTipoDeEstado.findByGroup(TipoDeEstadoSBLocal.SERVICOS);
		
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
		Integer idProjetoExterno = processoInterno.getProcessoExterno().getId();
		
		verificaMudancaDeEstado(processoInterno);
		
		sb.salvar(processoInterno);
		
		return "/pages/solicitante/solicitanteEdit?faces-redirect=true&id=" + idProjetoExterno;
	}

	private void verificaMudancaDeEstado(ProcessoInterno processoInterno) {
		
		if(processoInterno.getId() == null) {
			TipoDeEstado tipo = new TipoDeEstado();
			tipo.setId(TipoDeEstadoSBLocal.PI_CRIADO);
			estadoProcesso.setTiposDeEstado(tipo);
			processoInterno.getEstadosProcesso().add(estadoProcesso);
		}
	}
//		} else {
//			if(houveMudancaDeEstado()) {
//				// Cria o novo estado
//			}

//	}

	private boolean houveMudancaDeEstado() {
		return !estadoProcesso.getTiposDeEstado().getId().equals(getProcessoInterno().retornaEstadoAtual().getId());
	}
	
	public List<Historico> getHistorico() {
		
		return sbHistorico.findByObjectAndType(getId(), TipoDeEstadoSBLocal.PROCESSO_INTERNO);
	}

	
	public void delete( ProcessoInterno processo ){
		sb.delete(processo);
		
	}
	
	public ProcessoInterno getProcessoInterno() {
		if( processoInterno == null ){
			Integer id = getId();
			if( id != null ){
				processoInterno = sb.findById( getId() );
				estadoProcesso = processoInterno.retornaEstadoAtual();
			}else{
				processoInterno = new ProcessoInterno();
				processoInterno.setProcessoExterno( new ProcessoExterno() );
				processoInterno.getProcessoExterno().setId(getIdProcessoExterno());
				processoInterno.setEstadosProcesso(new ArrayList<EstadosProcesso>());
				
				servico = new Servico();
				servico.setTipoDeEstado(new TipoDeEstado());
				
				tipoServico = new TipoServico();
				
				estadoProcesso = new EstadosProcesso();
				
			}
		}
		return processoInterno;
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

	public TipoDeEstadoSBLocal getSbTipoDeEstado() {
		return sbTipoDeEstado;
	}

	public void setSbTipoDeEstado(TipoDeEstadoSBLocal sbTipoDeEstado) {
		this.sbTipoDeEstado = sbTipoDeEstado;
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
		return estadoProcesso;
	}

	public void setEstadoProcesso(EstadosProcesso estadoProcesso) {
		this.estadoProcesso = estadoProcesso;
	}
}
