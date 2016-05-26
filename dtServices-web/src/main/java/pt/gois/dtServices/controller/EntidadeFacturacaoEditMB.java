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

import pt.gois.dtServices.entity.EntidadedeFacturacao;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class EntidadeFacturacaoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.EntidadeFacturacaoSBLocal sb;
	
	EntidadedeFacturacao entidade;
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<EntidadedeFacturacao> searchPageCtrl = new SearchPageCtrl<EntidadedeFacturacao>();
		searchPageCtrl.getFilters().put("nome", value);
		List<EntidadedeFacturacao> solicitantes = sb.find(searchPageCtrl).getRows();
		if (solicitantes != null && solicitantes.size() > 0 ) {
			if( solicitantes.size() == 1 && ( solicitantes.get(0).getId() == entidade.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public String create() {
		entidade = new EntidadedeFacturacao();
		sb.create(entidade);
		return "entidadeFacturacaoEdit";
	}
	
	public String save(){
		EntidadedeFacturacao entidade = getEntidade();
		if( entidade.getId() != null ){
			sb.save( entidade );
		}else{
			sb.create( entidade );
		}
		return "entidadeFacturacaoList";
	}
	
	public String delete( EntidadedeFacturacao entidade ){
		sb.delete(entidade);
		return "entidadeFacturacaoList";
	}
	
	public EntidadedeFacturacao getEntidade() {
		if( entidade == null ){
			Integer id = getId();
			if( id != null ){
				entidade = sb.findById( getId() );
			}else{
				entidade = new EntidadedeFacturacao();
			}
		}
		return entidade;
	}
	
	public void setEntidade(EntidadedeFacturacao entidade) {
		this.entidade = entidade;
	}

	public pt.gois.dtServices.business.EntidadeFacturacaoSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.EntidadeFacturacaoSBLocal sb) {
		this.sb = sb;
	}

}
