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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the processointerno database table.
 * 
 */
@Entity
@NamedQuery(name="ProcessoInterno.findAll", query="SELECT p FROM ProcessoInterno p")
public class ProcessoInterno implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private boolean comChaves;
	private String fatura;
	private String idProcCliente;
	private String nomeSolicitante;
	private String numeroChave;
	private String observacoes;
	private Date previsaoFim;
	private Date previsaoInicio;
	private Date updateDt;
	private String updateUser;
	private List<EstadosProcesso> estadosProcesso;
	private ProcessoExterno processoExterno;
	private List<Servico> servicos;

	public ProcessoInterno() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public boolean getComChaves() {
		return this.comChaves;
	}

	public void setComChaves(boolean comChaves) {
		this.comChaves = comChaves;
	}


	public String getFatura() {
		return this.fatura;
	}

	public void setFatura(String fatura) {
		this.fatura = fatura;
	}


	public String getIdProcCliente() {
		return this.idProcCliente;
	}

	public void setIdProcCliente(String idProcCliente) {
		this.idProcCliente = idProcCliente;
	}


	public String getNomeSolicitante() {
		return this.nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}


	public String getNumeroChave() {
		return this.numeroChave;
	}

	public void setNumeroChave(String numeroChave) {
		this.numeroChave = numeroChave;
	}


	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	@Temporal(TemporalType.DATE)
	public Date getPrevisaoFim() {
		return this.previsaoFim;
	}

	public void setPrevisaoFim(Date previsaoFim) {
		this.previsaoFim = previsaoFim;
	}


	@Temporal(TemporalType.DATE)
	public Date getPrevisaoInicio() {
		return this.previsaoInicio;
	}

	public void setPrevisaoInicio(Date previsaoInicio) {
		this.previsaoInicio = previsaoInicio;
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


	//bi-directional many-to-one association to EstadosProcesso
	@OneToMany(mappedBy="processoInterno", fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	public List<EstadosProcesso> getEstadosProcesso() {
		return this.estadosProcesso;
	}

	public void setEstadosProcesso(List<EstadosProcesso> estadosprocessos) {
		this.estadosProcesso = estadosprocessos;
	}

	public EstadosProcesso addEstadosprocesso(EstadosProcesso estadosprocesso) {
		getEstadosProcesso().add(estadosprocesso);
		estadosprocesso.setProcessoInterno(this);

		return estadosprocesso;
	}

	public EstadosProcesso removeEstadosprocesso(EstadosProcesso estadosprocesso) {
		getEstadosProcesso().remove(estadosprocesso);
		estadosprocesso.setProcessoInterno(null);

		return estadosprocesso;
	}


	//bi-directional many-to-one association to ProcessoExterno
	@ManyToOne
	@JoinColumn(name="processoExternoId")
	public ProcessoExterno getProcessoExterno() {
		return this.processoExterno;
	}

	public void setProcessoExterno(ProcessoExterno processoExterno) {
		this.processoExterno = processoExterno;
	}


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="processoInterno")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setProcessoInterno(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setProcessoInterno(null);

		return servico;
	}

}