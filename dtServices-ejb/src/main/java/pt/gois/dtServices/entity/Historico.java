package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the t_historico database table.
 * 
 */
@Entity
@Table(name="t_historico")
@NamedQuery(name="Historico.findAll", query="SELECT t FROM Historico t")
public class Historico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private String descricao;

	private int objetoId;

	private int tipoObjetoId;

	public Historico() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(int objetoId) {
		this.objetoId = objetoId;
	}

	public int getTipoObjetoId() {
		return this.tipoObjetoId;
	}

	public void setTipoObjetoId(int tipoObjetoId) {
		this.tipoObjetoId = tipoObjetoId;
	}

}