package pt.gois.dtServices.entity;
// Generated 1/jun/2016 6:41:26 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orcamento generated by hbm2java
 */
@Entity
@Table(name = "Orcamento")
public class Orcamento implements java.io.Serializable {

	private Integer id;
	private Date dataDeSolicitacao;

	public Orcamento() {
	}

	public Orcamento(Date dataDeSolicitacao) {
		this.dataDeSolicitacao = dataDeSolicitacao;
	}

	@Id
	@GeneratedValue

	@Column(name = "id", nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dataDeSolicitacao")
	public Date getDataDeSolicitacao() {
		return this.dataDeSolicitacao;
	}

	public void setDataDeSolicitacao(Date dataDeSolicitacao) {
		this.dataDeSolicitacao = dataDeSolicitacao;
	}

}
