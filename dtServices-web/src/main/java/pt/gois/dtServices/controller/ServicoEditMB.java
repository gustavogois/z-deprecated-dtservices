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

import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ServicoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ServicoSBLocal sb;
	
	Servico servico;
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<Servico> searchPageCtrl = new SearchPageCtrl<Servico>();
		searchPageCtrl.getFilters().put("nome", value);
		List<Servico> servicos = sb.find(searchPageCtrl).getRows();
		if (servicos != null && servicos.size() > 0 ) {
			if( servicos.size() == 1 && ( servicos.get(0).getId() == servico.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public void validateValor(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public String create() {
		servico = new Servico();
		servico.setSolicitante(new Solicitante());
		sb.create(servico);
		return "ServicoEdit";
	}
	
	public String save(){
		Servico Servico = getServico();
		if( Servico.getId() != null ){
			sb.save( Servico );
		}else{
			sb.create( Servico );
		}
		return "servicoList";
	}
	
	public String delete( Servico Servico ){
		sb.delete(Servico);
		return "servicoList";
	}
	
	public Servico getServico() {
		if( servico == null ){
			Integer id = getId();
			if( id != null ){
				servico = sb.findById( getId() );
			}else{
				servico = new Servico();
				servico.setSolicitante(new Solicitante());
			}
		}
		return servico;
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

}
