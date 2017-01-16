package pt.gois.dtServices.business;

import java.util.Calendar;

import javax.ejb.Local;

import pt.gois.dtServices.entity.ProcInternoView;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.User;

@Local
public interface ProcessoInternoSBLocal extends GeneralSBLocal<ProcessoInterno>{
	public void salvar(ProcessoInterno processoInterno, Integer tipoEstado, Calendar data, User user);
	public boolean canEdit(ProcInternoView processoInterno);
	public boolean canSuspend(ProcInternoView processoInterno);
	public boolean canFinalize(ProcInternoView processoInterno);
	public boolean canFaturar(ProcInternoView processoInterno);
	public boolean canPagar(ProcInternoView processo);
	public void checkStatusProcessoInterno(Integer idProcesso, User user);
	public ProcessoInterno buscaProcessoComServicos(Integer idProcesso);
}
