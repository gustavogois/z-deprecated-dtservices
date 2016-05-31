package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TipoServico database table.
 * 
 */
@Entity
@NamedQuery(name="TipoServico.findAll", query="SELECT t FROM TipoServico t")
public class TipoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private String nome;
	private double valor;
	private List<TipoServicoSolicitante> tiposervicoSolicitantes;

	public TipoServico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	//bi-directional many-to-one association to TipoServicoSolicitante
	@OneToMany(mappedBy="tiposervico")
	public List<TipoServicoSolicitante> getTiposervicoSolicitantes() {
		return this.tiposervicoSolicitantes;
	}

	public void setTiposervicoSolicitantes(List<TipoServicoSolicitante> tiposervicoSolicitantes) {
		this.tiposervicoSolicitantes = tiposervicoSolicitantes;
	}

	public TipoServicoSolicitante addTiposervicoSolicitante(TipoServicoSolicitante tiposervicoSolicitante) {
		getTiposervicoSolicitantes().add(tiposervicoSolicitante);
		tiposervicoSolicitante.setTiposervico(this);

		return tiposervicoSolicitante;
	}

	public TipoServicoSolicitante removeTiposervicoSolicitante(TipoServicoSolicitante tiposervicoSolicitante) {
		getTiposervicoSolicitantes().remove(tiposervicoSolicitante);
		tiposervicoSolicitante.setTiposervico(null);

		return tiposervicoSolicitante;
	}

}