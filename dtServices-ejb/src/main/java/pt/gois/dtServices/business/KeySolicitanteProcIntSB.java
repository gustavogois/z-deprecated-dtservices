package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.KeySolicitanteProcInt;
import pt.gois.dtServices.entity.ProcessoInterno;

@Stateless
public class KeySolicitanteProcIntSB extends GeneralSB<KeySolicitanteProcInt> implements KeySolicitanteProcIntSBLocal{

	public KeySolicitanteProcIntSB() {
		super(KeySolicitanteProcInt.class);
	}

	@Override
	public void nextId(ProcessoInterno processoInterno) {
//		getEM().createQuery(qlString)
		
	}

}
