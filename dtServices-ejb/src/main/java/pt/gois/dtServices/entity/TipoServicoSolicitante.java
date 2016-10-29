package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_tipo_servico_solicitante database table.
 * 
 */
@Entity
@Table(name="tbl_tipo_servico_solicitante")
@NamedQuery(name="TipoServicoSolicitante.findAll", query="SELECT t FROM TipoServicoSolicitante t")
public class TipoServicoSolicitante implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date updateDt;
	private String updateUser;
	private double valor;
	private List<Servico> tblServicos;
	private Solicitante tblSolicitante;
	private TipoServico tblTipoServico;

	public TipoServicoSolicitante() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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


	//bi-directional many-to-one association to Servico
	@OneToMany(mappedBy="tblTipoServicoSolicitante")
	public List<Servico> getTblServicos() {
		return this.tblServicos;
	}

	public void setTblServicos(List<Servico> tblServicos) {
		this.tblServicos = tblServicos;
	}

	public Servico addTblServico(Servico tblServico) {
		getTblServicos().add(tblServico);
		tblServico.setTblTipoServicoSolicitante(this);

		return tblServico;
	}

	public Servico removeTblServico(Servico tblServico) {
		getTblServicos().remove(tblServico);
		tblServico.setTblTipoServicoSolicitante(null);

		return tblServico;
	}


	//bi-directional many-to-one association to Solicitante
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="solicitanteId")
	public Solicitante getTblSolicitante() {
		return this.tblSolicitante;
	}

	public void setTblSolicitante(Solicitante tblSolicitante) {
		this.tblSolicitante = tblSolicitante;
	}


	//bi-directional many-to-one association to TipoServico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoServicoId")
	public TipoServico getTblTipoServico() {
		return this.tblTipoServico;
	}

	public void setTblTipoServico(TipoServico tblTipoServico) {
		this.tblTipoServico = tblTipoServico;
	}

}