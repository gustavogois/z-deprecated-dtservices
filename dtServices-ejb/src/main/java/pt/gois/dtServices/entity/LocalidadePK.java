package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the localidade database table.
 * 
 */
@Embeddable
public class LocalidadePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private String dd;
	private String cc;
	private String llll;

	public LocalidadePK() {
	}

	@Column(insertable=false, updatable=false)
	public String getDd() {
		return this.dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}

	@Column(insertable=false, updatable=false)
	public String getCc() {
		return this.cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getLlll() {
		return this.llll;
	}
	public void setLlll(String llll) {
		this.llll = llll;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LocalidadePK)) {
			return false;
		}
		LocalidadePK castOther = (LocalidadePK)other;
		return 
			this.dd.equals(castOther.dd)
			&& this.cc.equals(castOther.cc)
			&& this.llll.equals(castOther.llll);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dd.hashCode();
		hash = hash * prime + this.cc.hashCode();
		hash = hash * prime + this.llll.hashCode();
		
		return hash;
	}
}