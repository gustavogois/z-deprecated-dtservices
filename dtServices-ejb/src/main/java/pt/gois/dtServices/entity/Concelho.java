package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the concelho database table.
 * 
 */
@Entity
@NamedQuery(name="Concelho.findAll", query="SELECT c FROM Concelho c")
public class Concelho implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private Distrito distrito;
	private List<Localidade> localidades;

	public Concelho() {
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


	//bi-directional many-to-one association to Distrito
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="distritoId")
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}


	//bi-directional many-to-one association to Localidade
	@OneToMany(mappedBy="concelho")
	public List<Localidade> getLocalidades() {
		return this.localidades;
	}

	public void setLocalidades(List<Localidade> localidades) {
		this.localidades = localidades;
	}

	public Localidade addLocalidade(Localidade localidade) {
		getLocalidades().add(localidade);
		localidade.setConcelho(this);

		return localidade;
	}

	public Localidade removeLocalidade(Localidade localidade) {
		getLocalidades().remove(localidade);
		localidade.setConcelho(null);

		return localidade;
	}

}