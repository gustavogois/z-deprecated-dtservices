package pt.gois.dtServices.business;

import java.util.Date;

import javax.ejb.Local;

import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.Servico;

@Local
public interface ServicoSBLocal extends GeneralSBLocal<Servico>{
	public void salvar(Servico servico, Integer tipoEstado, Date data);
	public String retornaNomeEstadoAtual(Servico servico);
	public EstadosServico retornaEstadoAtual(Servico servico);
	public boolean canStart(Servico servico);
	public boolean canSuspend(Servico servico);
	public boolean canFinalize(Servico servico);
}
