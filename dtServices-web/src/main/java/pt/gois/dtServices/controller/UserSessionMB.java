package pt.gois.dtServices.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.security.SecurityContextAssociation;

import pt.gois.dtServices.business.UserSBLocal;
import pt.gois.dtServices.entity.User;

@ManagedBean
@SessionScoped
public class UserSessionMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserSBLocal sbUser;
	
	User user;
	
	Integer solicitanteId;

	public UserSessionMB() {
	}
	
	public void logout() throws IOException{
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		SecurityContextAssociation.clearSecurityContext();
		setUser(null);
		fc.getExternalContext().redirect( fc.getExternalContext().getRequestContextPath() + "/pages/home.jsf");
	}

	
	public void saveUser(){
		sbUser.save(user);
	}


	public User getUser() {
		if( user == null ){
			String username = SecurityContextAssociation.getPrincipal().getName();
			user = getSbUser().findByUsername(username);
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSBLocal getSbUser() {
		return sbUser;
	}

	public void setSbUser(UserSBLocal sbUser) {
		this.sbUser = sbUser;
	}
	
	public Integer getSolicitanteId() {
		return solicitanteId;
	}

	public void setSolicitanteId(Integer solicitanteId) {
		this.solicitanteId = solicitanteId;
	}

}
