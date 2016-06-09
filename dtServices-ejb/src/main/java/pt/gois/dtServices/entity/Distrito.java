package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the distrito database table.
 * 
 */
@Entity
@NamedQuery(name="Distrito.findAll", query="SELECT d FROM Distrito d")
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private List<Concelho> concelhos;
	private List<Localidade> localidades;

	public Distrito() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-one association to Concelho
	@OneToMany(mappedBy="distrito")
	public List<Concelho> getConcelhos() {
		return this.concelhos;
	}

	public void setConcelhos(List<Concelho> concelhos) {
		this.concelhos = concelhos;
	}

	public Concelho addConcelho(Concelho concelho) {
		getConcelhos().add(concelho);
		concelho.setDistrito(this);

		return concelho;
	}

	public Concelho removeConcelho(Concelho concelho) {
		getConcelhos().remove(concelho);
		concelho.setDistrito(null);

		return concelho;
	}


	//bi-directional many-to-one association to Localidade
	@OneToMany(mappedBy="distrito")
	public List<Localidade> getLocalidades() {
		return this.localidades;
	}

	public void setLocalidades(List<Localidade> localidades) {
		this.localidades = localidades;
	}

	public Localidade addLocalidade(Localidade localidade) {
		getLocalidades().add(localidade);
		localidade.setDistrito(this);

		return localidade;
	}

	public Localidade removeLocalidade(Localidade localidade) {
		getLocalidades().remove(localidade);
		localidade.setDistrito(null);

		return localidade;
	}

}