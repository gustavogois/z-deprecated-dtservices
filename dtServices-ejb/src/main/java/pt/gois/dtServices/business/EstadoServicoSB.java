package pt.gois.dtServices.business;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import pt.gois.dtServices.entity.EstadosServico;

@Stateless
public class EstadoServicoSB extends GeneralSB<EstadosServico> implements EstadoServicoSBLocal{

	public EstadoServicoSB() {
		super(EstadosServico.class);
	}

	@Override
	public String retornaNomeEstado(Integer idEstado) {
		String sQuery = "select e.tiposDeEstado.nome from EstadosServico e where e.id = :idEstado";
		TypedQuery<String> query = getEM().createQuery(sQuery, String.class);
		query.setParameter("idEstado", idEstado);
		String nome = query.getSingleResult();
		return nome;
	}

}
