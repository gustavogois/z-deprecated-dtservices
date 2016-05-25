package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Servico;

@Stateless
public class ServicoSB extends GeneralSB<Servico> implements ServicoSBLocal{

	public ServicoSB() {
		super(Servico.class);
	}
}
