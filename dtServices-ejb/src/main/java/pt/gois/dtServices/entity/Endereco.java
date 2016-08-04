package pt.gois.dtServices.entity;

import java.io.Serializable;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	String localidade;
	String ruaPorta;
	String complemento;
	String cp;

	Concelho concelho;
	Distrito distrito;

	public Endereco() {
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CC")
	public Concelho getConcelho() {
		return this.concelho;
	}

	public void setConcelho(Concelho concelho) {
		this.concelho = concelho;
	}

	// bi-directional many-to-one association to Distrito
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DD")
	public Distrito getDistrito() {
		return this.distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getRuaPorta() {
		return ruaPorta;
	}

	public void setRuaPorta(String ruaPorta) {
		this.ruaPorta = ruaPorta;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

}