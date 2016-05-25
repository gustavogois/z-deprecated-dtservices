package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.ImovelSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.Imovel;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class ImovelListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private pt.gois.dtServices.business.ImovelSBLocal sb;

	PaginatedDataModel<Imovel> list;

	public ImovelListMB() {

	}

	public PaginatedDataModel<Imovel> getList() {
		if (list != null) {
			return list;
		}
		SearchPageCtrl<Imovel> searchPageCtrl = new SearchPageCtrl<Imovel>();
		Map<String, Object> filters = searchPageCtrl.getFilters();
		if (term != null && !"".equals(term = term.trim())) {
			if (StringUtils.isNumeric(term)) {
				filters.put("obj.id", new Integer( term ) );
			}
		}
		list = new PaginatedDataModel<Imovel>(searchPageCtrl, sb);
		return list;
	}

	public void setList(PaginatedDataModel<Imovel> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public ImovelSBLocal getSb() {
		return sb;
	}

	public void setSb(ImovelSBLocal sb) {
		this.sb = sb;
	}

	public List<Imovel> getImovels(){
		return sb.findAll();
	}

}
