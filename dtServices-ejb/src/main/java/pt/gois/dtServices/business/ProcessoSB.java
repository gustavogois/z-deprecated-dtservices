package pt.gois.dtServices.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.EstadoProcesso;
import pt.gois.dtServices.entity.Imagem;
import pt.gois.dtServices.entity.Processo;
import pt.gois.dtServices.entity.ProcessoView;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoEstado;
import pt.gois.dtServices.entity.User;

@Stateless
public class ProcessoSB extends GeneralSB<Processo> implements ProcessoSBLocal{
	
	@EJB
	ProcessoViewSBLocal sbProcView;
	
	@EJB
	SolicitanteSBLocal sbSolicitante;

	public ProcessoSB() {
		super(Processo.class);
	}
	
	private String geraIdProcCliente(Processo Processo) {
		Solicitante solicitante = sbSolicitante.
			findById(Processo.getSolicitante().getId());
		
		solicitante.setLastId(solicitante.getLastId()+1);
		
		return solicitante.getSigla() + solicitante.getLastId();
	}
	
	public List<Imagem> getImages(Integer id){
		Query query = getEM().createNamedQuery("Imagem.findByImovel" );
		query.setParameter("imovelId", id);
		return query.getResultList();
	}
	
	@Override
	public void salvar(Processo processo, Integer tipoEstado, Date data, User user) {
		
		if( processo.getId() != null ){
			
			save( processo );
			
		} else {
			
			processo.setCodInterno(geraIdProcCliente(processo));
			
			EstadoProcesso estadoProcesso = new EstadoProcesso();
			estadoProcesso.setDataInicio(data);
			estadoProcesso.setProcesso(processo);
			estadoProcesso.setTipoEstado(new TipoEstado(tipoEstado));
			estadoProcesso.setUserId(user.getId());
			processo.addEstadoProcesso(estadoProcesso);
			
			create( processo );
			create( processo.getImovel());
		}
	}
	
	@Override
	public boolean canSuspend(ProcessoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		
		return idEstadoAtual.equals(TipoEstadoSBLocal.PI_EM_EXECUCAO)
				&&
				checkAllStatesServicesIn(processo, TipoEstadoSBLocal.SRV_SUSPENSO);
	}

	@Override
	public boolean canFinalize(ProcessoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		
		return idEstadoAtual.equals(TipoEstadoSBLocal.PI_EM_EXECUCAO) 
				&&
				checkAllStatesServicesIn(processo, TipoEstadoSBLocal.SRV_FINALIZADO);
	}
	
	@Override
	public boolean canFaturar(ProcessoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		return idEstadoAtual.equals(TipoEstadoSBLocal.PI_AGUARDANDO_FATURAMENTO); 
	}
	
	@Override
	public boolean canPagar(ProcessoView processo) {
		Integer idEstadoAtual = processo.getIdEstado();
		return idEstadoAtual.equals(TipoEstadoSBLocal.PI_AGUARDANDO_PAGAMENTO); 
	}

	@Override
	public boolean canEdit(ProcessoView processo) {
		return true; 
	}
	
	@Override
	public void checkStatusProcesso(Integer idProcesso, User user) {
		
		ProcessoView procView = sbProcView.findByID(ProcessoView.class, idProcesso);
		Integer idTipoEstadoAtualProc = procView.getIdEstado();
		
		if(idTipoEstadoAtualProc.equals(TipoEstadoSBLocal.PI_CRIADO)) {
			if(checkAllStatesServicesIn(procView, TipoEstadoSBLocal.SRV_EM_EXECUCAO)) {
				criaEstadoProcesso(procView, TipoEstadoSBLocal.PI_EM_EXECUCAO, user);
				return;
			} else if(checkAllStatesServicesIn(procView, TipoEstadoSBLocal.SRV_SUSPENSO)) {
				criaEstadoProcesso(procView, TipoEstadoSBLocal.PI_SUSPENSO, user);
			}
		} else if(idTipoEstadoAtualProc.equals(TipoEstadoSBLocal.PI_EM_EXECUCAO)) {
			if(checkAllStatesServicesIn(procView, TipoEstadoSBLocal.SRV_SUSPENSO)) {
				criaEstadoProcesso(procView, TipoEstadoSBLocal.PI_SUSPENSO, user);
			} 
		}
	}
	
	private void criaEstadoProcesso(ProcessoView procView, Integer idTipo, User user) {
		Processo pi = buscaProcessoComServicos(procView.getId());
		EstadoProcesso novoEstado = new EstadoProcesso();
		novoEstado.setId(idTipo);
		novoEstado.setDataInicio(new Date());
		novoEstado.setProcesso(pi);
		novoEstado.setUserId(user.getId());
		
		TipoEstado tipo = new TipoEstado(idTipo);
		novoEstado.setTipoEstado(tipo);

		pi.getEstadoProcessos().add(novoEstado);
		save(pi);
	}
	
	private boolean checkAllStatesServicesIn(ProcessoView proc, Integer idEstado) {
		Processo pi = buscaProcessoComServicos(proc.getId());
		for (EstadoProcesso estado : pi.getEstadoProcessos()) {
			if(!estado.getTipoEstado().getId().equals(idEstado)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public Processo buscaProcessoComServicos(Integer idProcesso) {
		Processo processo = null;
		String sql = "select pi from Processo pi inner join fetch pi.estadosProcesso where pi.id = :id";
		TypedQuery<Processo> query = getEM().createQuery(sql, Processo.class);
		query.setParameter("id", idProcesso);
		try {
			processo = query.getSingleResult();
		} catch(NoResultException e) {}
		return processo;
	}


}
