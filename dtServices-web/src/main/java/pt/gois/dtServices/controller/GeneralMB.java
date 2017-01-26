package pt.gois.dtServices.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

public class GeneralMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{userSessionMB}")
	UserSessionMB userSessionMB;

	Integer id;

	String term;

	public void addMessage(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_INFO);
	}

	public void addMessage(String msg, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, getMessage(msg, severity));
	}
	
	public void addError(Throwable e){
		if( e instanceof SQLException ){
			addMessage(e.getLocalizedMessage(), FacesMessage.SEVERITY_ERROR);
		}else if( e.getCause() != null ){
			addError(e.getCause());
		}else{
			addMessage(e.getLocalizedMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public FacesMessage getMessage(String msg, Severity severity) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(severity);
		message.setSummary(translate(msg));
		return message;
	}

	public String translate(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "bundle");
		try {
				
			if(bundle != null && bundle.containsKey(key) ){
				return bundle.getString(key);
			}else{
				return key;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return key;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public UserSessionMB getUserSessionMB() {
		return userSessionMB;
	}

	public void setUserSessionMB(UserSessionMB userSessionMB) {
		this.userSessionMB = userSessionMB;
	}
}
