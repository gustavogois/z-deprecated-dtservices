package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.EntidadeDeFacturacao;

@Stateless
public class EntidadeDeFacturacaoSB extends GeneralSB<EntidadeDeFacturacao> implements EntidadeDeFacturacaoSBLocal{

	public EntidadeDeFacturacaoSB() {
		super(EntidadeDeFacturacao.class);
	}
}
