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

import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Processo;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoInternoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ProcessoSBLocal sb;
	
	@EJB
	private pt.gois.dtServices.business.ServicoSBLocal sbServico;

	Processo processo;
	
	Servico servico;
	
	public PaginatedDataModel<Servico> getServicoByProcesso() throws Exception{
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
	
	public String create() {
		processo = new Processo();
		sb.create(processo);
		return "processoEdit";
	}
	
	public String save(){
		Processo processo = getProcesso();
		if( processo.getId() != null ){
			sb.save( processo );
		}else{
			sb.create( processo );
		}
		return "processoList";
	}
	
	public String delete( Processo processo ){
		sb.delete(processo);
		return "processoList";
	}
	
	public Processo getProcesso() {
		if( processo == null ){
			Integer id = getId();
			if( id != null ){
				processo = sb.findById( getId() );
			}else{
				processo = new Processo();
//				processo.setProcessos(new ArrayList<Processo>());
//				processo.setTiposervicoSolicitantes(new ArrayList<TipoServicoSolicitante>());
			}
		}
		return processo;
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

}
