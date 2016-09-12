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
	private EntidadeDeFacturacao entidadeDeFacturacao;
	private TipoDeEstado tipoDeEstado;
	private ProcessoExterno processoExterno;
	private List<Servico> servicos;
	private String fatura;
	private String numeroChave;
	private String nomeSolicitante;


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


	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	//bi-directional many-to-one association to Entidadedefacturacao
	@ManyToOne
	@JoinColumn(name="entidaDeFacturacaoId")
	public EntidadeDeFacturacao getEntidadeDeFacturacao() {
		return this.entidadeDeFacturacao;
	}

	public void setEntidadeDeFacturacao(EntidadeDeFacturacao entidadeDeFacturacao) {
		this.entidadeDeFacturacao = entidadeDeFacturacao;
	}


	//bi-directional many-to-one association to TiposDeEstado
	@ManyToOne
	@JoinColumn(name="estado_atual_Id")
	public TipoDeEstado getTipoDeEstado() {
		return this.tipoDeEstado;
	}

	public void setTipoDeEstado(TipoDeEstado tipoDeEstado) {
		this.tipoDeEstado = tipoDeEstado;
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

}