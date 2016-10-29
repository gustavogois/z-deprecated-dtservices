package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_servico database table.
 * 
 */
@Entity
@Table(name="tbl_servico")
@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtCadastro;
	private Date dtFim;
	private Date dtInicio;
	private String observacoes;
	private Date updateDt;
	private String updateUser;
	private double valor;
	private List<EstadoServico> tblEstadoServicos;
	private List<Imagem> tblImagems;
	private ProcessoInterno tblProcessoInterno;
	private TipoServicoSolicitante tblTipoServicoSolicitante;

	public Servico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtFim() {
		return this.dtFim;
	}

	public void setDtFim(Date dtFim) {
		this.dtFim = dtFim;
	}


	@Temporal(TemporalType.DATE)
	public Date getDtInicio() {
		return this.dtInicio;
	}

	public void setDtInicio(Date dtInicio) {
		this.dtInicio = dtInicio;
	}


	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
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


	public double getValor() {
		return this.valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}


	//bi-directional many-to-one association to EstadoServico
	@OneToMany(mappedBy="tblServico")
	public List<EstadoServico> getTblEstadoServicos() {
		return this.tblEstadoServicos;
	}

	public void setTblEstadoServicos(List<EstadoServico> tblEstadoServicos) {
		this.tblEstadoServicos = tblEstadoServicos;
	}

	public EstadoServico addTblEstadoServico(EstadoServico tblEstadoServico) {
		getTblEstadoServicos().add(tblEstadoServico);
		tblEstadoServico.setTblServico(this);

		return tblEstadoServico;
	}

	public EstadoServico removeTblEstadoServico(EstadoServico tblEstadoServico) {
		getTblEstadoServicos().remove(tblEstadoServico);
		tblEstadoServico.setTblServico(null);

		return tblEstadoServico;
	}


	//bi-directional many-to-many association to Imagem
	@ManyToMany
	@JoinTable(
		name="tbl_imagem_servico"
		, joinColumns={
			@JoinColumn(name="servicoId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="imagemId")
			}
		)
	public List<Imagem> getTblImagems() {
		return this.tblImagems;
	}

	public void setTblImagems(List<Imagem> tblImagems) {
		this.tblImagems = tblImagems;
	}


	//bi-directional many-to-one association to ProcessoInterno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="processoId")
	public ProcessoInterno getTblProcessoInterno() {
		return this.tblProcessoInterno;
	}

	public void setTblProcessoInterno(ProcessoInterno tblProcessoInterno) {
		this.tblProcessoInterno = tblProcessoInterno;
	}


	//bi-directional many-to-one association to TipoServicoSolicitante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoServico_SolicitanteId")
	public TipoServicoSolicitante getTblTipoServicoSolicitante() {
		return this.tblTipoServicoSolicitante;
	}

	public void setTblTipoServicoSolicitante(TipoServicoSolicitante tblTipoServicoSolicitante) {
		this.tblTipoServicoSolicitante = tblTipoServicoSolicitante;
	}

}