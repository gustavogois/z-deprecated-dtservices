package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.EntidadedeFacturacao;

@Stateless
public class EntidadeFacturacaoSB extends GeneralSB<EntidadedeFacturacao> implements EntidadeFacturacaoSBLocal{

	public EntidadeFacturacaoSB() {
		super(EntidadedeFacturacao.class);
	}
}
