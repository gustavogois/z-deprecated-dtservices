package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_tipo_de_estadopi database table.
 * 
 */
@Entity
@Table(name="tbl_tipo_de_estadopi")
@NamedQuery(name="TipoDeEstadoPI.findAll", query="SELECT t FROM TipoDeEstadoPI t")
public class TipoDeEstadoPI implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private List<EstadoProcesso> tblEstadoProcessos;

	public TipoDeEstadoPI() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-one association to EstadoProcesso
	@OneToMany(mappedBy="tblTipoDeEstadopi")
	public List<EstadoProcesso> getTblEstadoProcessos() {
		return this.tblEstadoProcessos;
	}

	public void setTblEstadoProcessos(List<EstadoProcesso> tblEstadoProcessos) {
		this.tblEstadoProcessos = tblEstadoProcessos;
	}

	public EstadoProcesso addTblEstadoProcesso(EstadoProcesso tblEstadoProcesso) {
		getTblEstadoProcessos().add(tblEstadoProcesso);
		tblEstadoProcesso.setTblTipoDeEstadopi(this);

		return tblEstadoProcesso;
	}

	public EstadoProcesso removeTblEstadoProcesso(EstadoProcesso tblEstadoProcesso) {
		getTblEstadoProcessos().remove(tblEstadoProcesso);
		tblEstadoProcesso.setTblTipoDeEstadopi(null);

		return tblEstadoProcesso;
	}

}