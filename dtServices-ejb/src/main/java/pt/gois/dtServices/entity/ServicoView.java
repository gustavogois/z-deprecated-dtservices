package pt.gois.dtServices.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="v_servico")
@NamedQuery(name="ServicoView.findAll", query="SELECT p FROM ServicoView p")
public class ServicoView {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer idEstado; 
	private String nomeEstado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEstado;
	private double valor;
	private Integer processoId;
	public Integer getId() {
		return id;
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
	public String getNomeEstado() {
		return nomeEstado;
	}
	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
	public Date getDtEstado() {
		return dtEstado;
	}
	public void setDtEstado(Date dtEstado) {
		this.dtEstado = dtEstado;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Integer getProcessoId() {
		return processoId;
	}
	public void setProcessoId(Integer processoId) {
		this.processoId = processoId;
	}

	
}
