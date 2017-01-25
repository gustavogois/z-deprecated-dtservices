package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.TipoEstado;

@Stateless
public class TipoEstadoSB extends GeneralSB<TipoEstado> implements TipoEstadoSBLocal {

	public TipoEstadoSB() {
		super(TipoEstado.class);
	}

	public List<TipoEstado> findByGroup(Integer group) {
		TypedQuery<TipoEstado> query = getEM().createNamedQuery("TipoEstado.findTipoEstadoByGroup", TipoEstado.class);
		query.setParameter("pGrupoId", group);
		return query.getResultList();
	}

}
