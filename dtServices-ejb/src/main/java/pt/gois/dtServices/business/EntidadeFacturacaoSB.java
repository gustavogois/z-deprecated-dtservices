package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Entidadedefacturacao;;

@Stateless
public class EntidadeFacturacaoSB extends GeneralSB<Entidadedefacturacao> implements EntidadeFacturacaoSBLocal{

	public EntidadeFacturacaoSB() {
		super(Entidadedefacturacao.class);
	}
}
