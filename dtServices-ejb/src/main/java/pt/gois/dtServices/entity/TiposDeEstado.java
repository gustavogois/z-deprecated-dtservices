package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_de_estado database table.
 * 
 */
@Entity
@Table(name="tipos_de_estado")
@NamedQuery(name="TiposDeEstado.findAll", query="SELECT t FROM TiposDeEstado t")
public class TiposDeEstado implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private List<Historico> historicos;
	private List<Processo> processos;
	private List<Processocliente> processoclientes;
	private List<Servico> servicos;
	private GrupoTiposEstado grupoTiposEstado;

	public TiposDeEstado() {
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


	//bi-directional many-to-one association to Historico
	@OneToMany(mappedBy="tiposDeEstado")
	public List<Historico> getHistoricos() {
		return this.historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

	public Historico addHistorico(Historico historico) {
		getHistoricos().add(historico);
		historico.setTiposDeEstado(this);

		return historico;
	}

	public Historico removeHistorico(Historico historico) {
		getHistoricos().remove(historico);
		historico.setTiposDeEstado(null);

		return historico;
	}


	//bi-directional many-to-one association to Processo
	@OneToMany(mappedBy="tiposDeEstado")
	public List<Processo> getProcessos() {
		return this.processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public Processo addProcesso(Processo processo) {
		getProcessos().add(processo);
		processo.setTiposDeEstado(this);

		return processo;
	}

	public Processo removeProcesso(Processo processo) {
		getProcessos().remove(processo);
		processo.setTiposDeEstado(null);

		return processo;
	}


	//bi-directional many-to-one association to Processocliente
	@OneToMany(mappedBy="tiposDeEstado")
	public List<Processocliente> getProcessoclientes() {
		return this.processoclientes;
	}

	public void setProcessoclientes(List<Processocliente> processoclientes) {
		this.processoclientes = processoclientes;
	}

	public Processocliente addProcessocliente(Processocliente processocliente) {
		getProcessoclientes().add(processocliente);
		processocliente.setTiposDeEstado(this);

		return processocliente;
	}

	public Processocliente removeProcessocliente(Processocliente processocliente) {
		getProcessoclientes().remove(processocliente);
		processocliente.setTiposDeEstado(null);

		return processocliente;
	}


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tiposDeEstado")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setTiposDeEstado(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setTiposDeEstado(null);

		return servico;
	}


	//bi-directional many-to-one association to GrupoTiposEstado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="grupo_estadoId")
	public GrupoTiposEstado getGrupoTiposEstado() {
		return this.grupoTiposEstado;
	}

	public void setGrupoTiposEstado(GrupoTiposEstado grupoTiposEstado) {
		this.grupoTiposEstado = grupoTiposEstado;
	}

}