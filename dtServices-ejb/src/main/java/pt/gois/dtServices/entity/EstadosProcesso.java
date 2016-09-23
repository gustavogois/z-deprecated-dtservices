package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	private TipoDeEstado tiposDeEstado;

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


	//bi-directional many-to-one association to Processointerno
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="processoId")
	public ProcessoInterno getProcessoInterno() {
		return this.processoInterno;
	}

	public void setProcessoInterno(ProcessoInterno processoInterno) {
		this.processoInterno = processoInterno;
	}


	//bi-directional many-to-one association to TiposDeEstado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoId")
	public TipoDeEstado getTiposDeEstado() {
		return this.tiposDeEstado;
	}

	public void setTiposDeEstado(TipoDeEstado tiposDeEstado) {
		this.tiposDeEstado = tiposDeEstado;
	}

}