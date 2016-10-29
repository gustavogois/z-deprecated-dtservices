package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tbl_tipo_de_estadosrv database table.
 * 
 */
@Entity
@Table(name="tbl_tipo_de_estadosrv")
@NamedQuery(name="TipoDeEstadoSRV.findAll", query="SELECT t FROM TipoDeEstadoSRV t")
public class TipoDeEstadoSRV implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nome;
	private List<EstadoServico> tblEstadoServicos;

	public TipoDeEstadoSRV() {
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


	//bi-directional many-to-one association to EstadoServico
	@OneToMany(mappedBy="tblTipoDeEstadosrv")
	public List<EstadoServico> getTblEstadoServicos() {
		return this.tblEstadoServicos;
	}

	public void setTblEstadoServicos(List<EstadoServico> tblEstadoServicos) {
		this.tblEstadoServicos = tblEstadoServicos;
	}

	public EstadoServico addTblEstadoServico(EstadoServico tblEstadoServico) {
		getTblEstadoServicos().add(tblEstadoServico);
		tblEstadoServico.setTblTipoDeEstadosrv(this);

		return tblEstadoServico;
	}

	public EstadoServico removeTblEstadoServico(EstadoServico tblEstadoServico) {
		getTblEstadoServicos().remove(tblEstadoServico);
		tblEstadoServico.setTblTipoDeEstadosrv(null);

		return tblEstadoServico;
	}

}