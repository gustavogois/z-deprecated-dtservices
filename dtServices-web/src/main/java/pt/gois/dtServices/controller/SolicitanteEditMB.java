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

import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Processo;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoServicoSolicitante;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class SolicitanteEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.SolicitanteSBLocal sb;
	
	@EJB
	private pt.gois.dtServices.business.TipoServicoSolicitanteSBLocal sbTiposServicoSolicitante;

	Solicitante solicitante;
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<Solicitante> searchPageCtrl = new SearchPageCtrl<Solicitante>();
		searchPageCtrl.getFilters().put("nome", value);
		List<Solicitante> solicitantes = sb.find(searchPageCtrl).getRows();
		if (solicitantes != null && solicitantes.size() > 0 ) {
			if( solicitantes.size() == 1 && ( solicitantes.get(0).getId() == solicitante.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public void validateNif(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public void validateTelefone(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public String create() {
		solicitante = new Solicitante();
		sb.create(solicitante);
		return "solicitanteEdit";
	}
	
	public String save(){
		Solicitante solicitante = getSolicitante();
		if( solicitante.getId() != null ){
			sb.save( solicitante );
		}else{
			sb.create( solicitante );
		}
		return "solicitanteList";
	}
	
	public String delete( Solicitante solicitante ){
		sb.delete(solicitante);
		return "solicitanteList";
	}
	
	public Solicitante getSolicitante() {
		if( solicitante == null ){
			Integer id = getId();
			if( id != null ){
				solicitante = sb.findById( getId() );
			}else{
				solicitante = new Solicitante();
				solicitante.setProcessos(new ArrayList<Processo>());
				solicitante.setTiposervicoSolicitantes(new ArrayList<TipoServicoSolicitante>());
			}
		}
		return solicitante;
	}
	
	public PaginatedDataModel<TipoServicoSolicitante> getTiposServicoBySolicitante() throws Exception{
		SearchPageCtrl<TipoServicoSolicitante> searchPageCtrl = new SearchPageCtrl<TipoServicoSolicitante>();
		searchPageCtrl.getFilters().put("obj.solicitante.id", solicitante.getId());
		//List<TipoServicoSolicitante> tiposDeServico = sbTiposServicoSolicitante.find(searchPageCtrl).getRows();
		
		return new PaginatedDataModel<TipoServicoSolicitante>(searchPageCtrl, sbTiposServicoSolicitante);
	}

	
	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

	public pt.gois.dtServices.business.SolicitanteSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.SolicitanteSBLocal sb) {
		this.sb = sb;
	}

}
