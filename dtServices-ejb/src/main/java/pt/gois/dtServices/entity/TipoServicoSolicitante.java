package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tiposervico_solicitante database table.
 * 
 */
@Entity
@Table(name="tiposervico_solicitante")
@NamedQuery(name="TiposervicoSolicitante.findAll", query="SELECT t FROM TiposervicoSolicitante t")
public class TiposervicoSolicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double valor;
	private List<Servico> servicos;
	private Solicitante solicitante;
	private Tiposervico tiposervico;

	public TiposervicoSolicitante() {
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


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tiposervicoSolicitante")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setTiposervicoSolicitante(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setTiposervicoSolicitante(null);

		return servico;
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