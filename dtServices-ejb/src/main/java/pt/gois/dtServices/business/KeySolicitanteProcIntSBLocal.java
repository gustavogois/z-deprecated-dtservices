package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.KeySolicitanteProcInt;
import pt.gois.dtServices.entity.ProcessoInterno;

@Local
public interface KeySolicitanteProcIntSBLocal extends GeneralSBLocal<KeySolicitanteProcInt>{
	public void nextId(ProcessoInterno processoInterno);
}
