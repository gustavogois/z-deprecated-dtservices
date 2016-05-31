package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	private List<Servico> servico;
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
	@OneToMany(mappedBy="tiposervicoSolicitante")
	public List<Servico> getServico() {
		return this.servico;
	}

	public void setServico(List<Servico> servico) {
		this.servico = servico;
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
	public TipoServico getTiposervico() {
		return this.tiposervico;
	}

	public void setTiposervico(TipoServico tiposervico) {
		this.tiposervico = tiposervico;
	}

}