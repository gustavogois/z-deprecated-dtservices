package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.ProcessoInterno;

@Local
public interface ProcessoInternoSBLocal extends GeneralSBLocal<ProcessoInterno>{
	public void salvar(ProcessoInterno processoInterno, EstadosProcesso estado);
}
