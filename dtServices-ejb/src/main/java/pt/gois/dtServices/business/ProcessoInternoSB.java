package pt.gois.dtServices.business;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.ProcInternoView;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TiposDeEstado;
import pt.gois.dtServices.entity.User;

@Stateless
public class ProcessoInternoSB extends GeneralSB<ProcessoInterno> implements ProcessoInternoSBLocal{
	
	@EJB
	ProcInternoViewSBLocal sbProcView;
	
	@EJB
	SolicitanteSBLocal sbSolicitante;

	public ProcessoInternoSB() {
		super(ProcessoInterno.class);
	}
	
	private String geraIdProcCliente(ProcessoInterno processoInterno) {
		Solicitante solicitante = sbSolicitante.
			findById(processoInterno.getProcessoExterno().getSolicitante().getId());
		solicitante.setChaveSolicitanteProcesso(solicitante.getChaveSolicitanteProcesso()+1);
		return solicitante.getSigla() + solicitante.getChaveSolicitanteProcesso();
	}
	
	@Override
	public void salvar(ProcessoInterno processoInterno, Integer tipoEstado, Calendar data, User user) {
		
		if( processoInterno.getId() != null ){
			
			save( processoInterno );
			
		} else {
			
			processoInterno.setIdProcCliente(geraIdProcCliente(processoInterno));
			create( processoInterno );
		}
	}
	
	@Override
	public boolean canSuspend(ProcInternoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		
		return idEstadoAtual.equals(TipoDeEstadoSBLocal.PI_EM_EXECUCAO)
				&&
				checkAllStatesServicesIn(processo, TipoDeEstadoSBLocal.SRV_SUSPENSO);
	}

	@Override
	public boolean canFinalize(ProcInternoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		
		return idEstadoAtual.equals(TipoDeEstadoSBLocal.PI_EM_EXECUCAO) 
				&&
				checkAllStatesServicesIn(processo, TipoDeEstadoSBLocal.SRV_FINALIZADO);
	}
	
	@Override
	public boolean canFaturar(ProcInternoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		return idEstadoAtual.equals(TipoDeEstadoSBLocal.PI_AGUARDANDO_FATURAMENTO); 
	}
	
	@Override
	public boolean canPagar(ProcInternoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		return idEstadoAtual.equals(TipoDeEstadoSBLocal.PI_AGUARDANDO_PAGAMENTO); 
	}

	@Override
	public boolean canEdit(ProcInternoView processo) {
		return true; 
	}
	
	@Override
	public void checkStatusProcessoInterno(Integer idProcesso, User user) {
		
		ProcInternoView procView = sbProcView.findByID(ProcInternoView.class, idProcesso);
		Integer idTipoEstadoAtualProc = procView.getIdEstado();
		
		if(idTipoEstadoAtualProc.equals(TipoDeEstadoSBLocal.PI_CRIADO)) {
			if(checkAllStatesServicesIn(procView, TipoDeEstadoSBLocal.SRV_EM_EXECUCAO)) {
				criaEstadoProcesso(procView, TipoDeEstadoSBLocal.PI_EM_EXECUCAO, user);
				return;
			} else if(checkAllStatesServicesIn(procView, TipoDeEstadoSBLocal.SRV_SUSPENSO)) {
				criaEstadoProcesso(procView, TipoDeEstadoSBLocal.PI_SUSPENSO, user);
			}
		} else if(idTipoEstadoAtualProc.equals(TipoDeEstadoSBLocal.PI_EM_EXECUCAO)) {
			if(checkAllStatesServicesIn(procView, TipoDeEstadoSBLocal.SRV_SUSPENSO)) {
				criaEstadoProcesso(procView, TipoDeEstadoSBLocal.PI_SUSPENSO, user);
			} 
		}
	}
	
	private void criaEstadoProcesso(ProcInternoView procView, Integer idTipo, User user) {
		ProcessoInterno pi = buscaProcessoComServicos(procView.getId());
		EstadosProcesso novoEstado = new EstadosProcesso();
		novoEstado.setId(idTipo);
		novoEstado.setDtInicio(Calendar.getInstance());
		novoEstado.setProcessoInterno(pi);
		novoEstado.setUser(user);
		
		TiposDeEstado tipo = new TiposDeEstado(idTipo);
		novoEstado.setTiposDeEstado(tipo);

		pi.getEstadosProcesso().add(novoEstado);
		save(pi);
	}
	
	private boolean checkAllStatesServicesIn(ProcInternoView proc, Integer idEstado) {
		ProcessoInterno pi = buscaProcessoComServicos(proc.getId());
		for (EstadosProcesso estado : pi.getEstadosProcesso()) {
			if(!estado.getTiposDeEstado().getId().equals(idEstado)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public ProcessoInterno buscaProcessoComServicos(Integer idProcesso) {
		ProcessoInterno processo = null;
		String sql = "select pi from ProcessoInterno pi inner join fetch pi.estadosProcesso where pi.id = :id";
		TypedQuery<ProcessoInterno> query = getEM().createQuery(sql, ProcessoInterno.class);
		query.setParameter("id", idProcesso);
		try {
			processo = query.getSingleResult();
		} catch(NoResultException e) {}
		return processo;
	}


}
