package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tiposervico_solicitante database table.
 * 
 */
@Entity
@Table(name="tiposervico_solicitante")
@NamedQuery(name="TipoServicoSolicitante.findAll", query="SELECT t FROM TipoServicoSolicitante t")
public class TipoServicoSolicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double valor;
	private Servico servico;
	private Solicitante solicitante;
	private TipoServico tiposervico;

	public TipoServicoSolicitante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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
	public TipoServico getTiposervico() {
		return this.tiposervico;
	}

	public void setTiposervico(TipoServico tiposervico) {
		this.tiposervico = tiposervico;
	}

}