package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pt.gois.dtServices.entity.Concelho;
import pt.gois.dtServices.entity.Distrito;
import pt.gois.dtServices.entity.EnderecoVW;

@Stateless
public class EnderecoSB extends GeneralSB<EnderecoVW> implements EnderecoSBLocal{

	public EnderecoSB() {
		super(EnderecoVW.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Distrito> getDistritos() {
		Query query = getEM().createNamedQuery("Distrito.findAll");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Concelho> getConcelhos(Distrito distrito) {
		Query query = getEM().createNamedQuery("Concelho.findAll");
		return query.getResultList();
	}
}
