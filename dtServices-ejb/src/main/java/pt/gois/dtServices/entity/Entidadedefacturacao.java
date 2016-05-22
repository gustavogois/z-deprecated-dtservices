package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the entidadedefacturacao database table.
 * 
 */
@Entity
@Table(name="entidadedefacturacao")
@NamedQuery(name="Entidadedefacturacao.findAll", query="SELECT e FROM Entidadedefacturacao e")
public class Entidadedefacturacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private List<Processo> processos;

	public Entidadedefacturacao() {
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


	@Column(length=30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="entidadedefacturacao")
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setEntidadedefacturacao(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setEntidadedefacturacao(null);

		return processo;
	}

}