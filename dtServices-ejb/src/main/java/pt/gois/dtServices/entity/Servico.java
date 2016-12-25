package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the servico database table.
 * 
 */
@Entity
@NamedQuery(name="Servico.findAll", query="SELECT s FROM Servico s")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtCadastro;
	private Date dtFim;
	private Date dtInicio;
	private String observacoes;
	private ProcessoInterno processoInterno;
	private TipoServicoSolicitante tipoServicoSolicitante;
	private Date updateDt;
	private String updateUser;
	private double valor;
	private List<EstadosServico> estadosServicos;

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

	@ManyToOne
	@JoinColumn(name="processoId")
	public ProcessoInterno getProcessoInterno() {
		return this.processoInterno;
	}

	public void setProcessoInterno(ProcessoInterno processoInterno) {
		this.processoInterno = processoInterno;
	}

	@ManyToOne
	@JoinColumn(name="tipoServico_SolicitanteId")
	public TipoServicoSolicitante getTipoServicoSolicitante() {
		return this.tipoServicoSolicitante;
	}

	public void setTipoServicoSolicitante(TipoServicoSolicitante tipoServicoSolicitante) {
		this.tipoServicoSolicitante = tipoServicoSolicitante;
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


	//bi-directional many-to-one association to Estadosservico
	@OneToMany(mappedBy="servico", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<EstadosServico> getEstadosServicos() {
		return this.estadosServicos;
	}

	public void setEstadosServicos(List<EstadosServico> estadosservicos) {
		this.estadosServicos = estadosservicos;
	}

	public EstadosServico addEstadosservico(EstadosServico estadosservico) {
		getEstadosServicos().add(estadosservico);
		estadosservico.setServico(this);

		return estadosservico;
	}

	public EstadosServico removeEstadosservico(EstadosServico estadosservico) {
		getEstadosServicos().remove(estadosservico);
		estadosservico.setServico(null);

		return estadosservico;
	}
	


}