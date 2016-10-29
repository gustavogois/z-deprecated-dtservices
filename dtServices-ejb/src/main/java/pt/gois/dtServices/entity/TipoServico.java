package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_tipo_servico database table.
 * 
 */
@Entity
@Table(name="tbl_tipo_servico")
@NamedQuery(name="TipoServico.findAll", query="SELECT t FROM TipoServico t")
public class TipoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private String nome;
	private Date updateDt;
	private String updateUser;
	private double valor;
	private List<TipoServicoSolicitante> tblTipoServicoSolicitantes;

	public TipoServico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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


	//bi-directional many-to-one association to TipoServicoSolicitante
	@OneToMany(mappedBy="tblTipoServico")
	public List<TipoServicoSolicitante> getTblTipoServicoSolicitantes() {
		return this.tblTipoServicoSolicitantes;
	}

	public void setTblTipoServicoSolicitantes(List<TipoServicoSolicitante> tblTipoServicoSolicitantes) {
		this.tblTipoServicoSolicitantes = tblTipoServicoSolicitantes;
	}

	public TipoServicoSolicitante addTblTipoServicoSolicitante(TipoServicoSolicitante tblTipoServicoSolicitante) {
		getTblTipoServicoSolicitantes().add(tblTipoServicoSolicitante);
		tblTipoServicoSolicitante.setTblTipoServico(this);

		return tblTipoServicoSolicitante;
	}

	public TipoServicoSolicitante removeTblTipoServicoSolicitante(TipoServicoSolicitante tblTipoServicoSolicitante) {
		getTblTipoServicoSolicitantes().remove(tblTipoServicoSolicitante);
		tblTipoServicoSolicitante.setTblTipoServico(null);

		return tblTipoServicoSolicitante;
	}

}