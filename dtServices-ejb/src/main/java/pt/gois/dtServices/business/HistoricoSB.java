package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.Historico;

@Stateless
public class HistoricoSB extends GeneralSB<Historico> implements HistoricoSBLocal{

	public HistoricoSB() {
		super(Historico.class);
	}

	@Override
	public List<Historico> findByObjectAndType(Integer idObject, Integer typeObject) {
		
		TypedQuery<Historico> query = getEM().createNamedQuery("Historico.findByObjectAndType", Historico.class);
		query.setParameter("pObjectId", idObject);
		query.setParameter("pType", typeObject);
		return query.getResultList();
	}
	
}
