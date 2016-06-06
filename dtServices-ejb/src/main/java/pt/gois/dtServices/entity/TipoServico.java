package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;


/**
 * The persistent class for the tipoServico database table.
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
	private List<TipoServicoSolicitante> tipoServicoSolicitantes;

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
	@OneToMany(mappedBy="tipoServico")
	public List<TipoServicoSolicitante> getTipoServicoSolicitantes() {
		return this.tipoServicoSolicitantes;
	}

	public void setTipoServicoSolicitantes(List<TipoServicoSolicitante> tipoServicoSolicitantes) {
		this.tipoServicoSolicitantes = tipoServicoSolicitantes;
	}

	public TipoServicoSolicitante addTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		getTipoServicoSolicitantes().add(tipoServicoSolicitante);
		tipoServicoSolicitante.setTipoServico(this);

		return tipoServicoSolicitante;
	}

	public TipoServicoSolicitante removeTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		getTipoServicoSolicitantes().remove(tipoServicoSolicitante);
		tipoServicoSolicitante.setTipoServico(null);

		return tipoServicoSolicitante;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoServico other = (TipoServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}