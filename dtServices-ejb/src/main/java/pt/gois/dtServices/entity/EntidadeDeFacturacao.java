package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the entidadedefacturacao database table.
 * 
 */
@Entity
@NamedQuery(name="EntidadeDeFacturacao.findAll", query="SELECT e FROM EntidadeDeFacturacao e")
public class EntidadeDeFacturacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private String nif;
	private Solicitante solicitante;
	
	public EntidadeDeFacturacao() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNif() {
		return nif;
	}


	public void setNif(String nif) {
		this.nif = nif;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="solicitanteId")
	public Solicitante getSolicitante() {
		return solicitante;
	}


	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

}