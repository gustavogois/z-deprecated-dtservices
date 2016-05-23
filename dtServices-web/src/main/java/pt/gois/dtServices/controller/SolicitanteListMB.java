package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.SolicitanteSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class SolicitanteListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.SolicitanteSBLocal sb;

	PaginatedDataModel<Solicitante> list;

	public SolicitanteListMB() {

	}

	public PaginatedDataModel<Solicitante> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<Solicitante> searchPageCtrl = new SearchPageCtrl<Solicitante>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			filters.put("obj.loteria.nome", term);
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
//		if( userSessionMB.getCidadeId() != null ){
//			filters.put("obj.cidade.id", userSessionMB.getCidadeId() );
//		}
		list = new PaginatedDataModel<Solicitante>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<Solicitante> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public SolicitanteSBLocal getSb() {
		return sb;
	}

	public void setSb(SolicitanteSBLocal sb) {
		this.sb = sb;
	}
}
