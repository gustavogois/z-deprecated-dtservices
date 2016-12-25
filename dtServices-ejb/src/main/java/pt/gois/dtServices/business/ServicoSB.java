package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.Servico;

@Stateless
public class ServicoSB extends GeneralSB<Servico> implements ServicoSBLocal{

	@EJB
	EstadoServicoSBLocal sbEstadoServico;
	
	public ServicoSB() {
		super(Servico.class);
	}

	public Servico getServicoComEstados(Integer idServico) {
		Servico servico = null;
		String sql = 
				"select servico " +
				"from Servico servico " +
				"inner join fetch servico.estadosServicos " +
				"where servico.id = :idServico";
		TypedQuery<Servico> query = getEM().createQuery(sql, Servico.class);
		query.setParameter("idServico", idServico);
		try { 
			servico = query.getSingleResult();
		} catch(NoResultException e) {}
		return servico;
	}
	
	public List<EstadosServico> getEstadosServicos(Integer idServico) {
		Servico servico = getServicoComEstados(idServico);
		return servico != null ? servico.getEstadosServicos() : null;
	}
	
	public EstadosServico retornaEstadoAtual(Integer idServico) {
		List<EstadosServico> estadosServicoList = getEstadosServicos(idServico);
		if(estadosServicoList != null && estadosServicoList.size() > 0) {
			return estadosServicoList.get(estadosServicoList.size() - 1);
		} else {
			return null;
		}
	}

	@Override
	public void salvar(Servico servico, Integer idProcessoInterno) {
		
		if( servico.getId() != null ){
			
			save( servico );
			
		} else {
			
			create( servico );
		}
		
	}

	@Override
	public String retornaNomeEstadoAtual(Integer idServico) {
		EstadosServico estadoAtual = retornaEstadoAtual(idServico);
		if(estadoAtual != null) {
			return sbEstadoServico.retornaNomeEstado(estadoAtual.getId());
		} else {
			return "";
		}
	}
	
}
