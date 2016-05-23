package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bairro database table.
 * 
 */
@Entity
@Table(name="bairro")
@NamedQuery(name="Bairro.findAll", query="SELECT b FROM Bairro b")
public class Bairro implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;

	public Bairro() {
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

}