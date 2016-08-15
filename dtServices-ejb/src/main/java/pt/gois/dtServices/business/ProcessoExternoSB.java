package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pt.gois.dtServices.entity.Imagem;
import pt.gois.dtServices.entity.ProcessoExterno;

@Stateless
public class ProcessoExternoSB extends GeneralSB<ProcessoExterno> implements ProcessoExternoSBLocal{

	public ProcessoExternoSB() {
		super(ProcessoExterno.class);
	}
	
	public List<Imagem> getImages(Integer id){
		Query query = getEM().createNamedQuery("Imagem.findByImovel" );
		query.setParameter("imovelId", id);
		return query.getResultList();
	}
}
