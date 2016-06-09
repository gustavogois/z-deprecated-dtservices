package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historico database table.
 * 
 */
@Entity
@NamedQuery(name="Historico.findAll", query="SELECT h FROM Historico h")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date data;
	private int idObjeto;
	private GrupoTiposEstado grupoTiposEstado;
	private TiposDeEstado tiposDeEstado;

	public Historico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}


	public int getIdObjeto() {
		return this.idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}


	//bi-directional many-to-one association to GrupoTiposEstado
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="grupo_tipos_estadoId")
	public GrupoTiposEstado getGrupoTiposEstado() {
		return this.grupoTiposEstado;
	}

	public void setGrupoTiposEstado(GrupoTiposEstado grupoTiposEstado) {
		this.grupoTiposEstado = grupoTiposEstado;
	}


	//bi-directional many-to-one association to TiposDeEstado
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipos_de_estadoId")
	public TiposDeEstado getTiposDeEstado() {
		return this.tiposDeEstado;
	}

	public void setTiposDeEstado(TiposDeEstado tiposDeEstado) {
		this.tiposDeEstado = tiposDeEstado;
	}

}