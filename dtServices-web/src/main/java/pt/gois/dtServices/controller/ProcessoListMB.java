package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.ProcessoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Processo;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ProcessoSBLocal sb;

	PaginatedDataModel<Processo> list;

	public ProcessoListMB() {

	}

	public PaginatedDataModel<Processo> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<Processo> searchPageCtrl = new SearchPageCtrl<Processo>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
		list = new PaginatedDataModel<Processo>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<Processo> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public ProcessoSBLocal getSb() {
		return sb;
	}

	public void setSb(ProcessoSBLocal sb) {
		this.sb = sb;
	}

	public List<Processo> getProcessos(){
		return sb.findAll();
	}

}
