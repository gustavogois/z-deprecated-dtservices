package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.EnderecoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Endereco;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class EnderecoMB extends GeneralMB implements Serializable {
    private static final long serialVersionUID = 1L;

    @EJB
    private EnderecoSBLocal sb;

    PaginatedDataModel<Endereco> list;

    Integer recolheId;

    public EnderecoMB() {

    }

    public PaginatedDataModel<Endereco> getList() {
	if (list == null) {
	    SearchPageCtrl<Endereco> searchPageCtrl = new SearchPageCtrl<Endereco>();
	    Map<String, Object> filters = searchPageCtrl.getFilters();
	    searchPageCtrl.setAndFilter(true);
	    if (term != null && !"".equals(term = term.trim())) {
		filters.put("obj.completo", term);
	    }
	    list = new PaginatedDataModel<Endereco>(searchPageCtrl, sb);
	}
	return list;
    }

    public PaginatedDataModel<Endereco> getListByRecolheId(Integer recolheId) {
	this.recolheId = recolheId;
	return getList();
    }

    public void setList(PaginatedDataModel<Endereco> list) {
	this.list = list;
    }

    public void setEnderecos(PaginatedDataModel<Endereco> list) {
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
}
