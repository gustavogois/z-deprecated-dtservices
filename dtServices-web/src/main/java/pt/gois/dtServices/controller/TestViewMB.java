package pt.gois.dtServices.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TestViewMB implements Serializable {
	private static final long serialVersionUID = 1L;

	int id;
	int idInterno;
	
	public void grava() {
		
		idInterno++;
		
		System.out.println("Novo id gravado: " + id);
		System.out.println("Id interno: " + idInterno);
		
	}
	
	public String redireciona(int x) {
		if(x == 0) {
			return "outpage";
		} else if (x == 1) {
			return "testeview";
		}
		return "";
	}
	
	@PostConstruct
	public void inicializa() {
		System.out.println(" ######### Opa, fui criado!");
	}
	
	@PreDestroy
	public void shutdown() {
		System.out.println(" +++++++ Adeus ...");
	}
	 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
