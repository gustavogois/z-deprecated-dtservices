package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@Table(name="servico")
@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private double valor;
	private List<Processo> processos;
	private Solicitante solicitante;

	public Servico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(length=30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(nullable=false)
	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	//bi-directional many-to-many association to Processo
	@ManyToMany
	@JoinTable(
		name="processoservicos"
		, joinColumns={
			@JoinColumn(name="servicoId", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="processoId", nullable=false)
			}
		)
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}


	//bi-directional many-to-one association to Solicitante
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="solicitanteId", nullable=false)
	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

}