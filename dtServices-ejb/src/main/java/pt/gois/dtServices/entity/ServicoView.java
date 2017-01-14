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
@Table(name="v_servico")
@NamedQuery(name="VServico.findAll", query="SELECT v FROM ServicoView v")
public class ServicoView implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dtEstado;
	private String nomeEstado;
	private String nomeTipo;
	private double valor;
	private Integer processoId;

	public Integer getProcessoId() {
		return processoId;
	}

	public void setProcessoId(Integer processoId) {
		this.processoId = processoId;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public ServicoView() {
	}

	@Id
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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


	public String getNomeTipo() {
		return this.nomeTipo;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

}