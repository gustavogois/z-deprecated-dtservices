package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.User;

@Local
public interface ServicoSBLocal extends GeneralSBLocal<Servico>{
	public void salvar(Servico servico, User user);
	public boolean canStart(Servico servico);
	public boolean canSuspend(Servico servico);
	public boolean canFinalize(Servico servico);
	public Servico findByIdWithEstadosServico(Integer id);
}
