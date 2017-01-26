package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the t_servico database table.
 * 
 */
@Entity
@Table(name="t_servico")
@NamedQuery(name="Servico.findAll", query="SELECT t FROM Servico t")
public class Servico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	private Date dtCadastro;

	private String observacoes;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDt;

	private String updateUser;

	private double valor;

	//bi-directional many-to-one association to TEstadoServico
	@OneToMany(mappedBy="servico", cascade = CascadeType.ALL)
	private List<EstadoServico> estadoServicos;

	//bi-directional many-to-one association to TipoServico
	@ManyToOne
	@JoinColumn(name="tipoServicoId")
	private TipoServico tipoServico;

	//bi-directional many-to-one association to Processo
	@ManyToOne
	@JoinColumn(name="processoId")
	private Processo processo;

	public Servico() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtCadastro() {
		return this.dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getObservacoes() {
		return this.observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

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

	public TipoServico getTipoServico() {
		return this.tipoServico;
	}

	public void setTipoServico(TipoServico tipoServico) {
		this.tipoServico = tipoServico;
	}

	public Processo getProcesso() {
		return this.processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}
	public List<EstadoServico> getEstadoServicos() {
		return this.estadoServicos;
	}

	public void setEstadoServicos(List<EstadoServico> estadoServicos) {
		this.estadoServicos = estadoServicos;
	}

	public EstadoServico addEstadoServico(EstadoServico estadoServico) {
		getEstadoServicos().add(estadoServico);
		estadoServico.setServico(this);

		return estadoServico;
	}

	public EstadoServico removeEstadoServico(EstadoServico estadoServico) {
		getEstadoServicos().remove(estadoServico);
		estadoServico.setServico(null);

		return estadoServico;
	}

}