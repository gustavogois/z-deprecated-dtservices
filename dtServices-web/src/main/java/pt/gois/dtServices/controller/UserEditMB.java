package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import pt.gois.dtServices.business.UserSBLocal;
import pt.gois.dtServices.entity.TipoDeUser;
import pt.gois.dtServices.entity.User;
import pt.gois.dtServices.util.SearchPageCtrl;

@ManagedBean
@ViewScoped
public class UserEditMB extends GeneralMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserSBLocal sb;

	User user;

	public void validateName(FacesContext context, UIComponent toValidate, Object value) throws Exception {
		String name = (String) value;

		if (name.trim().length() == 0) {
			throw new ValidatorException(getMessage("default_msg_emptyTerm", FacesMessage.SEVERITY_ERROR));
		}

		SearchPageCtrl<User> searchPageCtrl = new SearchPageCtrl<User>();
		searchPageCtrl.getFilters().put("nome", value);
		List<User> users = sb.find(searchPageCtrl).getRows();
		if (users != null && users.size() > 0) {
			if (users.size() == 1 && (users.get(0).getId() == user.getId())) {
				return;
			}
			throw new ValidatorException(getMessage("default_msg_exists", FacesMessage.SEVERITY_ERROR));
		}
	}

	public String create() {
		user = new User();
		sb.create(user);
		return "userEdit";
	}

	public String save() {
		User user = getUser();
		if (user.getId() != null) {
			sb.save(user);
		} else {
			user.setCreateDt(new Date());
			sb.create(user);
		}
		return "userList";
	}

	public void delete(User user) {
		sb.delete(user);
	}

	public User getUser() {
		if (user == null) {
			Integer id = getId();
			if (id != null) {
				user = sb.findById(getId());
			} else {
				user = new User();
				user.setTipoDeUser(new TipoDeUser());
			}
		}
		return user;
	}

	public List<TipoDeUser> getTipoDeUsers() {
		return sb.getTipoDeUsers();
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSBLocal getSb() {
		return sb;
	}

	public void setSb(UserSBLocal sb) {
		this.sb = sb;
	}

}
