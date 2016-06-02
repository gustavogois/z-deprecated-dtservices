package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipoServico_solicitante database table.
 * 
 */
@Entity
@Table(name="tipoServico_solicitante")
@NamedQuery(name="TipoServicoSolicitante.findAll", query="SELECT t FROM TipoServicoSolicitante t")
public class TipoServicoSolicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private double valor;
	private List<Servico> servicos;
	private Solicitante solicitante;
	private TipoServico tipoServico;

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


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tipoServicoSolicitante")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setTipoServicoSolicitante(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setTipoServicoSolicitante(null);

		return servico;
	}


	//bi-directional many-to-one association to Solicitante
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="solicitanteId")
	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}


	//bi-directional many-to-one association to TipoServico
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tipoServicoId")
	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

}