package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="EntidadedeFacturacao.findAll", query="SELECT e FROM EntidadeDeFacturacao e")
public class EntidadeDeFacturacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private List<Processo> processos;

	public EntidadeDeFacturacao() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="entidadeDeFacturacao")
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setEntidadeDeFacturacao(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setEntidadeDeFacturacao(null);

		return processo;
	}

}