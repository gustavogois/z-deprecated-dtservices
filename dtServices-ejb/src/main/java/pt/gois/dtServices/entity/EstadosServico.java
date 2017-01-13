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
	private TiposDeEstado tiposDeEstado;
	private User user;

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
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="servicoId")
	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
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

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

}