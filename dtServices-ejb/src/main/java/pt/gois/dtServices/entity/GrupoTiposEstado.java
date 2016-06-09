package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupo_tipos_estado database table.
 * 
 */
@Entity
@Table(name="grupo_tipos_estado")
@NamedQuery(name="GrupoTiposEstado.findAll", query="SELECT g FROM GrupoTiposEstado g")
public class GrupoTiposEstado implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private List<Historico> historicos;
	private List<TiposDeEstado> tiposDeEstados;

	public GrupoTiposEstado() {
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


	//bi-directional many-to-one association to Historico
	@OneToMany(mappedBy="grupoTiposEstado")
	public List<Historico> getHistoricos() {
		return this.historicos;
	}

	public void setHistoricos(List<Historico> historicos) {
		this.historicos = historicos;
	}

	public Historico addHistorico(Historico historico) {
		getHistoricos().add(historico);
		historico.setGrupoTiposEstado(this);

		return historico;
	}

	public Historico removeHistorico(Historico historico) {
		getHistoricos().remove(historico);
		historico.setGrupoTiposEstado(null);

		return historico;
	}


	//bi-directional many-to-one association to TiposDeEstado
	@OneToMany(mappedBy="grupoTiposEstado")
	public List<TiposDeEstado> getTiposDeEstados() {
		return this.tiposDeEstados;
	}

	public void setTiposDeEstados(List<TiposDeEstado> tiposDeEstados) {
		this.tiposDeEstados = tiposDeEstados;
	}

	public TiposDeEstado addTiposDeEstado(TiposDeEstado tiposDeEstado) {
		getTiposDeEstados().add(tiposDeEstado);
		tiposDeEstado.setGrupoTiposEstado(this);

		return tiposDeEstado;
	}

	public TiposDeEstado removeTiposDeEstado(TiposDeEstado tiposDeEstado) {
		getTiposDeEstados().remove(tiposDeEstado);
		tiposDeEstado.setGrupoTiposEstado(null);

		return tiposDeEstado;
	}

}