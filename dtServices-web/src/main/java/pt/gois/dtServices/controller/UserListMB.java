package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import pt.gois.dtServices.business.UserSBLocal;
import pt.gois.dtServices.controller.util.PaginatedDataModel;
import pt.gois.dtServices.entity.User;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class UserListMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserSBLocal sb;

	PaginatedDataModel<User> list;

	Integer recolheId;

	public UserListMB() {

	}

	public PaginatedDataModel<User> getList() {
		if (list == null) {
			SearchPageCtrl<User> searchPageCtrl = new SearchPageCtrl<User>();
			Map<String, Object> filters = searchPageCtrl.getFilters();
			searchPageCtrl.setAndFilter(true);
			if (term != null && !"".equals(term = term.trim())) {
				filters.put("obj.loteria.nome", term);
				if (StringUtils.isNumeric(term)) {
					filters.put("obj.id", new Integer(term));
				}
			}
			list = new PaginatedDataModel<User>(searchPageCtrl, sb);
		}
		return list;
	}
	
	public PaginatedDataModel<User> getListByRecolheId(Integer recolheId) {
		this.recolheId = recolheId;
		return getList();		
	}

	public void setList(PaginatedDataModel<User> list) {
		this.list = list;
	}

	public void setUsers(PaginatedDataModel<User> list) {
		this.list = list;
	}

	public void search() throws Exception {
		list = null;
	}

	public UserSBLocal getSb() {
		return sb;
	}

	public void setSb(UserSBLocal sb) {
		this.sb = sb;
	}

	public Integer getRecolheId() {
		return recolheId;
	}

	public void setRecolheId(Integer recolheId) {
		this.recolheId = recolheId;
	}
}
