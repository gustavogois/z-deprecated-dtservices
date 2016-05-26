package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the orcamento database table.
 * 
 */
@Entity
@NamedQuery(name="Orcamento.findAll", query="SELECT o FROM Orcamento o")
public class Orcamento implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dataDeSolicitacao;

	public Orcamento() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	public Date getDataDeSolicitacao() {
		return this.dataDeSolicitacao;
	}

	public void setDataDeSolicitacao(Date dataDeSolicitacao) {
		this.dataDeSolicitacao = dataDeSolicitacao;
	}

}