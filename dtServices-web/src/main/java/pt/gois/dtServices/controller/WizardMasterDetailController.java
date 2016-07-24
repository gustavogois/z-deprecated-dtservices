package pt.gois.dtServices.controller;

import java.awt.event.ActionEvent;
import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class WizardMasterDetailController implements Serializable {

	private static final long serialVersionUID = 20120209L;

	private User user = new User();
	private int currentLevel = 1;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void save(ActionEvent actionEvent) {
		FacesMessage msg = new FacesMessage("Saved successful", "Welcome :" + user.getFirstname());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		// create new empty user
		user = new User();
		currentLevel = 1;
	}
}
            