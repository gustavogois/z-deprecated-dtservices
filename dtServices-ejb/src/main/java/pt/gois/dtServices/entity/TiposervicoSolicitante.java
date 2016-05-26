package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tiposervico_solicitante database table.
 * 
 */
@Entity
@Table(name="tiposervico_solicitante")
@NamedQuery(name="TiposervicoSolicitante.findAll", query="SELECT t FROM TiposervicoSolicitante t")
public class TiposervicoSolicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private double valor;
	private Servico servico;
	private Solicitante solicitante;
	private Tiposervico tiposervico;

	public TiposervicoSolicitante() {
	}


	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	//bi-directional one-to-one association to Servico
	@OneToOne(mappedBy="tiposervicoSolicitante", fetch=FetchType.LAZY)
	public Servico getServico() {
		return this.servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}


	//bi-directional many-to-one association to Solicitante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitanteId")
	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}


	//bi-directional many-to-one association to Tiposervico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoServicoId")
	public Tiposervico getTiposervico() {
		return this.tiposervico;
	}

	public void setTiposervico(Tiposervico tiposervico) {
		this.tiposervico = tiposervico;
	}

}