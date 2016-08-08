package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal;
import pt.gois.dtServices.business.ServicoSBLocal;
import pt.gois.dtServices.business.TipoDeEstadoSBLocal;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.EntidadeDeFacturacao;
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
	
	ProcessoInterno processoInterno;
	Servico servico;
	TipoServico tipoServico;
	
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
		
		ProcessoInterno processo = getProcessoInterno();
		TipoDeEstado tipoDeEstado = sbTipoDeEstado.findById(TipoDeEstadoSBLocal.CRIADO); 
		processo.setTipoDeEstado(tipoDeEstado);
		
		ProcessoExterno processoExterno = sbProcessoExterno.findById(1);
		processo.setProcessoExterno(processoExterno);
		
		if( processo.getId() != null ){
			sb.save( processo );
		}else{
			sb.create( processo );
		}
		return "processoInternoList";
	}
	
	public String delete( ProcessoInterno processo ){
		sb.delete(processo);
		return "processoInternoList";
	}
	
	public ProcessoInterno getProcessoInterno() {
		if( processoInterno == null ){
			Integer id = getId();
			if( id != null ){
				processoInterno = sb.findById( getId() );
			}else{
				
				processoInterno = new ProcessoInterno();
				processoInterno.setEntidadeDeFacturacao(new EntidadeDeFacturacao());
			}
			
			servico = new Servico();
			servico.setTipoDeEstado(new TipoDeEstado());
			
			tipoServico = new TipoServico();
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

}
