package pt.gois.dtServices.business;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TiposDeEstado;

@Stateless
public class ProcessoInternoSB extends GeneralSB<ProcessoInterno> implements ProcessoInternoSBLocal{
	
	@EJB
	SolicitanteSBLocal sbSolicitante;

	@EJB
	TipoDeEstadoSBLocal sbTipoDeEstado;
	
	@EJB
	EstadoProcessoSBLocal sbEstadoProcesso;
	
	@EJB
	ServicoSBLocal sbServico;
	
	public ProcessoInternoSB() {
		super(ProcessoInterno.class);
	}
	
	public ProcessoInterno findById(Object id){
		ProcessoInterno pi = super.findById(id);
		pi.getEstadosProcesso().size();
		return pi;
	}

	private String geraIdProcCliente(ProcessoInterno processoInterno) {
		Solicitante solicitante = sbSolicitante.
			findById(processoInterno.getProcessoExterno().getSolicitante().getId());
		solicitante.setChaveSolicitanteProcesso(solicitante.getChaveSolicitanteProcesso()+1);
		return solicitante.getSigla() + solicitante.getChaveSolicitanteProcesso();
	}
	
	@Override
	public void salvar(ProcessoInterno processoInterno, Integer tipoEstado, Date data) {
		
		criarEstadoProcesso(processoInterno, tipoEstado, data);
		
		if( processoInterno.getId() != null ){
			
			save( processoInterno );
			
		} else {
			
			processoInterno.setIdProcCliente(geraIdProcCliente(processoInterno));
			create( processoInterno );
		}
	}
	
	private void criarEstadoProcesso(ProcessoInterno processoInterno, Integer tipoEstado, Date data) {

		TiposDeEstado tipo = new TiposDeEstado();
		tipo.setId(tipoEstado);
		
		EstadosProcesso estadosProcesso = new EstadosProcesso();
		estadosProcesso.setTiposDeEstado(tipo);
		
		EstadosProcesso estadoAtual = retornaEstadoAtual(processoInterno);
		if(estadoAtual != null) {
			estadoAtual.setDtFim(data);
		}
		estadosProcesso.setDtInicio(data);
		
		processoInterno.addEstadosprocesso(estadosProcesso);
	}

	@Override
	public String retornaNomeEstadoAtual(ProcessoInterno processo) {
		EstadosProcesso estadoAtual = retornaEstadoAtual(processo);
		if(estadoAtual != null) {
			return sbEstadoProcesso.retornaNomeEstado(estadoAtual.getId());
		} else {
			return "";
		}
	}

	@Override
	public EstadosProcesso retornaEstadoAtual(ProcessoInterno processo) {
		EstadosProcesso estadoProcesso = null;
		if(processo != null) {
			List<EstadosProcesso> estadosProcessoList = processo.getEstadosProcesso();
			if(estadosProcessoList != null && estadosProcessoList.size() > 0) {
				estadoProcesso = estadosProcessoList.get(estadosProcessoList.size() - 1);
			} 
		}
		return estadoProcesso;
	}

	@Override
	public boolean canStart(ProcessoInterno processo) {
		EstadosProcesso estadoAtual = retornaEstadoAtual(processo);
		
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.PI_CRIADO)
				&&
				existService(processo, TipoDeEstadoSBLocal.SRV_EM_EXECUCAO);
	}

	private boolean existService(ProcessoInterno processo, Integer tipo) {
		
		for (Servico servico : processo.getServicos()) {
			EstadosServico estadoAtual = sbServico.retornaEstadoAtual(servico);
			if(estadoAtual.getTiposDeEstado().getId().equals(tipo)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canSuspend(ProcessoInterno processo) {
		EstadosProcesso estadoAtual = retornaEstadoAtual(processo);
		
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.PI_EM_EXECUCAO)
				&&
				allServicesIn(processo, TipoDeEstadoSBLocal.SRV_SUSPENSO);
	}

	@Override
	public boolean canFinalize(ProcessoInterno processo) {
		EstadosProcesso estadoAtual = retornaEstadoAtual(processo);
		
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.PI_EM_EXECUCAO) 
				&&
				allServicesIn(processo, TipoDeEstadoSBLocal.SRV_FINALIZADO);
	}
	
	private boolean allServicesIn(ProcessoInterno processo, Integer tipo) {
		for (Servico servico : processo.getServicos()) {
			EstadosServico estadoAtual = sbServico.retornaEstadoAtual(servico);
			if(!estadoAtual.getTiposDeEstado().getId().equals(tipo)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean canFaturar(ProcessoInterno processo) {
		EstadosProcesso estadoAtual = retornaEstadoAtual(processo);
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.PI_AGUARDANDO_FATURAMENTO); 
	}
	
	@Override
	public boolean canPagar(ProcessoInterno processo) {
		EstadosProcesso estadoAtual = retornaEstadoAtual(processo);
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.PI_AGUARDANDO_PAGAMENTO); 
	}

	@Override
	public boolean canEdit(ProcessoInterno processo) {
		return true; 
	}
	
	@Override
	public void atualizaEstadoProcesso(ProcessoInterno processo) {
		if(canStart(processo)) {
			criarEstadoProcesso(processo, TipoDeEstadoSBLocal.PI_EM_EXECUCAO, getDataInicioExecucao(processo));
			save(processo);
			return;
		} else if(canFinalize(processo)) {
			criarEstadoProcesso(processo, TipoDeEstadoSBLocal.PI_AGUARDANDO_FATURAMENTO, getDataFimExecucao(processo));
			save(processo);
			return;
		} else if(canSuspend(processo)) {
			criarEstadoProcesso(processo, TipoDeEstadoSBLocal.PI_AGUARDANDO_FATURAMENTO, new Date());
			save(processo);
			return;
		}
		
	}
	
	private Date getDataFimExecucao(ProcessoInterno processo) {
		Date maiorData = new Date(0L);
		List<Servico> servicos = processo.getServicos();
		for (Servico servico : servicos) {
			List<EstadosServico> estadosServicos = servico.getEstadosServicos();
			for (EstadosServico estadosServico : estadosServicos) {
				if(estadosServico.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.SRV_EM_EXECUCAO)) {
					if(estadosServico.getDtFim().after(maiorData)) {
						maiorData = estadosServico.getDtFim();
					}
				}
			}
		}
		return maiorData;
	}

	private Date getDataInicioExecucao(ProcessoInterno processo) {
		List<Servico> servicos = processo.getServicos();
		for (Servico servico : servicos) {
			List<EstadosServico> estadosServicos = servico.getEstadosServicos();
			for (EstadosServico estadosServico : estadosServicos) {
				if(estadosServico.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.SRV_EM_EXECUCAO)) {
					return estadosServico.getDtInicio();
				}
			}
		}
		return null;
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
