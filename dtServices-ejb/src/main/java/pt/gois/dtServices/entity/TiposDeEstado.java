package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	private List<EstadosProcesso> estadosProcesso;
	private List<EstadosServico> estadosServico;
	private GrupoTiposEstado grupoTiposEstado;

	public TiposDeEstado() {
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


	//bi-directional many-to-one association to EstadosProcesso
	@OneToMany(mappedBy="tiposDeEstado")
	public List<EstadosProcesso> getEstadosProcesso() {
		return this.estadosProcesso;
	}

	public void setEstadosProcesso(List<EstadosProcesso> estadosprocessos) {
		this.estadosProcesso = estadosprocessos;
	}

	//bi-directional many-to-one association to EstadosServico
	@OneToMany(mappedBy="tiposDeEstado")
	public List<EstadosServico> getEstadosServico() {
		return this.estadosServico;
	}

	public void setEstadosServico(List<EstadosServico> estadosservicos) {
		this.estadosServico = estadosservicos;
	}

	//bi-directional many-to-one association to GrupoTiposEstado
	@ManyToOne
	@JoinColumn(name="grupo_estadoId")
	public GrupoTiposEstado getGrupoTiposEstado() {
		return this.grupoTiposEstado;
	}

	public void setGrupoTiposEstado(GrupoTiposEstado grupoTiposEstado) {
		this.grupoTiposEstado = grupoTiposEstado;
	}

}