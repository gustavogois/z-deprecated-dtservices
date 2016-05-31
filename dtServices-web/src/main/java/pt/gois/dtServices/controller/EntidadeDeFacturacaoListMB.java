package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.EntidadeDeFacturacao;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class EntidadeDeFacturacaoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.EntidadeDeFacturacaoSBLocal sb;

	PaginatedDataModel<EntidadeDeFacturacao> list;

	public EntidadeDeFacturacaoListMB() {

	}

	public PaginatedDataModel<EntidadeDeFacturacao> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<EntidadeDeFacturacao> searchPageCtrl = new SearchPageCtrl<EntidadeDeFacturacao>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
		list = new PaginatedDataModel<EntidadeDeFacturacao>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<EntidadeDeFacturacao> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public EntidadeDeFacturacaoSBLocal getSb() {
		return sb;
	}

	public void setSb(EntidadeDeFacturacaoSBLocal sb) {
		this.sb = sb;
	}
}
