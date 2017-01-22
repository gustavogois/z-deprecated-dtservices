package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the t_processo database table.
 * 
 */
@Entity
@Table(name="t_processo")
@NamedQuery(name="Processo.findAll", query="SELECT t FROM Processo t")
public class Processo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String codExterno;

	private String codInterno;

	private byte comChaves;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtFim;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtInicio;

	private String nomeRequisitante;

	private String numeroChave;

	private String observacoes;

	@Temporal(TemporalType.TIMESTAMP)
	private Date previsaoFim;

	@Temporal(TemporalType.TIMESTAMP)
	private Date previsaoInicio;

	//bi-directional many-to-one association to TEstadoProcesso
	@OneToMany(mappedBy="processo")
	private List<EstadoProcesso> estadoProcessos;

	//bi-directional many-to-one association to Imovel
	@ManyToOne
	@JoinColumn(name="imovelId")
	private Imovel imovel;

	//bi-directional many-to-one association to Solicitante
	@ManyToOne
	@JoinColumn(name="solicitanteId")
	private Solicitante solicitante;

	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="processo")
	private List<Servico> servicos;

	public Processo() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodExterno() {
		return this.codExterno;
	}

	public void setCodExterno(String codExterno) {
		this.codExterno = codExterno;
	}

	public String getCodInterno() {
		return this.codInterno;
	}

	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}

	public byte getComChaves() {
		return this.comChaves;
	}

	public void setComChaves(byte comChaves) {
		this.comChaves = comChaves;
	}

	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtFim() {
		return this.dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}

	public Date getDtInicio() {
		return this.dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}

	public String getNomeRequisitante() {
		return this.nomeRequisitante;
	}

	public void setNomeRequisitante(String nomeRequisitante) {
		this.nomeRequisitante = nomeRequisitante;
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

	public Date getPrevisaoFim() {
		return this.previsaoFim;
	}

	public void setPrevisaoFim(Date previsaoFim) {
		this.previsaoFim = previsaoFim;
	}

	public Date getPrevisaoInicio() {
		return this.previsaoInicio;
	}

	public void setPrevisaoInicio(Date previsaoInicio) {
		this.previsaoInicio = previsaoInicio;
	}

	public List<EstadoProcesso> getEstadoProcessos() {
		return this.estadoProcessos;
	}

	public void setEstadoProcessos(List<EstadoProcesso> estadoProcessos) {
		this.estadoProcessos = estadoProcessos;
	}

	public EstadoProcesso addEstadoProcesso(EstadoProcesso estadoProcesso) {
		getEstadoProcessos().add(estadoProcesso);
		estadoProcesso.setProcesso(this);

		return estadoProcesso;
	}

	public EstadoProcesso removeEstadoProcesso(EstadoProcesso estadoProcesso) {
		getEstadoProcessos().remove(estadoProcesso);
		estadoProcesso.setProcesso(null);

		return estadoProcesso;
	}

	public Imovel getImovel() {
		return this.imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

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