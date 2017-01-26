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
import pt.gois.dtServices.entity.ProcessoView;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ProcessoListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ProcessoSBLocal sb;

	@EJB
	private pt.gois.dtServices.business.ProcessoViewSBLocal sbProcView;
	
	PaginatedDataModel<ProcessoView> list;
	Integer idProcessoExterno;
	
	public PaginatedDataModel<ProcessoView> getList() {
		return list(null);
	}

	public PaginatedDataModel<ProcessoView> list(Integer idProcessoExterno) {
		this.setIdProcessoExterno(idProcessoExterno);
		if (list == null) {
			SearchPageCtrl<ProcessoView> searchPageCtrl = new SearchPageCtrl<ProcessoView>();
			Map<String, Object> filters = searchPageCtrl.getFilters();
			searchPageCtrl.setAndFilter(true);
			if (term != null && !"".equals(term = term.trim())) {
				filters.put("obj.codExterno", term);
			}
			list = new PaginatedDataModel<ProcessoView>(searchPageCtrl, sbProcView);
		}
		return list;
	}

	public void setList(PaginatedDataModel<ProcessoView> list) {
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

	public Integer getIdProcessoExterno() {
		return idProcessoExterno;
	}

	public void setIdProcessoExterno(Integer idProcessoExterno) {
		this.idProcessoExterno = idProcessoExterno;
	}
}
