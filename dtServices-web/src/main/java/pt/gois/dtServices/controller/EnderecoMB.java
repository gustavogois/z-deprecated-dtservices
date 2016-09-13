package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pt.gois.dtServices.business.EnderecoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Concelho;
import pt.gois.dtServices.entity.Distrito;
import pt.gois.dtServices.entity.EnderecoVW;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class EnderecoMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private EnderecoSBLocal sb;

	PaginatedDataModel<EnderecoVW> list;
	List<Distrito> distritos;
	List<Concelho> concelhos;

	Integer recolheId;

	public EnderecoMB() {

	}

	public PaginatedDataModel<EnderecoVW> getList() {
		SearchPageCtrl<EnderecoVW> searchPageCtrl = new SearchPageCtrl<EnderecoVW>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		searchPageCtrl.setAndFilter(true);
		if (term != null && !"".equals(term = term.trim())) {
			filters.put("obj.completo", term);
		}
		list = new PaginatedDataModel<EnderecoVW>(searchPageCtrl, sb);
		return list;
	}

	public PaginatedDataModel<EnderecoVW> getListByRecolheId(Integer recolheId) {
		this.recolheId = recolheId;
		return getList();
	}

	public void setList(PaginatedDataModel<EnderecoVW> list) {
		this.list = list;
	}

	public void setEnderecos(PaginatedDataModel<EnderecoVW> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public EnderecoSBLocal getSb() {
		return sb;
	}

	public void setSb(EnderecoSBLocal sb) {
		this.sb = sb;
	}

	public Integer getRecolheId() {
		return recolheId;
	}

	public void setRecolheId(Integer recolheId) {
		this.recolheId = recolheId;
	}

	public List<Distrito> getDistritos() {
		if (distritos == null) {
			distritos = sb.getDistritos();
		}
		return distritos;
	}

	public List<Concelho> getConcelhos(Distrito distrito) {
		if (concelhos == null) {
			concelhos = sb.getConcelhos(distrito);
		}
		return concelhos;
	}

	public List<EnderecoVW> search(String query) {
		SearchPageCtrl<EnderecoVW> searchPageCtrl = new SearchPageCtrl<EnderecoVW>();
		searchPageCtrl.setPageSize(10);
		searchPageCtrl.setAndFilter(true);

//		Map<String, Object> filters = searchPageCtrl.getFilters();
//		if (query != null && !"".equals(query = query.trim())) {
//			filters.put("obj.completo", "%" + query + "%");
//		}
		Map<String, Object> textualFilters = searchPageCtrl.getTextualFilters();
		if (query != null && !"".equals(query = query.trim())) {
			textualFilters.put(" '' = fulltextSearch( 'searchAddress', :query )", query );
		}

		
		sb.find(searchPageCtrl);
		return searchPageCtrl.getRows();
	}
}
