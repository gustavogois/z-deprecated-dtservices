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
 * The persistent class for the solicitante database table.
 * 
 */
@Entity
@NamedQuery(name="Solicitante.findAll", query="SELECT s FROM Solicitante s")
public class Solicitante extends Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nif;
	private String nome;
	private String telefone;
	private List<ProcessoExterno> processoExterno;
	private List<TipoServicoSolicitante> tipoServicoSolicitantes;
	private List<EntidadeDeFacturacao> entidadeDeFacturacao;

	public Solicitante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	//bi-directional many-to-one association to Processocliente
	@OneToMany(mappedBy="solicitante")
	public List<ProcessoExterno> getProcessoExterno() {
		return this.processoExterno;
	}

	public void setProcessoExterno(List<ProcessoExterno> processoExterno) {
		this.processoExterno = processoExterno;
	}

	public ProcessoExterno addProcessoExterno(ProcessoExterno processoExterno) {
		getProcessoExterno().add(processoExterno);
		processoExterno.setSolicitante(this);

		return processoExterno;
	}

	public ProcessoExterno removeProcessoExterno(ProcessoExterno processoExterno) {
		getProcessoExterno().remove(processoExterno);
		processoExterno.setSolicitante(null);

		return processoExterno;
	}


	//bi-directional many-to-one association to TipoServicoSolicitante
	@OneToMany(mappedBy="solicitante")
	public List<TipoServicoSolicitante> getTipoServicoSolicitantes() {
		return this.tipoServicoSolicitantes;
	}

	public void setTipoServicoSolicitantes(List<TipoServicoSolicitante> tipoServicoSolicitantes) {
		this.tipoServicoSolicitantes = tipoServicoSolicitantes;
	}

	public TipoServicoSolicitante addTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		getTipoServicoSolicitantes().add(tipoServicoSolicitante);
		tipoServicoSolicitante.setSolicitante(this);

		return tipoServicoSolicitante;
	}

	public TipoServicoSolicitante removeTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		getTipoServicoSolicitantes().remove(tipoServicoSolicitante);
		tipoServicoSolicitante.setSolicitante(null);

		return tipoServicoSolicitante;
	}
	
	@OneToMany(mappedBy="solicitante")
	public List<EntidadeDeFacturacao> getEntidadeDeFacturacao() {
		return entidadeDeFacturacao;
	}


	public void setEntidadeDeFacturacao(List<EntidadeDeFacturacao> entidadeDeFacturacao) {
		this.entidadeDeFacturacao = entidadeDeFacturacao;
	}

}