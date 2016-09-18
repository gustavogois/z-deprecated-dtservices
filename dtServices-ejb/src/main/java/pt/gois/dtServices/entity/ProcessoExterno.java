package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the processoexterno database table.
 * 
 */
@Entity
@NamedQuery(name="ProcessoExterno.findAll", query="SELECT p FROM ProcessoExterno p")
public class ProcessoExterno extends GeneralEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String descricao;
	private Imovel imovel;
	private Solicitante solicitante;
	private List<ProcessoInterno> processosInterno;

	public ProcessoExterno() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


	//bi-directional one-to-one association to Imovel
	@OneToOne(mappedBy="processoExterno", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	@ManyToOne
	@JoinColumn(name="solicitanteId")
	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}


	@OneToMany(mappedBy="processoExterno")
	public List<ProcessoInterno> getProcessosInterno() {
		return this.processosInterno;
	}

	public void setProcessosInterno(List<ProcessoInterno> processosInterno) {
		this.processosInterno = processosInterno;
	}

	public ProcessoInterno addProcessointerno(ProcessoInterno processoInterno) {
		getProcessosInterno().add(processoInterno);
		processoInterno.setProcessoExterno(this);

		return processoInterno;
	}

	public ProcessoInterno removeProcessoInterno(ProcessoInterno processoInterno) {
		getProcessosInterno().remove(processoInterno);
		processoInterno.setProcessoExterno(null);

		return processoInterno;
	}

}