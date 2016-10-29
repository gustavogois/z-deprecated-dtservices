package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_estado_processo database table.
 * 
 */
@Entity
@Table(name="tbl_estado_processo")
@NamedQuery(name="EstadoProcesso.findAll", query="SELECT e FROM EstadoProcesso e")
public class EstadoProcesso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtFim;
	private Date dtInicio;
	private String observacoes;
	private ProcessoInterno tblProcessoInterno;
	private TipoDeEstadoPI tblTipoDeEstadopi;

	public EstadoProcesso() {
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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processoId")
	public ProcessoInterno getTblProcessoInterno() {
		return this.tblProcessoInterno;
	}

	public void setTblProcessoInterno(ProcessoInterno tblProcessoInterno) {
		this.tblProcessoInterno = tblProcessoInterno;
	}


	//bi-directional many-to-one association to TipoDeEstadoPI
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoId")
	public TipoDeEstadoPI getTblTipoDeEstadopi() {
		return this.tblTipoDeEstadopi;
	}

	public void setTblTipoDeEstadopi(TipoDeEstadoPI tblTipoDeEstadopi) {
		this.tblTipoDeEstadopi = tblTipoDeEstadopi;
	}

}