package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tbl_concelho database table.
 * 
 */
@Entity
@Table(name="tbl_concelho")
@NamedQuery(name="Concelho.findAll", query="SELECT c FROM Concelho c")
public class Concelho implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cc;
	private String dd;
	private String nome;

	public Concelho() {
	}


	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}


	public String getDd() {
		return this.dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}