package pt.gois.dtServices.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.TipoDeEstado;

@Stateless
public class TiposDeEstadoSB extends GeneralSB<TipoDeEstado> implements TipoDeEstadoSBLocal{

	public TiposDeEstadoSB() {
		super(TipoDeEstado.class);
	}
	
	public List<TipoDeEstado> findByGroup(Integer group) {
		TypedQuery<TipoDeEstado> query = getEM().createNamedQuery("findTipoDeEstadoByGroup", TipoDeEstado.class);
		query.setParameter("pGrupoId", group);
		return query.getResultList();
	}
	
	public List<TipoDeEstado> findNextStates(Integer idGroup, Integer idActualState) {
		
		ArrayList<TipoDeEstado> nextStates = new ArrayList<TipoDeEstado>();
		
		if(idGroup.equals(SERVICOS)) {
			if(idActualState.equals(SRV_CRIADO)) {
				nextStates.add(findById(SRV_EM_EXECUCAO));
				nextStates.add(findById(SRV_SUSPENSO));
			} else if(idActualState.equals(SRV_EM_EXECUCAO)) {
				nextStates.add(findById(SRV_FINALIZADO));
				nextStates.add(findById(SRV_SUSPENSO));
			} else if(idActualState.equals(SRV_SUSPENSO)) {
				nextStates.add(findById(SRV_EM_EXECUCAO));
				nextStates.add(findById(SRV_FINALIZADO));
			}
		}
			
		return nextStates;
	}
}
