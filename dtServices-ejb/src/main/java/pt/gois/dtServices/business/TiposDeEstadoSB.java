package pt.gois.dtServices.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.TiposDeEstado;

@Stateless
public class TiposDeEstadoSB extends GeneralSB<TiposDeEstado> implements TipoDeEstadoSBLocal {

	public TiposDeEstadoSB() {
		super(TiposDeEstado.class);
	}

	public List<TiposDeEstado> findByGroup(Integer group) {
		TypedQuery<TiposDeEstado> query = getEM().createNamedQuery("TiposDeEstado.findTiposDeEstadoByGroup", TiposDeEstado.class);
		query.setParameter("pGrupoId", group);
		return query.getResultList();
	}

	public List<TiposDeEstado> findNextStates(Integer idGroup, Integer idActualState) {

		ArrayList<TiposDeEstado> nextStates = new ArrayList<TiposDeEstado>();

		if (idGroup.equals(SERVICOS)) {

			if (idActualState.equals(SRV_CRIADO)) {
				nextStates.add(findById(SRV_EM_EXECUCAO));
				nextStates.add(findById(SRV_SUSPENSO));
			} else if (idActualState.equals(SRV_EM_EXECUCAO)) {
				nextStates.add(findById(SRV_FINALIZADO));
				nextStates.add(findById(SRV_SUSPENSO));
			} else if (idActualState.equals(SRV_SUSPENSO)) {
				nextStates.add(findById(SRV_EM_EXECUCAO));
				nextStates.add(findById(SRV_FINALIZADO));
			}

		} else if (idGroup.equals(PROCESSO_INTERNO)) {

			if (idActualState.equals(PI_CRIADO)) {
				nextStates.add(findById(PI_EM_EXECUCAO));
				nextStates.add(findById(PI_SUSPENSO));
			} else if (idActualState.equals(PI_EM_EXECUCAO)) {
				nextStates.add(findById(PI_AGUARDANDO_FATURAMENTO));
				nextStates.add(findById(PI_SUSPENSO));
			} else if (idActualState.equals(PI_AGUARDANDO_FATURAMENTO)) {
				nextStates.add(findById(PI_AGUARDANDO_PAGAMENTO));
				nextStates.add(findById(PI_SUSPENSO));
			} else if (idActualState.equals(PI_AGUARDANDO_PAGAMENTO)) {
				nextStates.add(findById(PI_PAGO));
			} else if (idActualState.equals(PI_SUSPENSO)) {
				nextStates.add(findById(PI_EM_EXECUCAO));
				nextStates.add(findById(PI_AGUARDANDO_FATURAMENTO));
			}
		} else if (idGroup.equals(PROCESSO_EXTERNO)) {

			if (idActualState.equals(PE_CRIADO)) {
				nextStates.add(findById(PE_EM_EXECUCAO));
				nextStates.add(findById(PE_SUSPENSO));
			} else if (idActualState.equals(PE_EM_EXECUCAO)) {
				nextStates.add(findById(PE_FINALIZADO));
				nextStates.add(findById(PE_SUSPENSO));
			} else if (idActualState.equals(PE_SUSPENSO)) {
				nextStates.add(findById(PE_EM_EXECUCAO));
				nextStates.add(findById(PE_FINALIZADO));
			}
		}

		return nextStates;
	}
}
