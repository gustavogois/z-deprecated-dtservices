package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dt_services_cp.ConcelhoVW")
@NamedQueries({ @NamedQuery(name = "Concelho.findAll", query = "SELECT c FROM Concelho c order by c.nome"),
		@NamedQuery(name = "Concelho.findByDistrito", query = "SELECT c FROM Concelho c where c.distrito.id = :distritoId order by c.nome") })
public class Concelho implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String nome;
	private Distrito distrito;
	private List<EnderecoVW> enderecos;

	public Concelho() {
	}

	@Id
	@Column(name = "cc")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	// bi-directional many-to-one association to Distrito
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dd")
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	// bi-directional many-to-one association to Localidade
	@OneToMany(mappedBy = "concelho")
	public List<EnderecoVW> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<EnderecoVW> enderecos) {
		this.enderecos = enderecos;
	}

}
