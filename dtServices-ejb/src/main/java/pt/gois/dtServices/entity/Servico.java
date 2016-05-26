package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtCadastro;
	private Date dtFim;
	private Date dtInicio;
	private int estado;
	private Processo processo;
	private TiposervicoSolicitante tiposervicoSolicitante;

	public Servico() {
	}


	@Id
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
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


	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}


	//bi-directional many-to-one association to Processo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idProcesso")
	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}


	//bi-directional one-to-one association to TiposervicoSolicitante
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	public TiposervicoSolicitante getTiposervicoSolicitante() {
		return this.tiposervicoSolicitante;
	}

	public void setTiposervicoSolicitante(TiposervicoSolicitante tiposervicoSolicitante) {
		this.tiposervicoSolicitante = tiposervicoSolicitante;
	}

}