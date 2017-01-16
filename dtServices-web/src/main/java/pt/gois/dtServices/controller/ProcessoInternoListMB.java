package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pt.gois.dtServices.business.ProcessoInternoSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.ProcInternoView;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoInternoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ProcessoInternoSBLocal sb;

	@EJB
	private pt.gois.dtServices.business.ProcInternoViewSBLocal sbProcView;
	
	PaginatedDataModel<ProcInternoView> list;
	Integer idProcessoExterno;
	
	public PaginatedDataModel<ProcInternoView> getList() {
		return list(null);
	}

	public PaginatedDataModel<ProcInternoView> list(Integer idProcessoExterno) {
		this.setIdProcessoExterno(idProcessoExterno);
		if (list == null) {
			SearchPageCtrl<ProcInternoView> searchPageCtrl = new SearchPageCtrl<ProcInternoView>();
			Map<String, Object> filters = searchPageCtrl.getFilters();
			searchPageCtrl.setAndFilter(true);
			if( idProcessoExterno != null ){
				filters.put("processoExternoId", idProcessoExterno);
			}
			list = new PaginatedDataModel<ProcInternoView>(searchPageCtrl, sbProcView);
		}
		return list;
	}

	public void setList(PaginatedDataModel<ProcInternoView> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public ProcessoInternoSBLocal getSb() {
		return sb;
	}

	public void setSb(ProcessoInternoSBLocal sb) {
		this.sb = sb;
	}

	public List<ProcessoInterno> getProcessos(){
		return sb.findAll();
	}

	public Integer getIdProcessoExterno() {
		return idProcessoExterno;
	}

	public void setIdProcessoExterno(Integer idProcessoExterno) {
		this.idProcessoExterno = idProcessoExterno;
	}
}
