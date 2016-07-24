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
	private boolean comChaves;
	private String observacoes;
	private EntidadeDeFacturacao entidadeDeFacturacao;
	private TiposDeEstado tiposDeEstado;
	private List<Servico> servicos;

	public Processo() {
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


	//bi-directional many-to-one association to EntidadeDefacturacao
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