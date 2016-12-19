package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the estadosservico database table.
 * 
 */
@Entity
@NamedQuery(name="EstadosServico.findAll", query="SELECT e FROM EstadosServico e")
public class EstadosServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtFim;
	private Date dtInicio;
	private String observacoes;
	private Servico servico;
	private TipoDeEstado tiposDeEstado;

	public EstadosServico() {
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
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="servicoId")
	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}


	//bi-directional many-to-one association to TipoDeEstado
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoId")
	public TipoDeEstado getTipoDeEstado() {
		return this.tiposDeEstado;
	}

	public void setTipoDeEstado(TipoDeEstado tiposDeEstado) {
		this.tiposDeEstado = tiposDeEstado;
	}

}