package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_processoexterno database table.
 * 
 */
@Entity
@Table(name="tbl_processoexterno")
@NamedQuery(name="Processoexterno.findAll", query="SELECT p FROM Processoexterno p")
public class Processoexterno implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Date updateDt;
	private String updateUser;
	private Imovel tblImovel;
	private List<ProcessoInterno> tblProcessoInternos;
	private Solicitante tblSolicitante;

	public Processoexterno() {
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


	//bi-directional one-to-one association to Imovel
	@OneToOne(mappedBy="tblProcessoexterno", fetch=FetchType.LAZY)
	public Imovel getTblImovel() {
		return this.tblImovel;
	}

	public void setTblImovel(Imovel tblImovel) {
		this.tblImovel = tblImovel;
	}


	//bi-directional many-to-one association to ProcessoInterno
	@OneToMany(mappedBy="tblProcessoexterno")
	public List<ProcessoInterno> getTblProcessoInternos() {
		return this.tblProcessoInternos;
	}

	public void setTblProcessoInternos(List<ProcessoInterno> tblProcessoInternos) {
		this.tblProcessoInternos = tblProcessoInternos;
	}

	public ProcessoInterno addTblProcessoInterno(ProcessoInterno tblProcessoInterno) {
		getTblProcessoInternos().add(tblProcessoInterno);
		tblProcessoInterno.setTblProcessoexterno(this);

		return tblProcessoInterno;
	}

	public ProcessoInterno removeTblProcessoInterno(ProcessoInterno tblProcessoInterno) {
		getTblProcessoInternos().remove(tblProcessoInterno);
		tblProcessoInterno.setTblProcessoexterno(null);

		return tblProcessoInterno;
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

}