package pt.gois.dtServices.business;

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
}
