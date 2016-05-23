package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the solicitante database table.
 * 
 */
@Entity
@Table(name="solicitante")
@NamedQuery(name="Solicitante.findAll", query="SELECT s FROM Solicitante s")
public class Solicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nif;
	private String nome;
	private String telefone;
	private List<Processo> processos;
	private List<Servico> servicos;

	public Solicitante() {
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


	@Column(length=10)
	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}


	@Column(length=30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(length=10)
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="solicitante")
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setSolicitante(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setSolicitante(null);

		return processo;
	}


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="solicitante")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setSolicitante(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setSolicitante(null);

		return servico;
	}

}