package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the t_estado_servico database table.
 * 
 */
@Entity
@Table(name="t_estado_servico")
@NamedQuery(name="EstadoServico.findAll", query="SELECT t FROM EstadoServico t")
public class EstadoServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataInicio;

	private String observacoes;

	//bi-directional many-to-one association to TipoServico
	@ManyToOne
	@JoinColumn(name="tipoEstadoId")
	private TipoEstado tipoEstado;

	//bi-directional many-to-one association to Servico
	@ManyToOne
	@JoinColumn(name="servicoId")
	private Servico servico;
	
	private Integer userId;


	public EstadoServico() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataFim() {
		return this.dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Date getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public TipoEstado getTipoEstado() {
		return this.tipoEstado;
	}

	public void setTipoEstado(TipoEstado tipoEstado) {
		this.tipoEstado = tipoEstado;
	}

	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Integer getUser() {
		return userId;
	}

	public void setUser(Integer userId) {
		this.userId = userId;
	}

}