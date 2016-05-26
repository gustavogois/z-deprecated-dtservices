package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the orcamento database table.
 * 
 */
@Entity
@NamedQuery(name="Orcamento.findAll", query="SELECT o FROM Orcamento o")
public class Orcamento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date dataDeSolicitacao;
	private List<Processo> processos;

	public Orcamento() {
	}


	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getDataDeSolicitacao() {
		return this.dataDeSolicitacao;
	}

	public void setDataDeSolicitacao(Date dataDeSolicitacao) {
		this.dataDeSolicitacao = dataDeSolicitacao;
	}


	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="orcamento")
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setOrcamento(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setOrcamento(null);

		return processo;
	}

}