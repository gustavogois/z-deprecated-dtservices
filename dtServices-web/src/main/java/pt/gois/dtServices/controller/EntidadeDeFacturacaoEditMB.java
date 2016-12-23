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

import pt.gois.dtServices.entity.EntidadeDeFacturacao;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class EntidadeDeFacturacaoEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal sb;
	
	@EJB
	private pt.gois.dtServices.business.SolicitanteSBLocal sbSolicitante;
	
	EntidadeDeFacturacao entidade;
	
	Integer idSolicitante;
	
	

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm",FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<EntidadeDeFacturacao> searchPageCtrl = new SearchPageCtrl<EntidadeDeFacturacao>();
		searchPageCtrl.getFilters().put("nome", value);
		List<EntidadeDeFacturacao> solicitantes = sb.find(searchPageCtrl).getRows();
		if (solicitantes != null && solicitantes.size() > 0 ) {
			if( solicitantes.size() == 1 && ( solicitantes.get(0).getId() == entidade.getId() ) ){
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists",FacesMessage.SEVERITY_ERROR));
		}
	}
	
	public String create() {
		entidade = new EntidadeDeFacturacao();
		sb.create(entidade);
		return "entidadeFacturacaoEdit";
	}
	
	public String save(){
		
		// Implementar o save, levando para o EJB a lógica de o ligar ao pai, verificar id para save ou create, etc.
		
		EntidadeDeFacturacao entidade = getEntidade();
		idSolicitante = sb.salvar(entidade, idSolicitante);
		
		return "/pages/solicitante/solicitanteEdit?faces-redirect=true&id=" + idSolicitante;
	}
	
	public List<Solicitante> getSolicitantes() {
		return sbSolicitante.findAll();
	}

	public String delete( EntidadeDeFacturacao entidade ){
		sb.delete(entidade);
		return "/pages/solicitante/solicitanteEdit?faces-redirect=true&id=" + idSolicitante;
	}
	
	public EntidadeDeFacturacao getEntidade() {
		if( entidade == null ){
			Integer id = getId();
			if( id != null ){
				entidade = sb.findById( getId() );
			}else{
				entidade = new EntidadeDeFacturacao();
				entidade.setSolicitante(new Solicitante());
			}
		}
		return entidade;
	}
	
	public void setEntidade(EntidadeDeFacturacao entidade) {
		this.entidade = entidade;
	}

	public pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal getSb() {
		return sb;
	}

	public void setSBLocalb(pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal sb) {
		this.sb = sb;
	}
	public Integer getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Integer idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

}
