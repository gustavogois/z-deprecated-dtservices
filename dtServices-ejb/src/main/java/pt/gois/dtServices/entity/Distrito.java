package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the distrito database table.
 * 
 */
@Entity
@Table(name = "dt_services_cp.Distrito")
@NamedQuery(name="Distrito.findAll", query="SELECT d FROM Distrito d order by d.nome")
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private List<Concelho> concelhos;
	private List<EnderecoVW> enderecos;

	public Distrito() {
	}


	@Id
	@Column(name="dd")
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


	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="distrito")
	public List<EnderecoVW> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<EnderecoVW> enderecos) {
		this.enderecos = enderecos;
	}
}
