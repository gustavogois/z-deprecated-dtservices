package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ProcessoInterno extends GeneralEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean comChaves;
	private String observacoes;
	private ProcessoExterno processoExterno;
	private List<Servico> servicos;
	private String fatura;
	private String numeroChave;
	private String nomeSolicitante;
	private String idProcCliente;
	private Date previsaoFim;
	private Date previsaoInicio;
	private List<EstadosProcesso> estadosProcesso;

	public ProcessoInterno() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public EstadosProcesso retornaEstadoAtual() {
		List<EstadosProcesso> estadosProcessoList = this.getEstadosProcesso();
		if(estadosProcessoList != null && estadosProcessoList.size() > 0) {
			return estadosProcessoList.get(estadosProcessoList.size() - 1);
		} else {
			return null;
		}
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


	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	//bi-directional many-to-one association to Processoexterno
	@ManyToOne(fetch=FetchType.EAGER)
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
	public EstadosProcesso addEstadosProcesso(EstadosProcesso estado) {
		getEstadosProcesso().add(estado);
		estado.setProcessoInterno(this);

		return estado;
	}

	public EstadosProcesso removeEstadosProcesso(EstadosProcesso estado) {
		getEstadosProcesso().remove(estado);
		estado.setProcessoInterno(null);

		return estado;
	}
	public String getFatura() {
		return fatura;
	}


	public void setFatura(String fatura) {
		this.fatura = fatura;
	}


	public String getNumeroChave() {
		return numeroChave;
	}


	public void setNumeroChave(String numeroChave) {
		this.numeroChave = numeroChave;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}


	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public String getIdProcCliente() {
		return idProcCliente;
	}


	public void setIdProcCliente(String idProcCliente) {
		this.idProcCliente = idProcCliente;
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
	//bi-directional many-to-one association to Estadosprocesso
	@OneToMany(mappedBy="processoInterno", cascade = CascadeType.ALL)
	public List<EstadosProcesso> getEstadosProcesso() {
		if( this.estadosProcesso == null ){
			this.estadosProcesso = new ArrayList<EstadosProcesso>();
		}
		return this.estadosProcesso;
	}

	public void setEstadosProcesso(List<EstadosProcesso> estadosProcesso) {
		this.estadosProcesso = estadosProcesso;
	}

}