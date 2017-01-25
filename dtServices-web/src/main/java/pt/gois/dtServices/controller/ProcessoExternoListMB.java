package pt.gois.dtServices.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProcessoExternoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EJB
//	private ProcessoExternoSBLocal sb;
//
//	PaginatedDataModel<ProcessoExterno> list;
//
//	public ProcessoExternoListMB() {
//
//	}
//
//	public PaginatedDataModel<ProcessoExterno> getList() {
//		if (list == null) {
//			SearchPageCtrl<ProcessoExterno> searchPageCtrl = new SearchPageCtrl<ProcessoExterno>();
//			Map<String, Object> filters = searchPageCtrl.getFilters();
//			searchPageCtrl.setAndFilter(true);
//			if (term != null && !"".equals(term = term.trim())) {
//				filters.put("obj.loteria.nome", term);
//				if (StringUtils.isNumeric(term)) {
//					filters.put("obj.id", new Integer(term));
//				}
//			}
//			list = new PaginatedDataModel<ProcessoExterno>(searchPageCtrl, sb);
//		}
//		return list;
//	}
//	
//	public void setList(PaginatedDataModel<ProcessoExterno> list) {
//		this.list = list;
//	}
//
//	public void search() throws Exception {
//		list = null;
//	}
//
//	public ProcessoExternoSBLocal getSb() {
//		return sb;
//	}
//
//	public void setSb(ProcessoExternoSBLocal sb) {
//		this.sb = sb;
//	}
//
}
