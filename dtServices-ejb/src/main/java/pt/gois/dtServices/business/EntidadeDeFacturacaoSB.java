package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Entidadedefacturacao;

@Stateless
public class EntidadeDeFacturacaoSB extends GeneralSB<Entidadedefacturacao> implements EntidadeDeFacturacaoSBLocal{

	public EntidadeDeFacturacaoSB() {
		super(Entidadedefacturacao.class);
	}
}
