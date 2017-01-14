package pt.gois.dtServices.business;

import java.util.Date;

import javax.ejb.Local;

import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.User;

@Local
public interface ProcessoInternoSBLocal extends GeneralSBLocal<ProcessoInterno>{
	public void salvar(ProcessoInterno processoInterno, Integer tipoEstado, Date data, User user);
	public String retornaNomeEstadoAtual(ProcessoInterno processoInterno);
	public EstadosProcesso retornaEstadoAtual(ProcessoInterno processoInterno);
	public boolean canEdit(ProcessoInterno processoInterno);
	public boolean canStart(ProcessoInterno processoInterno);
	public boolean canSuspend(ProcessoInterno processoInterno);
	public boolean canFinalize(ProcessoInterno processoInterno);
	public boolean canFaturar(ProcessoInterno processoInterno);
	public boolean canPagar(ProcessoInterno processo);
	public void atualizaEstadoProcesso(ProcessoInterno processo, User user);
	public ProcessoInterno buscaProcessoComServicos(Integer idProcesso);
}
