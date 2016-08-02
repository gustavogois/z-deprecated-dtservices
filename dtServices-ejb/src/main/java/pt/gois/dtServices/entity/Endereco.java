package pt.gois.dtServices.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the localidade database table.
 * 
 */
@Entity
@Table(name = "AddressVW")
@NamedQuery(name = "AddressVW.findAll", query = "SELECT l FROM Endereco l")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer id;
    String localidade;
    String ruaPorta;
    String complemento;
    String cp;

    private Concelho concelho;
    private Distrito distrito;

    public Endereco() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
	return this.id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    // bi-directional many-to-one association to Concelho
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