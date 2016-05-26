package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.TipoServicoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class TipoServicoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.TipoServicoSBLocal sb;

	PaginatedDataModel<TipoServico> list;

	public TipoServicoListMB() {

	}

	public PaginatedDataModel<TipoServico> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<TipoServico> searchPageCtrl = new SearchPageCtrl<TipoServico>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
		list = new PaginatedDataModel<TipoServico>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<TipoServico> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public TipoServicoSBLocal getSb() {
		return sb;
	}

	public void setSb(TipoServicoSBLocal sb) {
		this.sb = sb;
	}
}
