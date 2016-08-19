package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.ProcessoExternoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.ProcessoExterno;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoExternoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProcessoExternoSBLocal sb;

	PaginatedDataModel<ProcessoExterno> list;

	public ProcessoExternoListMB() {

	}

	public PaginatedDataModel<ProcessoExterno> getList() {
		if (list == null) {
			SearchPageCtrl<ProcessoExterno> searchPageCtrl = new SearchPageCtrl<ProcessoExterno>();
			Map<String, Object> filters = searchPageCtrl.getFilters();
			searchPageCtrl.setAndFilter(true);
			if (term != null && !"".equals(term = term.trim())) {
				filters.put("obj.loteria.nome", term);
				if (StringUtils.isNumeric(term)) {
					filters.put("obj.id", new Integer(term));
				}
			}
			list = new PaginatedDataModel<ProcessoExterno>(searchPageCtrl, sb);
		}
		return list;
	}
	
	public void setList(PaginatedDataModel<ProcessoExterno> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public ProcessoExternoSBLocal getSb() {
		return sb;
	}

	public void setSb(ProcessoExternoSBLocal sb) {
		this.sb = sb;
	}

}
