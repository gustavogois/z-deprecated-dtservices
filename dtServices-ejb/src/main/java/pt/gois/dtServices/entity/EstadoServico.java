package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_estado_servico database table.
 * 
 */
@Entity
@Table(name="tbl_estado_servico")
@NamedQuery(name="EstadoServico.findAll", query="SELECT e FROM EstadoServico e")
public class EstadoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtFim;
	private Date dtInicio;
	private String observacoes;
	private Servico tblServico;
	private TipoDeEstadoSRV tblTipoDeEstadosrv;

	public EstadoServico() {
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


	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="servicoId")
	public Servico getTblServico() {
		return this.tblServico;
	}

	public void setTblServico(Servico tblServico) {
		this.tblServico = tblServico;
	}


	//bi-directional many-to-one association to TipoDeEstadoSRV
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoId")
	public TipoDeEstadoSRV getTblTipoDeEstadosrv() {
		return this.tblTipoDeEstadosrv;
	}

	public void setTblTipoDeEstadosrv(TipoDeEstadoSRV tblTipoDeEstadosrv) {
		this.tblTipoDeEstadosrv = tblTipoDeEstadosrv;
	}

}