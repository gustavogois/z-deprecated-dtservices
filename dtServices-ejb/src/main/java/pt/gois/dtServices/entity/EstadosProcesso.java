package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the estadosprocesso database table.
 * 
 */
@Entity
@NamedQuery(name="EstadosProcesso.findAll", query="SELECT e FROM EstadosProcesso e")
public class EstadosProcesso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtFim;
	private Date dtInicio;
	private String observacoes;
	private ProcessoInterno processoInterno;
	private TiposDeEstado tiposDeEstado;

	public EstadosProcesso() {
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
	public Date getDtFim() {
		return this.dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtInicio() {
		return this.dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}


	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	//bi-directional many-to-one association to ProcessoInterno
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="processoId")
	public ProcessoInterno getProcessoInterno() {
		return this.processoInterno;
	}

	public void setProcessoInterno(ProcessoInterno processoInterno) {
		this.processoInterno = processoInterno;
	}


	//bi-directional many-to-one association to TiposDeEstado
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoId")
	public TiposDeEstado getTiposDeEstado() {
		return this.tiposDeEstado;
	}

	public void setTiposDeEstado(TiposDeEstado tiposDeEstado) {
		this.tiposDeEstado = tiposDeEstado;
	}

}