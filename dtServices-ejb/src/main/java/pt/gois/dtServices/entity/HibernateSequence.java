package pt.gois.dtServices.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the hibernate_sequence database table.
 * 
 */
@Entity
@Table(name="hibernate_sequence")
@NamedQuery(name="HibernateSequence.findAll", query="SELECT h FROM HibernateSequence h")
public class HibernateSequence implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigInteger nextVal;

	public HibernateSequence() {
	}


	@Column(name="next_val")
	public BigInteger getNextVal() {
		return this.nextVal;
	}

	public void setNextVal(BigInteger nextVal) {
		this.nextVal = nextVal;
	}

}