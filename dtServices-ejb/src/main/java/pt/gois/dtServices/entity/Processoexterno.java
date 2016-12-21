package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the processoexterno database table.
 * 
 */
@Entity
@NamedQuery(name="ProcessoExterno.findAll", query="SELECT p FROM ProcessoExterno p")
public class ProcessoExterno implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Date updateDt;
	private String updateUser;
	private Imovel imovel;
	private Solicitante solicitante;
	private List<ProcessoInterno> processointernos;

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


	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}


	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	//bi-directional one-to-one association to Imovel
	@OneToOne(mappedBy="processoExterno", fetch=FetchType.LAZY)
	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
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


	//bi-directional many-to-one association to ProcessoInterno
	@OneToMany(mappedBy="processoExterno")
	public List<ProcessoInterno> getProcessointernos() {
		return this.processointernos;
	}

	public void setProcessointernos(List<ProcessoInterno> processointernos) {
		this.processointernos = processointernos;
	}

	public ProcessoInterno addProcessointerno(ProcessoInterno processointerno) {
		getProcessointernos().add(processointerno);
		processointerno.setProcessoExterno(this);

		return processointerno;
	}

	public ProcessoInterno removeProcessointerno(ProcessoInterno processointerno) {
		getProcessointernos().remove(processointerno);
		processointerno.setProcessoExterno(null);

		return processointerno;
	}

}