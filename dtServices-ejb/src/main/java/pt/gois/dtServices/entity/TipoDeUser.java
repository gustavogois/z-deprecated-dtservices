package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_tipo_de_user database table.
 * 
 */
@Entity
@Table(name="tbl_tipo_de_user")
@NamedQuery(name="TipoDeUser.findAll", query="SELECT t FROM TipoDeUser t")
public class TipoDeUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private List<User> tblUsers;

	public TipoDeUser() {
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


	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="tblTipoDeUser")
	public List<User> getTblUsers() {
		return this.tblUsers;
	}

	public void setTblUsers(List<User> tblUsers) {
		this.tblUsers = tblUsers;
	}

	public User addTblUser(User tblUser) {
		getTblUsers().add(tblUser);
		tblUser.setTblTipoDeUser(this);

		return tblUser;
	}

	public User removeTblUser(User tblUser) {
		getTblUsers().remove(tblUser);
		tblUser.setTblTipoDeUser(null);

		return tblUser;
	}

}