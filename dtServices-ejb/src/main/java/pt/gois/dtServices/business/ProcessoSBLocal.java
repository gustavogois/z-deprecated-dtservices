package pt.gois.dtServices.business;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.Imagem;
import pt.gois.dtServices.entity.Processo;
import pt.gois.dtServices.entity.ProcessoView;
import pt.gois.dtServices.entity.User;

@Local
public interface ProcessoSBLocal extends GeneralSBLocal<Processo>{
	public void salvar(Processo processoInterno, Integer tipoEstado, Calendar data, User user);
	public boolean canEdit(ProcessoView processoInterno);
	public boolean canSuspend(ProcessoView processoInterno);
	public boolean canFinalize(ProcessoView processoInterno);
	public boolean canFaturar(ProcessoView processoInterno);
	public boolean canPagar(ProcessoView processo);
	public void checkStatusProcesso(Integer idProcesso, User user);
	public Processo buscaProcessoComServicos(Integer idProcesso);
	public List<Imagem> getImages(Integer id);
}
