package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private Entidadedefacturacao entidadedefacturacao;
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


	//bi-directional many-to-one association to Entidadedefacturacao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="entidadeFacturacaoId")
	public Entidadedefacturacao getEntidadedefacturacao() {
		return this.entidadedefacturacao;
	}

	public void setEntidadedefacturacao(Entidadedefacturacao entidadedefacturacao) {
		this.entidadedefacturacao = entidadedefacturacao;
	}


	//bi-directional many-to-one association to TiposDeEstado
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="estado_atual_Id")
	public TiposDeEstado getTiposDeEstado() {
		return this.tiposDeEstado;
	}

	public void setTiposDeEstado(TiposDeEstado tiposDeEstado) {
		this.tiposDeEstado = tiposDeEstado;
	}


	//bi-directional many-to-one association to Processocliente
	@ManyToOne(fetch=FetchType.LAZY)
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