package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.ServicoViewSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.ServicoView;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ServicoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ServicoViewSBLocal sb;

	PaginatedDataModel<ServicoView> list;
	
	Integer idProc;

	public ServicoListMB() {

	}

	public PaginatedDataModel<ServicoView> list(Integer idProc) {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<ServicoView> searchPageCtrl = new SearchPageCtrl<ServicoView>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
		if (idProc != null) {
			filters.put("processoId", idProc);
		}
		list = new PaginatedDataModel<ServicoView>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<ServicoView> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public ServicoViewSBLocal getSb() {
		return sb;
	}

	public void setSb(ServicoViewSBLocal sb) {
		this.sb = sb;
	}
	public Integer getidProc() {
		return idProc;
	}

	public void setidProc(Integer idProc) {
		this.idProc = idProc;
	}
}
