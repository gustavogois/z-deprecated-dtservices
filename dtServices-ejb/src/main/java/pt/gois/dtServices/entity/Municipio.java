package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the municipio database table.
 * 
 */
@Entity
@Table(name="municipio")
@NamedQuery(name="Municipio.findAll", query="SELECT m FROM Municipio m")
public class Municipio implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private List<Concelho> concelhos;
	private List<Freguesia> freguesias;
	private Distrito distrito;

	public Municipio() {
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


	//bi-directional many-to-one association to Concelho
	@OneToMany(mappedBy="municipio")
	public List<Concelho> getConcelhos() {
		return this.concelhos;
	}

	public void setConcelhos(List<Concelho> concelhos) {
		this.concelhos = concelhos;
	}

	public Concelho addConcelho(Concelho concelho) {
		getConcelhos().add(concelho);
		concelho.setMunicipio(this);

		return concelho;
	}

	public Concelho removeConcelho(Concelho concelho) {
		getConcelhos().remove(concelho);
		concelho.setMunicipio(null);

		return concelho;
	}


	//bi-directional many-to-one association to Freguesia
	@OneToMany(mappedBy="municipio")
	public List<Freguesia> getFreguesias() {
		return this.freguesias;
	}

	public void setFreguesias(List<Freguesia> freguesias) {
		this.freguesias = freguesias;
	}

	public Freguesia addFreguesia(Freguesia freguesia) {
		getFreguesias().add(freguesia);
		freguesia.setMunicipio(this);

		return freguesia;
	}

	public Freguesia removeFreguesia(Freguesia freguesia) {
		getFreguesias().remove(freguesia);
		freguesia.setMunicipio(null);

		return freguesia;
	}


	//bi-directional many-to-one association to Distrito
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idDistrito", nullable=false)
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

}