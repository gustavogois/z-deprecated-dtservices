package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.EntidadeFacturacaoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Entidadedefacturacao;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class EntidadeFacturacaoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.EntidadeFacturacaoSBLocal sb;

	PaginatedDataModel<Entidadedefacturacao> list;

	public EntidadeFacturacaoListMB() {

	}

	public PaginatedDataModel<Entidadedefacturacao> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<Entidadedefacturacao> searchPageCtrl = new SearchPageCtrl<Entidadedefacturacao>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
		list = new PaginatedDataModel<Entidadedefacturacao>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<Entidadedefacturacao> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public EntidadeFacturacaoSBLocal getSb() {
		return sb;
	}

	public void setSb(EntidadeFacturacaoSBLocal sb) {
		this.sb = sb;
	}
}
