package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the processo database table.
 * 
 */
@Entity
@NamedQuery(name="Processo.findAll", query="SELECT p FROM Processo p")
public class Processo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte comChaves;
	private Date dtCadastro;
	private Date dtFaturamento;
	private Date dtFinalizacao;
	private Date dtInicioExecucao;
	private Date dtRecebimento;
	private Date dtSolicitacao;
	private int estado;
	private String observacoes;
	private Entidadedefacturacao entidadedefacturacao;
	private Imovel imovel;
	private Orcamento orcamento;
	private Solicitante solicitante;
	private List<Servico> servicos;

	public Processo() {
	}


	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public byte getComChaves() {
		return this.comChaves;
	}

	public void setComChaves(byte comChaves) {
		this.comChaves = comChaves;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtFaturamento() {
		return this.dtFaturamento;
	}

	public void setDtFaturamento(Date dtFaturamento) {
		this.dtFaturamento = dtFaturamento;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtFinalizacao() {
		return this.dtFinalizacao;
	}

	public void setDtFinalizacao(Date dtFinalizacao) {
		this.dtFinalizacao = dtFinalizacao;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtInicioExecucao() {
		return this.dtInicioExecucao;
	}

	public void setDtInicioExecucao(Date dtInicioExecucao) {
		this.dtInicioExecucao = dtInicioExecucao;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtRecebimento() {
		return this.dtRecebimento;
	}

	public void setDtRecebimento(Date dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtSolicitacao() {
		return this.dtSolicitacao;
	}

	public void setDtSolicitacao(Date dtSolicitacao) {
		this.dtSolicitacao = dtSolicitacao;
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


	//bi-directional many-to-one association to Imovel
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="imovelId")
	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	//bi-directional many-to-one association to Orcamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orcamentoId")
	public Orcamento getOrcamento() {
		return this.orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}


	//bi-directional many-to-one association to Solicitante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitanteId")
	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
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