package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the freguesia database table.
 * 
 */
@Entity
@Table(name="freguesia")
@NamedQuery(name="Freguesia.findAll", query="SELECT f FROM Freguesia f")
public class Freguesia implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private List<Endereco> enderecos;

	public Freguesia() {
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


	//bi-directional many-to-one association to Endereco
	@OneToMany(mappedBy="freguesia")
	public List<Endereco> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Endereco addEndereco(Endereco endereco) {
		getEnderecos().add(endereco);
		endereco.setFreguesia(this);

		return endereco;
	}

	public Endereco removeEndereco(Endereco endereco) {
		getEnderecos().remove(endereco);
		endereco.setFreguesia(null);

		return endereco;
	}

}