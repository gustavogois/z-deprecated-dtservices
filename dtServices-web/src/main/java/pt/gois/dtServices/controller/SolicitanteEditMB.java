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

import org.primefaces.event.SelectEvent;

import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.EnderecoVW;
import pt.gois.dtServices.entity.EntidadeDeFacturacao;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoServico;
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
	
	@EJB
	private pt.gois.dtServices.business.EnderecoSBLocal sbEndereco;

	@EJB
	private pt.gois.dtServices.business.TipoServicoSBLocal sbTiposServico;

	@EJB
	private pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal sbEntidade;
	
	Solicitante solicitante;
	TipoServico tipoServico;
	TipoServicoSolicitante tipoServicoSolicitante;
	List<TipoServicoSolicitante> tiposServicoPorSolicitante;

	PaginatedDataModel<TipoServicoSolicitante> tipoServicoSolicitantesPDM;
	
	
	
	public PaginatedDataModel<EntidadeDeFacturacao> getEntidadesBySolicitante() throws Exception {
		
		SearchPageCtrl<EntidadeDeFacturacao> searchPageCtrl = new SearchPageCtrl<EntidadeDeFacturacao>();
		searchPageCtrl.getFilters().put("obj.solicitante.id", getId());

		return new PaginatedDataModel<EntidadeDeFacturacao>(searchPageCtrl, sbEntidade);
	}
	
	public List<TipoServico> getTiposDeServico() throws Exception {

		List<TipoServico> tipoDeServicoNotYetSelected = sbTiposServico.findAll();
		if (tiposServicoPorSolicitante == null) {
			tiposServicoPorSolicitante = (List<TipoServicoSolicitante>) getTiposServicoBySolicitante().getWrappedData();
		}
		for (TipoServicoSolicitante tipoServicoPorSolicitante : tiposServicoPorSolicitante) {
			TipoServico tipo = new TipoServico();
			tipo.setId(tipoServicoPorSolicitante.getTipoServico().getId());
			tipoDeServicoNotYetSelected.remove(tipo);
		}

		if (tipoDeServicoNotYetSelected != null && tipoDeServicoNotYetSelected.size() > 0) {
			tipoServico = tipoDeServicoNotYetSelected.get(0);
			tipoServicoSolicitante = getTipoServicoSolicitante();
			tipoServicoSolicitante.setValor(tipoServico.getValor());
		}
		return tipoDeServicoNotYetSelected;
	}

	public void onTipoServicoChange() {
		if (tipoServico != null) {
			tipoServico = sbTiposServico.findById(tipoServico.getId());
		}
		tipoServicoSolicitante.setTipoServico(tipoServico);
		tipoServicoSolicitante.setValor(tipoServico.getValor());
	}

	public void addTipoDeServico() {

		salvaSolicitante(solicitante);
		tipoServicoSolicitante.setSolicitante(solicitante);
		tipoServicoSolicitante.setTipoServico(sbTiposServico.findById(tipoServico.getId()));
		sbTiposServicoSolicitante.save(tipoServicoSolicitante);

	}

	public void deleteTipoDeServico(TipoServicoSolicitante tipoServicoSolicitante) {

		sbTiposServicoSolicitante.delete(tipoServicoSolicitante);
		tipoServicoSolicitantesPDM = null;

	}

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm", FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<Solicitante> searchPageCtrl = new SearchPageCtrl<Solicitante>();
		searchPageCtrl.getFilters().put("nome", value);
		List<Solicitante> solicitantes = sb.find(searchPageCtrl).getRows();
		if (solicitantes != null && solicitantes.size() > 0) {
			if (solicitantes.size() == 1 && (solicitantes.get(0).getId() == solicitante.getId())) {
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists", FacesMessage.SEVERITY_ERROR));
		}
	}

	public void validateNif(FacesContext context, UIComponent toValidate, Object value) throws Exception {

	}

	public void validateTelefone(FacesContext context, UIComponent toValidate, Object value) throws Exception {

	}

	public String create() {
		solicitante = new Solicitante();
		tipoServico = new TipoServico();
		sb.create(solicitante);
		return "solicitanteEdit";
	}

	public String save() {
		Solicitante solicitante = getSolicitante();
		
		salvaSolicitante(solicitante);
		return "solicitanteList";
	}

	private void salvaSolicitante(Solicitante solicitante) {
		
		solicitante = getSolicitante();
		
		if (solicitante.getId() != null) {
			sb.save(solicitante);
			addMessage("default_msg_saved");
		} else {
			sb.create(solicitante);
			addMessage("default_msg_created");
		}
	}

	public String delete(Solicitante solicitante) {
		sb.delete(solicitante);
		return "solicitanteList";
	}

	public Solicitante getSolicitante() {
		if (solicitante == null) {
			Integer id = getId();
			if (id != null) {
				solicitante = sb.findById(getId());
			} else {
				solicitante = new Solicitante();
				solicitante.setEntidadeDeFacturacao(new ArrayList<EntidadeDeFacturacao>());
				solicitante.setChaveSolicitanteProcesso(new Integer(0));
			}
		}
		return solicitante;
	}

	public PaginatedDataModel<TipoServicoSolicitante> getTiposServicoBySolicitante() throws Exception {
		if (naoENovoSolicitante()) {
			SearchPageCtrl<TipoServicoSolicitante> searchPageCtrl = new SearchPageCtrl<TipoServicoSolicitante>();
			searchPageCtrl.getFilters().put("obj.solicitante.id", solicitante.getId());

			tiposServicoPorSolicitante = sbTiposServicoSolicitante.find(searchPageCtrl).getRows();
			solicitante.setTipoServicoSolicitantes(tiposServicoPorSolicitante);

			tipoServicoSolicitantesPDM = new PaginatedDataModel<TipoServicoSolicitante>(searchPageCtrl,
					sbTiposServicoSolicitante);
			tipoServicoSolicitantesPDM.setWrappedData(tiposServicoPorSolicitante);
		}

		return tipoServicoSolicitantesPDM;
	}
	
	public void onEnderecoSelect(SelectEvent event) {
		Solicitante solicitante = getSolicitante();
		EnderecoVW end = (EnderecoVW) event.getObject();
		end = sbEndereco.findById(end.getId());

		solicitante.setRuaPorta(end.getRuaPorta());
		solicitante.setComplemento(end.getComplemento());
		solicitante.setLocalidade(end.getLocalidade());
		solicitante.setDistrito(end.getDistrito());
		solicitante.setConcelho(end.getConcelho());
		solicitante.setCodigoPostal(end.getCodigoPostal());
	}

	private boolean naoENovoSolicitante() {
		return solicitante != null && tipoServicoSolicitantesPDM == null;
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

	public TipoServico getTipoServico() {
		if (this.tipoServico == null) {
			tipoServico = new TipoServico();
		}
		return tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public List<TipoServicoSolicitante> getTiposServicoPorSolicitante() {
		return tiposServicoPorSolicitante;
	}

	public void setTiposServicoPorSolicitante(List<TipoServicoSolicitante> tiposServicoPorSolicitante) {
		this.tiposServicoPorSolicitante = tiposServicoPorSolicitante;
	}

	public TipoServicoSolicitante getTipoServicoSolicitante() {
		if (tipoServicoSolicitante == null) {
			tipoServicoSolicitante = new TipoServicoSolicitante();
		}
		return tipoServicoSolicitante;
	}

	public void setTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		this.tipoServicoSolicitante = tipoServicoSolicitante;
	}

	public pt.gois.dtServices.business.TipoServicoSolicitanteSBLocal getSbTiposServicoSolicitante() {
		return sbTiposServicoSolicitante;
	}

	public void setSbTiposServicoSolicitante(
			pt.gois.dtServices.business.TipoServicoSolicitanteSBLocal sbTiposServicoSolicitante) {
		this.sbTiposServicoSolicitante = sbTiposServicoSolicitante;
	}

	public pt.gois.dtServices.business.EnderecoSBLocal getSbEndereco() {
		return sbEndereco;
	}

	public void setSbEndereco(pt.gois.dtServices.business.EnderecoSBLocal sbEndereco) {
		this.sbEndereco = sbEndereco;
	}

	public pt.gois.dtServices.business.TipoServicoSBLocal getSbTiposServico() {
		return sbTiposServico;
	}

	public void setSbTiposServico(pt.gois.dtServices.business.TipoServicoSBLocal sbTiposServico) {
		this.sbTiposServico = sbTiposServico;
	}

	public pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal getSbEntidade() {
		return sbEntidade;
	}

	public void setSbEntidade(pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal sbEntidade) {
		this.sbEntidade = sbEntidade;
	}

	public void setSb(pt.gois.dtServices.business.SolicitanteSBLocal sb) {
		this.sb = sb;
	}

}
