package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Historico;

@Stateless
public class HistoricoSB extends GeneralSB<Historico> implements HistoricoSBLocal{

	public HistoricoSB() {
		super(Historico.class);
	}
	
}
