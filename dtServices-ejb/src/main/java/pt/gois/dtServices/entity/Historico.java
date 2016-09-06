package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historico database table.
 * 
 */
@Entity
@NamedQuery(name="Historico.findAll", query="SELECT h FROM Historico h")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date data;
	private String descricao;
	private Integer idObjeto;

	public Historico() {
	}


	@Id
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getIdObjeto() {
		return this.idObjeto;
	}

	public void setIdObjeto(Integer idObjeto) {
		this.idObjeto = idObjeto;
	}

}