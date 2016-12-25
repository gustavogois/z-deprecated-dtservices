package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.Servico;

@Local
public interface ServicoSBLocal extends GeneralSBLocal<Servico>{
	public void salvar(Servico servico, Integer idProcessoInterno);
	public String retornaNomeEstadoAtual(Integer idServico);
	public EstadosServico retornaEstadoAtual(Integer idServico);
}
