package pt.gois.dtServices.business;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.TiposDeEstado;
import pt.gois.dtServices.entity.User;

@Stateless
public class ServicoSB extends GeneralSB<Servico> implements ServicoSBLocal{

	@EJB
	EstadoServicoSBLocal sbEstadoServico;
	
	@EJB
	TipoServicoSolicitanteSBLocal sbTSS;
	
	@EJB
	ProcessoInternoSBLocal sbPI;
	
	public ServicoSB() {
		super(Servico.class);
	}

	public Servico findByIdWithEstadosServico(Integer id) {
		String sql = "select serv from Servico serv inner join fetch serv.estadosServicos where serv.id = :id";
		Query query = getEM().createQuery(sql);
		query.setParameter("id", id);
		return (Servico)query.getSingleResult();
		
	}
	
	public EstadosServico retornaEstadoAtual(Servico servico) {
		EstadosServico estadoServico = null;
		if(servico != null) {
			servico = findByIdWithEstadosServico(servico.getId());
			List<EstadosServico> estadosServicoList = servico.getEstadosServicos();
			if(estadosServicoList != null && estadosServicoList.size() > 0) {
				estadoServico = estadosServicoList.get(estadosServicoList.size() - 1);
			} 
		}
		return estadoServico;
	}

	@Override
	public void salvar(Servico servico, Integer tipoEstado, Date data, User user) {
		
		criarEstadoServico(servico, tipoEstado, data, user);
		
		if( servico.getId() != null ){
			
			save( servico );
			
		} else {
			
			create( servico );
		}
		
		sbPI.atualizaEstadoProcesso(sbPI.buscaProcessoComServicos(servico.getProcessoInterno().getId()), user);
		
	}

	@Override
	public String retornaNomeEstadoAtual(Servico servico) {
		EstadosServico estadoAtual = retornaEstadoAtual(servico);
		if(estadoAtual != null) {
			return sbEstadoServico.retornaNomeEstado(estadoAtual.getId());
		} else {
			return "";
		}
	}

	@Override
	public boolean canStart(Servico servico) {
		EstadosServico estadoAtual = retornaEstadoAtual(servico);
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.SRV_CRIADO) || 
				estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.SRV_SUSPENSO) ? true : false;
	}

	@Override
	public boolean canSuspend(Servico servico) {
		EstadosServico estadoAtual = retornaEstadoAtual(servico);
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.SRV_EM_EXECUCAO) ? true : false;
	}

	@Override
	public boolean canFinalize(Servico servico) {
		EstadosServico estadoAtual = retornaEstadoAtual(servico);
		return estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.SRV_EM_EXECUCAO) ||
				estadoAtual.getTiposDeEstado().getId().equals(TipoDeEstadoSBLocal.SRV_SUSPENSO);
	}

	private void criarEstadoServico(Servico servico, Integer tipoEstado, Date data, User user) {

		TiposDeEstado tipo = new TiposDeEstado();
		tipo.setId(tipoEstado);
		
		EstadosServico estadosServico = new EstadosServico();
		estadosServico.setTiposDeEstado(tipo);
		estadosServico.setUser(user);
		
		EstadosServico estadoAtual = retornaEstadoAtual(servico);
		if(estadoAtual != null) {
			estadoAtual.setDtFim(data);
		}
		estadosServico.setDtInicio(data);
		
		servico.addEstadosservico(estadosServico);
	}
	
}
