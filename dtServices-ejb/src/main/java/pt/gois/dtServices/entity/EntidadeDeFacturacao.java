package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_entidade_de_facturacao database table.
 * 
 */
@Entity
@Table(name="tbl_entidade_de_facturacao")
@NamedQuery(name="EntidadeDeFacturacao.findAll", query="SELECT e FROM EntidadeDeFacturacao e")
public class EntidadeDeFacturacao implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nif;
	private String nome;
	private Date updateDt;
	private String updateUser;
	private Solicitante tblSolicitante;

	public EntidadeDeFacturacao() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
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