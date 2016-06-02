package pt.gois.dtServices.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the localidade database table.
 * 
 */
@Entity
@NamedQuery(name="Localidade.findAll", query="SELECT l FROM Localidade l")
public class Localidade implements Serializable {
	private static final long serialVersionUID = 1L;
	private LocalidadePK localidadeId;
	private String artCod;
	private String artDesig;
	private String artLocal;
	private String artTipo;
	private String artTitulo;
	private String cliente;
	private String codigoPostal3;
	private String codigoPostal4;
	private String codigoPostalDesignacao;
	private String localidade;
	private String porta;
	private String priPrep;
	private String segPrep;
	private String troco;
	private Concelho concelho;
	private Distrito distrito;

	public Localidade() {
	}


	@EmbeddedId
	public LocalidadePK getLocalidadeId() {
		return this.localidadeId;
	}

	public void setLocalidadeId(LocalidadePK id) {
		this.localidadeId = id;
	}


	@Column(name="ART_COD")
	public String getArtCod() {
		return this.artCod;
	}

	public void setArtCod(String artCod) {
		this.artCod = artCod;
	}


	@Column(name="ART_DESIG")
	public String getArtDesig() {
		return this.artDesig;
	}

	public void setArtDesig(String artDesig) {
		this.artDesig = artDesig;
	}


	@Column(name="ART_LOCAL")
	public String getArtLocal() {
		return this.artLocal;
	}

	public void setArtLocal(String artLocal) {
		this.artLocal = artLocal;
	}


	@Column(name="ART_TIPO")
	public String getArtTipo() {
		return this.artTipo;
	}

	public void setArtTipo(String artTipo) {
		this.artTipo = artTipo;
	}


	@Column(name="ART_TITULO")
	public String getArtTitulo() {
		return this.artTitulo;
	}

	public void setArtTitulo(String artTitulo) {
		this.artTitulo = artTitulo;
	}


	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public String getCodigoPostal3() {
		return this.codigoPostal3;
	}

	public void setCodigoPostal3(String codigoPostal3) {
		this.codigoPostal3 = codigoPostal3;
	}


	public String getCodigoPostal4() {
		return this.codigoPostal4;
	}

	public void setCodigoPostal4(String codigoPostal4) {
		this.codigoPostal4 = codigoPostal4;
	}


	public String getCodigoPostalDesignacao() {
		return this.codigoPostalDesignacao;
	}

	public void setCodigoPostalDesignacao(String codigoPostalDesignacao) {
		this.codigoPostalDesignacao = codigoPostalDesignacao;
	}


	public String getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}


	public String getPorta() {
		return this.porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}


	@Column(name="PRI_PREP")
	public String getPriPrep() {
		return this.priPrep;
	}

	public void setPriPrep(String priPrep) {
		this.priPrep = priPrep;
	}


	@Column(name="SEG_PREP")
	public String getSegPrep() {
		return this.segPrep;
	}

	public void setSegPrep(String segPrep) {
		this.segPrep = segPrep;
	}


	public String getTroco() {
		return this.troco;
	}

	public void setTroco(String troco) {
		this.troco = troco;
	}


	//bi-directional many-to-one association to Concelho
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CC")
	public Concelho getConcelho() {
		return this.concelho;
	}

	public void setConcelho(Concelho concelho) {
		this.concelho = concelho;
	}


	//bi-directional many-to-one association to Distrito
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DD")
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

}