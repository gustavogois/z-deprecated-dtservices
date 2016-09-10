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
			
		} else if(idGroup.equals(PROCESSO_INTERNO)) {
			
			if(idActualState.equals(PI_CRIADO)) {
				nextStates.add(findById(PI_EM_EXECUCAO));
				nextStates.add(findById(PI_SUSPENSO));
			} else if(idActualState.equals(PI_EM_EXECUCAO)) {
				nextStates.add(findById(PI_AGUARDANDO_FATURAMENTO));
				nextStates.add(findById(PI_SUSPENSO));
			} else if(idActualState.equals(PI_AGUARDANDO_FATURAMENTO)) {
				nextStates.add(findById(PI_AGUARDANDO_PAGAMENTO));
				nextStates.add(findById(PI_SUSPENSO));
			} else if(idActualState.equals(PI_AGUARDANDO_PAGAMENTO)) {
				nextStates.add(findById(PI_PAGO));
			} else if(idActualState.equals(PI_SUSPENSO)) {
				nextStates.add(findById(PI_EM_EXECUCAO));
				nextStates.add(findById(PI_AGUARDANDO_FATURAMENTO));
			}
		} 
			
		return nextStates;
	}
}
