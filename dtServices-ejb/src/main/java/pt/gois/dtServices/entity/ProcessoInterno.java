package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_processo_interno database table.
 * 
 */
@Entity
@Table(name="tbl_processo_interno")
@NamedQuery(name="ProcessoInterno.findAll", query="SELECT p FROM ProcessoInterno p")
public class ProcessoInterno implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private byte comChaves;
	private String fatura;
	private String idProcCliente;
	private String nomeSolicitante;
	private String numeroChave;
	private String observacoes;
	private Date previsaoFim;
	private Date previsaoInicio;
	private Date updateDt;
	private String updateUser;
	private List<EstadoProcesso> tblEstadoProcessos;
	private Processoexterno tblProcessoexterno;
	private List<Servico> tblServicos;

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


	//bi-directional many-to-one association to EstadoProcesso
	@OneToMany(mappedBy="tblProcessoInterno")
	public List<EstadoProcesso> getTblEstadoProcessos() {
		return this.tblEstadoProcessos;
	}

	public void setTblEstadoProcessos(List<EstadoProcesso> tblEstadoProcessos) {
		this.tblEstadoProcessos = tblEstadoProcessos;
	}

	public EstadoProcesso addTblEstadoProcesso(EstadoProcesso tblEstadoProcesso) {
		getTblEstadoProcessos().add(tblEstadoProcesso);
		tblEstadoProcesso.setTblProcessoInterno(this);

		return tblEstadoProcesso;
	}

	public EstadoProcesso removeTblEstadoProcesso(EstadoProcesso tblEstadoProcesso) {
		getTblEstadoProcessos().remove(tblEstadoProcesso);
		tblEstadoProcesso.setTblProcessoInterno(null);

		return tblEstadoProcesso;
	}


	//bi-directional many-to-one association to Processoexterno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processoExternoId")
	public Processoexterno getTblProcessoexterno() {
		return this.tblProcessoexterno;
	}

	public void setTblProcessoexterno(Processoexterno tblProcessoexterno) {
		this.tblProcessoexterno = tblProcessoexterno;
	}


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tblProcessoInterno")
	public List<Servico> getTblServicos() {
		return this.tblServicos;
	}

	public void setTblServicos(List<Servico> tblServicos) {
		this.tblServicos = tblServicos;
	}

	public Servico addTblServico(Servico tblServico) {
		getTblServicos().add(tblServico);
		tblServico.setTblProcessoInterno(this);

		return tblServico;
	}

	public Servico removeTblServico(Servico tblServico) {
		getTblServicos().remove(tblServico);
		tblServico.setTblProcessoInterno(null);

		return tblServico;
	}

}