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

import pt.gois.dtServices.entity.Imovel;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ImovelEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ImovelSBLocal sb;
	
	Imovel imovel;
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<Imovel> searchPageCtrl = new SearchPageCtrl<Imovel>();
		searchPageCtrl.getFilters().put("nome", value);
		List<Imovel> imovels = sb.find(searchPageCtrl).getRows();
		if (imovels != null && imovels.size() > 0 ) {
			if( imovels.size() == 1 && ( imovels.get(0).getId() == imovel.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public void validateEndereco(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public void validateCrp(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public void validateInquilino(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public String create() {
		imovel = new Imovel();
		sb.create(imovel);
		return "imovelEdit";
	}
	
	public String save(){
		Imovel imovel = getImovel();
		if( imovel.getId() != null ){
			sb.save( imovel );
		}else{
			sb.create( imovel );
		}
		return "imovelList";
	}
	
	public String delete( Imovel imovel ){
		sb.delete(imovel);
		return "imovelList";
	}
	
	public Imovel getImovel() {
		if( imovel == null ){
			Integer id = getId();
			if( id != null ){
				imovel = sb.findById( getId() );
			}else{
				imovel = new Imovel();
			}
		}
		return imovel;
	}
	
	public void setImovel(Imovel Imovel) {
		this.imovel = Imovel;
	}

	public pt.gois.dtServices.business.ImovelSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.ImovelSBLocal sb) {
		this.sb = sb;
	}

}
