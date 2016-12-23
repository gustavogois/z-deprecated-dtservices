package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import pt.gois.dtServices.business.TipoServicoSB;
import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class TipoServicoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.TipoServicoSBLocal sb;
	
	TipoServico tipoServico;
	
	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<TipoServico> searchPageCtrl = new SearchPageCtrl<TipoServico>();
		searchPageCtrl.getFilters().put("nome", value);
		List<TipoServico> tipoServicos = sb.find(searchPageCtrl).getRows();
		if (tipoServicos != null && tipoServicos.size() > 0 ) {
			if( tipoServicos.size() == 1 && ( tipoServicos.get(0).getId() == tipoServico.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public void validateValor(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		
	}
	
	public String create() {
		tipoServico = new TipoServico();
		//TipoServico.setSolicitante(new Solicitante());
		sb.create(tipoServico);
		return "tipoServicoEdit";
	}
	
	public String save(){
		TipoServico tipoServico = getTipoServico();
		if( tipoServico.getId() != null ){
			sb.save( tipoServico );
		}else{
			sb.create( tipoServico );
		}
		return "tipoServicoList";
	}
	
	public void delete(TipoServico tipoServico){
		try {
			sb.delete(tipoServico);
		} catch(EJBException e) {
			if(sb.isCauseException(TipoServicoSB.CONSTRAINT_VIOLATION_EXCEPTION, e)) {
				String mensagem = "Não é possível excluir este Tipo de Serviço. Existem Solicitantes associados.";
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, "System Error"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, TipoServicoSBLocal.MSG_FATAL_ERRO, 
						"Erro desconhecido na exclusão do Tipo de Serviço"));
			}
		}
	}
	
	public TipoServico getTipoServico() {
		if( tipoServico == null ){
			Integer id = getId();
			if( id != null ){
				tipoServico = sb.findById( getId() );
			}else{
				tipoServico = new TipoServico();
				//TipoServico.setSolicitante(new Solicitante());
			}
		}
		return tipoServico;
	}
	
	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public pt.gois.dtServices.business.TipoServicoSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.TipoServicoSBLocal sb) {
		this.sb = sb;
	}

}
