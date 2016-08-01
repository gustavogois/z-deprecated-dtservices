package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pt.gois.dtServices.business.LogSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Log;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class LogListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private LogSBLocal sb;

	PaginatedDataModel<Log> list;
	
	public LogListMB() {

	}

	public PaginatedDataModel<Log> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<Log> searchPageCtrl = new SearchPageCtrl<Log>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			filters.put("obj.level", term);
		}
		searchPageCtrl.getFieldToSort().put("obj.level", "asc");
		searchPageCtrl.getFieldToSort().put("obj.updateDt", "desc");
		list = new PaginatedDataModel<Log>(searchPageCtrl, sb);
		return list;
	}
	
	public void setList(PaginatedDataModel<Log> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public LogSBLocal getSb() {
		return sb;
	}

	public void setSb(LogSBLocal sbLog) {
		this.sb = sbLog;
	}
}
