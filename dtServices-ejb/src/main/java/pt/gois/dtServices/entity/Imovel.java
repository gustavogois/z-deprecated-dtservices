package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tbl_imovel database table.
 * 
 */
@Entity
@Table(name="tbl_imovel")
@NamedQuery(name="Imovel.findAll", query="SELECT i FROM Imovel i")
public class Imovel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String cc;
	private String codigoPostal;
	private String complemento;
	private String crp;
	private String dd;
	private String inquilino;
	private String latitude;
	private String localidade;
	private String longitude;
	private String ruaPorta;
	private Date updateDt;
	private String updateUser;
	private List<Imagem> tblImagems;
	private Processoexterno tblProcessoexterno;

	public Imovel() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getCc() {
		return this.cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}


	public String getCodigoPostal() {
		return this.codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}


	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getCrp() {
		return this.crp;
	}

	public void setCrp(String crp) {
		this.crp = crp;
	}


	public String getDd() {
		return this.dd;
	}

	public void setDd(String dd) {
		this.dd = dd;
	}


	public String getInquilino() {
		return this.inquilino;
	}

	public void setInquilino(String inquilino) {
		this.inquilino = inquilino;
	}


	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getRuaPorta() {
		return this.ruaPorta;
	}

	public void setRuaPorta(String ruaPorta) {
		this.ruaPorta = ruaPorta;
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


	//bi-directional many-to-many association to Imagem
	@ManyToMany
	@JoinTable(
		name="tbl_imagem_imovel"
		, joinColumns={
			@JoinColumn(name="imovelId")
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


	//bi-directional one-to-one association to Processoexterno
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id")
	public Processoexterno getTblProcessoexterno() {
		return this.tblProcessoexterno;
	}

	public void setTblProcessoexterno(Processoexterno tblProcessoexterno) {
		this.tblProcessoexterno = tblProcessoexterno;
	}

}