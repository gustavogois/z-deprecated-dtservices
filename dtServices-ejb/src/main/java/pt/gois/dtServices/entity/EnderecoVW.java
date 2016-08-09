package pt.gois.dtServices.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the localidade database table.
 * 
 */
@Entity
@Table(name = "AddressVW")
@NamedQuery(name = "AddressVW.findAll", query = "SELECT l FROM EnderecoVW l")
public class EnderecoVW extends Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer id;
	String completo;

	public EnderecoVW() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCompleto() {
		return completo;
	}

	public void setCompleto(String completo) {
		this.completo = completo;
	}
	
	public String toString(){
		return id != null? id.toString(): null;
	}

}