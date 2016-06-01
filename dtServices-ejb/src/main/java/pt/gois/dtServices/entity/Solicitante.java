package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the solicitante database table.
 * 
 */
@Entity
@NamedQuery(name="Solicitante.findAll", query="SELECT s FROM Solicitante s")
public class Solicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nif;
	private String nome;
	private String telefone;
	private List<Processocliente> processoclientes;
	private List<TiposervicoSolicitante> tiposervicoSolicitantes;

	public Solicitante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	public List<Processocliente> getProcessoclientes() {
		return this.processoclientes;
	}

	public void setProcessoclientes(List<Processocliente> processoclientes) {
		this.processoclientes = processoclientes;
	}

	public Processocliente addProcessocliente(Processocliente processocliente) {
		getProcessoclientes().add(processocliente);
		processocliente.setSolicitante(this);

		return processocliente;
	}

	public Processocliente removeProcessocliente(Processocliente processocliente) {
		getProcessoclientes().remove(processocliente);
		processocliente.setSolicitante(null);

		return processocliente;
	}


	//bi-directional many-to-one association to TiposervicoSolicitante
	@OneToMany(mappedBy="solicitante")
	public List<TiposervicoSolicitante> getTiposervicoSolicitantes() {
		return this.tiposervicoSolicitantes;
	}

	public void setTiposervicoSolicitantes(List<TiposervicoSolicitante> tiposervicoSolicitantes) {
		this.tiposervicoSolicitantes = tiposervicoSolicitantes;
	}

	public TiposervicoSolicitante addTiposervicoSolicitante(TiposervicoSolicitante tiposervicoSolicitante) {
		getTiposervicoSolicitantes().add(tiposervicoSolicitante);
		tiposervicoSolicitante.setSolicitante(this);

		return tiposervicoSolicitante;
	}

	public TiposervicoSolicitante removeTiposervicoSolicitante(TiposervicoSolicitante tiposervicoSolicitante) {
		getTiposervicoSolicitantes().remove(tiposervicoSolicitante);
		tiposervicoSolicitante.setSolicitante(null);

		return tiposervicoSolicitante;
	}

}