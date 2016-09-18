package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

import pt.gois.dtServices.business.TipoDeEstadoSBLocal;


/**
 * The persistent class for the tipos_de_estado database table.
 * 
 */
@Entity
@Table(name="tipos_de_estado")
@NamedQueries({
@NamedQuery(name="TiposDeEstado.findAll", query="SELECT t FROM TipoDeEstado t"),
@NamedQuery(name = "TiposDeEstado.findTipoDeEstadoByGroup", query = "select t from TipoDeEstado t where t.grupoTiposEstado.id=:pGrupoId")
})
public class TipoDeEstado implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private List<ProcessoInterno> processosInterno;
	private List<ProcessoExterno> processosExterno;
	private List<Servico> servicos;
	private GrupoTiposEstado grupoTiposEstado;

	public TipoDeEstado() {
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
	@OneToMany(mappedBy="tipoDeEstado")
	public List<ProcessoInterno> getProcessosInterno() {
		return this.processosInterno;
	}

	public void setProcessosInterno(List<ProcessoInterno> processosInterno) {
		this.processosInterno = processosInterno;
	}

	public ProcessoInterno addProcessoInterno(ProcessoInterno processoInterno) {
		getProcessosInterno().add(processoInterno);
		processoInterno.setTipoDeEstado(this);

		return processoInterno;
	}

	public ProcessoInterno removeProcessoInterno(ProcessoInterno processoInterno) {
		getProcessosInterno().remove(processoInterno);
		processoInterno.setTipoDeEstado(null);

		return processoInterno;
	}


	public void setProcessosExterno(List<ProcessoExterno> processosExterno) {
		this.processosExterno = processosExterno;
	}

	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tipoDeEstado")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setTipoDeEstado(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setTipoDeEstado(null);

		return servico;
	}


	//bi-directional many-to-one association to GrupoTiposEstado
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grupo_estadoId")
	public GrupoTiposEstado getGrupoTiposEstado() {
		return this.grupoTiposEstado;
	}

	public void setGrupoTiposEstado(GrupoTiposEstado grupoTiposEstado) {
		this.grupoTiposEstado = grupoTiposEstado;
	}

}