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
@Table(name="processo")
@NamedQuery(name="Processo.findAll", query="SELECT p FROM Processo p")
public class Processo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private byte comChaves;
	private Date dataDeCadastro;
	private Date dataDeFaturamento;
	private Date dataDeFinalizacaoServicos;
	private Date dataDeInicioExecucao;
	private Date dataDeSolicitacao;
	private Date dataDoRecebimento;
	private String observacoes;
	private Entidadedefacturacao entidadedefacturacao;
	private List<Iimagem> iimagems;
	private Imovel imovel;
	private Orcamento orcamento;
	private Solicitante solicitante;
	private List<Servico> servicos;

	public Processo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
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
	public Date getDataDeCadastro() {
		return this.dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}


	@Temporal(TemporalType.DATE)
	public Date getDataDeFaturamento() {
		return this.dataDeFaturamento;
	}

	public void setDataDeFaturamento(Date dataDeFaturamento) {
		this.dataDeFaturamento = dataDeFaturamento;
	}


	@Temporal(TemporalType.DATE)
	public Date getDataDeFinalizacaoServicos() {
		return this.dataDeFinalizacaoServicos;
	}

	public void setDataDeFinalizacaoServicos(Date dataDeFinalizacaoServicos) {
		this.dataDeFinalizacaoServicos = dataDeFinalizacaoServicos;
	}


	@Temporal(TemporalType.DATE)
	public Date getDataDeInicioExecucao() {
		return this.dataDeInicioExecucao;
	}

	public void setDataDeInicioExecucao(Date dataDeInicioExecucao) {
		this.dataDeInicioExecucao = dataDeInicioExecucao;
	}


	@Temporal(TemporalType.DATE)
	public Date getDataDeSolicitacao() {
		return this.dataDeSolicitacao;
	}

	public void setDataDeSolicitacao(Date dataDeSolicitacao) {
		this.dataDeSolicitacao = dataDeSolicitacao;
	}


	@Temporal(TemporalType.DATE)
	public Date getDataDoRecebimento() {
		return this.dataDoRecebimento;
	}

	public void setDataDoRecebimento(Date dataDoRecebimento) {
		this.dataDoRecebimento = dataDoRecebimento;
	}


	@Column(length=60)
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


	//bi-directional many-to-many association to Iimagem
	@ManyToMany
	@JoinTable(
		name="processoimagens"
		, joinColumns={
			@JoinColumn(name="processoId", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="imagemId", nullable=false)
			}
		)
	public List<Iimagem> getIimagems() {
		return this.iimagems;
	}

	public void setIimagems(List<Iimagem> iimagems) {
		this.iimagems = iimagems;
	}


	//bi-directional many-to-one association to Imovel
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="imovelId", nullable=false)
	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}


	//bi-directional many-to-one association to Orcamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orcamentoId", nullable=false)
	public Orcamento getOrcamento() {
		return this.orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}


	//bi-directional many-to-one association to Solicitante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitanteId", nullable=false)
	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}


	//bi-directional many-to-many association to Servico
	@ManyToMany(mappedBy="processos")
	public List<Servico> getServicos() {
		return this.servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

}