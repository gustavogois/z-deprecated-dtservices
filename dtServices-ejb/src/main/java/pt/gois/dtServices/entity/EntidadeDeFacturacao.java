package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the entidadedefacturacao database table.
 * 
 */
@Entity
@NamedQuery(name="EntidadeDeFacturacao.findAll", query="SELECT e FROM EntidadeDeFacturacao e")
public class EntidadeDeFacturacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private List<ProcessoInterno> processosInternos;

	public EntidadeDeFacturacao() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


	//bi-directional many-to-one association to ProcessoInterno
	@OneToMany(mappedBy="entidadeDeFacturacao")
	public List<ProcessoInterno> getProcessosInternos() {
		return this.processosInternos;
	}

	public void setProcessosInternos(List<ProcessoInterno> processosInternos) {
		this.processosInternos = processosInternos;
	}

	public ProcessoInterno addProcesso(ProcessoInterno processo) {
		getProcessosInternos().add(processo);
		processo.setEntidadeDeFacturacao(this);

		return processo;
	}

	public ProcessoInterno removeProcessoInterno(ProcessoInterno processoInterno) {
		getProcessosInternos().remove(processoInterno);
		processoInterno.setEntidadeDeFacturacao(null);

		return processoInterno;
	}

}