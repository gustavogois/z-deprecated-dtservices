package pt.gois.dtServices.business;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.EstadosProcesso;

@Stateless
public class EstadoProcessoSB extends GeneralSB<EstadosProcesso> implements EstadoProcessoSBLocal{

	public EstadoProcessoSB() {
		super(EstadosProcesso.class);
	}

	@Override
	public String retornaNomeEstado(Integer idEstado) {
		
		String sQuery = "select e.tiposDeEstado.nome from EstadosProcesso e where e.id = :idEstado";
		TypedQuery<String> query = getEM().createQuery(sQuery, String.class);
		query.setParameter("idEstado", idEstado);
		String nome = query.getSingleResult();
		return nome;
	}

}
