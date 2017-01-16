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
@NamedQuery(name="ProcInternoView.findAll", query="SELECT p FROM ProcInternoView p")
public class ProcInternoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idEstado; 
	private String nomeEstado;
	private Date dtEstado;
	private Integer processoExternoId;
	private String idProcCliente;

	
	public String getIdProcCliente() {
		return idProcCliente;
	}

	public void setIdProcCliente(String idProcCliente) {
		this.idProcCliente = idProcCliente;
	}

	public ProcInternoView() {
	}
	
	@Id
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

	@Temporal(TemporalType.TIMESTAMP)
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

	public Integer getProcessoExternoId() {
		return processoExternoId;
	}

	public void setProcessoExternoId(Integer processoExternoId) {
		this.processoExternoId = processoExternoId;
	}

}