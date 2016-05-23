package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the concelho database table.
 * 
 */
@Entity
@Table(name="concelho")
@NamedQuery(name="Concelho.findAll", query="SELECT c FROM Concelho c")
public class Concelho implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private Municipio municipio;

	public Concelho() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-one association to Municipio
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idMunicipio", nullable=false)
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

}