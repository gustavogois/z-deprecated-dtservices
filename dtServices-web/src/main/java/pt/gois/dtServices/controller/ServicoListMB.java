package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.ServicoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ServicoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ServicoSBLocal sb;

	PaginatedDataModel<Servico> list;

	public ServicoListMB() {

	}

	public PaginatedDataModel<Servico> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<Servico> searchPageCtrl = new SearchPageCtrl<Servico>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
		list = new PaginatedDataModel<Servico>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<Servico> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public ServicoSBLocal getSb() {
		return sb;
	}

	public void setSb(ServicoSBLocal sb) {
		this.sb = sb;
	}
}
