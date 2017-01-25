package pt.gois.dtServices.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the v_servico database table.
 * 
 */
@Entity
@Table(name="v_processo")
@NamedQuery(name="ProcessoView.findAll", query="SELECT p FROM ProcessoView p")
public class ProcessoView implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer id;
	private Integer idEstado; 
	private String nomeEstado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEstado;
	private String codExterno;
	private String codInterno;
	private Integer idSolicitante;
	private String nomeSolicitante;

	public Integer getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Integer idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public ProcessoView() {
	}
	
	public String getCodExterno() {
		return codExterno;
	}

	public void setCodExterno(String codExterno) {
		this.codExterno = codExterno;
	}

	public String getCodInterno() {
		return codInterno;
	}

	public void setCodInterno(String codInterno) {
		this.codInterno = codInterno;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Date getDtEstado() {
		return this.dtEstado;
	}

	public void setDtEstado(Date dtEstado) {
		this.dtEstado = dtEstado;
	}


	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

}