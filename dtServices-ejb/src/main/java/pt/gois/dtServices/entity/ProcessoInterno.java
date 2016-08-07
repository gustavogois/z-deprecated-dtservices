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
public class ProcessoInterno implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private byte comChaves;
	private String observacoes;
	private EntidadeDeFacturacao entidadeDeFacturacao;
	private TipoDeEstado tipoDeEstado;
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


	public byte getComChaves() {
		return this.comChaves;
	}

	public void setComChaves(byte comChaves) {
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

}