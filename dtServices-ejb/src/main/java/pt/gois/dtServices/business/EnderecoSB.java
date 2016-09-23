package pt.gois.dtServices.business;

import java.util.ArrayList;
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

	@Override
	public List<EnderecoVW> getEnderecos(String term, boolean byCp) {
		term = term.replaceAll( "-", "" );
		List<EnderecoVW> result = new ArrayList<EnderecoVW>();
		String sql = "SELECT id, localidade, ruaPorta, complemento, codigoPostal, ad.cc, cc.nome ccNome, ad.dd, dd.nome ddNome "
					+ "FROM dt_services_cp.addressVW ad "
					+ "	JOIN dt_services_cp.ConcelhoVW cc ON ad.cc = cc.cc "
					+ "	JOIN dt_services_cp.distrito dd ON ad.dd = dd.dd "
					+ "WHERE MATCH(" + ( byCp? "codigoPostal1": "completo" ) + ") AGAINST('" + term + "*' IN BOOLEAN MODE) "
					+ "ORDER BY " + ( byCp? "codigoPostal ": "ruaPorta, complemento, localidade " )
					+ "LIMIT 20";
		Query query = getEM().createNativeQuery(sql);
		List<Object []> list = query.getResultList();
		for( Object [] line: list ){
			result.add(new EnderecoVW( line ));
		}
		return result;
	}
	
	
}
