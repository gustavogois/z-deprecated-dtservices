package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Bairro;

@Stateless
public class BairroSB extends GeneralSB<Bairro> implements BairroSBLocal{

	public BairroSB() {
		super(Bairro.class);
	}
}
