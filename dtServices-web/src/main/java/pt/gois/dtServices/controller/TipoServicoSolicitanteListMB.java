package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.TipoServicoSolicitanteSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.TipoServicoSolicitante;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class TipoServicoSolicitanteListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private TipoServicoSolicitanteSBLocal sb;

	PaginatedDataModel<TipoServicoSolicitante> list;

	public TipoServicoSolicitanteListMB() {

	}

	public PaginatedDataModel<TipoServicoSolicitante> getList() {
		if (list == null) {
			SearchPageCtrl<TipoServicoSolicitante> searchPageCtrl = new SearchPageCtrl<TipoServicoSolicitante>();
			Map<String, Object> filters = searchPageCtrl.getFilters();
			searchPageCtrl.setAndFilter(true);
			if (term != null && !"".equals(term = term.trim())) {
				filters.put("obj.loteria.nome", term);
				if (StringUtils.isNumeric(term)) {
					filters.put("obj.id", new Integer(term));
				}
			}
			if(userSessionMB.getSolicitanteId() != null) {
				filters.put("obj.solicitante.id", userSessionMB.getSolicitanteId());
			}
			list = new PaginatedDataModel<TipoServicoSolicitante>(searchPageCtrl, sb);
		}
		return list;
	}
	
	public void setList(PaginatedDataModel<TipoServicoSolicitante> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public TipoServicoSolicitanteSBLocal getSb() {
		return sb;
	}

	public void setSb(TipoServicoSolicitanteSBLocal sb) {
		this.sb = sb;
	}

}
