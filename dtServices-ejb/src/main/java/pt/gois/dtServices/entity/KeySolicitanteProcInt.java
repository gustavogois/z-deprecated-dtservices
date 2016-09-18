package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the keysolicitanteprocint database table.
 * 
 */
@Entity
@NamedQuery(name="KeySolicitanteProcInt.findAll", query="SELECT k FROM KeySolicitanteProcInt k")
public class KeySolicitanteProcInt implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private ProcessoInterno processoInterno;
	private Solicitante solicitante;

	public KeySolicitanteProcInt() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	//bi-directional many-to-one association to Processointerno
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="procInternoId")
	public ProcessoInterno getProcessoInterno() {
		return this.processoInterno;
	}

	public void setProcessoInterno(ProcessoInterno processoInterno) {
		this.processoInterno = processoInterno;
	}


	//bi-directional many-to-one association to Solicitante
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="solicitanteId")
	public Solicitante getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(Solicitante solicitante) {
		this.solicitante = solicitante;
	}

}