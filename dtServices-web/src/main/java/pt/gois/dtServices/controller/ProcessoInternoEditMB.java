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
	
	Integer idEstadoAtual;
	

	public String getEstadoAtual() {
		
		List<EstadosProcesso> estadosProcesso = getProcessoInterno().getEstadosProcesso();
		if(estadosProcesso != null && estadosProcesso.size() > 0) {
			EstadosProcesso estadoProcesso = estadosProcesso.get(estadosProcesso.size()-1);
			return estadoProcesso.getTiposDeEstado().getNome();
		}
		return "Em criação";
	}
	
	public List<TipoDeEstado> getNovosEstados() {
		ArrayList<TipoDeEstado> novosEstados = new ArrayList<TipoDeEstado>();
		if(getProcessoInterno().getTipoDeEstado().getId() != null) {
			novosEstados.add(sbTipoDeEstado.findById(getProcessoInterno().getTipoDeEstado().getId()));
		}
		novosEstados.addAll(sbTipoDeEstado.findNextStates(TipoDeEstadoSBLocal.PROCESSO_INTERNO, getProcessoInterno().getTipoDeEstado().getId()));
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
		processoInterno.setEntidadeDeFacturacao(new EntidadeDeFacturacao());
		sb.create(processoInterno);
		return "processoInternoEdit";
	}
	
	public String save(){
		
		ProcessoInterno procInterno = getProcessoInterno();
		
		ProcessoExterno processoExterno = sbProcessoExterno.findById(procInterno.getProcessoExterno().getId());
		procInterno.setProcessoExterno(processoExterno);
		
		boolean novo = false;
		if( procInterno.getId() != null ){
			sb.save( procInterno );
		}else{
			novo = true;
			sb.create( procInterno );
		}
		
		if(!idEstadoAtual.equals(getProcessoInterno().getTipoDeEstado().getId())) {
			
			processoInterno = sb.findById(getProcessoInterno().getId());
			Historico historico = new Historico();
			historico.setIdObjeto(processoInterno.getId());
			historico.setTipoObjeto(TipoDeEstadoSBLocal.PROCESSO_INTERNO);
			historico.setData(new Date());
			String descricao;
			if(novo) {
				descricao = "Processo Interno criado";
			} else {
				descricao = "Estado do Processo Interno alterado para: " + processoInterno.getTipoDeEstado().getNome();
			}
			historico.setDescricao(descricao);
			sbHistorico.create(historico);
		}

		
		return "/pages/solicitante/solicitanteEdit?faces-redirect=true&id=" + processoExterno.getId();
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
				idEstadoAtual = processoInterno.getTipoDeEstado().getId();
			}else{
				processoInterno = new ProcessoInterno();
				processoInterno.setEntidadeDeFacturacao(new EntidadeDeFacturacao());
				processoInterno.setProcessoExterno( new ProcessoExterno() );
				processoInterno.getProcessoExterno().setId(getIdProcessoExterno());
				processoInterno.setTipoDeEstado(sbTipoDeEstado.findById(TipoDeEstadoSBLocal.PI_CRIADO));
				idEstadoAtual = TipoDeEstadoSBLocal.PI_CRIADO;
				
				servico = new Servico();
				servico.setTipoDeEstado(new TipoDeEstado());
				
				tipoServico = new TipoServico();
				
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
	
	

}
