package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tbl_users database table.
 * 
 */
@Entity
@Table(name="tbl_users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String createDt;
	private Date expiryDate;
	private byte locked;
	private String name;
	private String password;
	private String phone;
	private Date updateDt;
	private String updateUser;
	private String username;
	private TipoDeUser tblTipoDeUser;

	public User() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}


	@Temporal(TemporalType.DATE)
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}


	public byte getLocked() {
		return this.locked;
	}

	public void setLocked(byte locked) {
		this.locked = locked;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	//bi-directional many-to-one association to TipoDeUser
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tipoDeUserId")
	public TipoDeUser getTblTipoDeUser() {
		return this.tblTipoDeUser;
	}

	public void setTblTipoDeUser(TipoDeUser tblTipoDeUser) {
		this.tblTipoDeUser = tblTipoDeUser;
	}

}