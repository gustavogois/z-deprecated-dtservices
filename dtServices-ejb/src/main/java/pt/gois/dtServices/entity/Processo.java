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
 * The persistent class for the processo database table.
 * 
 */
@Entity
@NamedQuery(name="Processo.findAll", query="SELECT p FROM Processo p")
public class Processo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private byte comChaves;
	private int estado;
	private String observacoes;
	private EntidadeDeFacturacao entidadeDeFacturacao;
	private TiposDeEstado tiposDeEstado;
	private Processocliente processocliente;
	private List<Servico> servicos;

	public Processo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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


	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}


	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	//bi-directional many-to-one association to EntidadeDeFacturacao
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="entidadeFacturacaoId")
	public EntidadeDeFacturacao getEntidadeDeFacturacao() {
		return this.entidadeDeFacturacao;
	}

	public void setEntidadeDeFacturacao(EntidadeDeFacturacao entidadeDeFacturacao) {
		this.entidadeDeFacturacao = entidadeDeFacturacao;
	}


	//bi-directional many-to-one association to TiposDeEstado
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="estado_atual_Id")
	public TiposDeEstado getTiposDeEstado() {
		return this.tiposDeEstado;
	}

	public void setTiposDeEstado(TiposDeEstado tiposDeEstado) {
		this.tiposDeEstado = tiposDeEstado;
	}


	//bi-directional many-to-one association to Processocliente
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="processoClienteId")
	public Processocliente getProcessocliente() {
		return this.processocliente;
	}

	public void setProcessocliente(Processocliente processocliente) {
		this.processocliente = processocliente;
	}


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="processo")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Servico addServico(Servico servico) {
		getServicos().add(servico);
		servico.setProcesso(this);

		return servico;
	}

	public Servico removeServico(Servico servico) {
		getServicos().remove(servico);
		servico.setProcesso(null);

		return servico;
	}

}