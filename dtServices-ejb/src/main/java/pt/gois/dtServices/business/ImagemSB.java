package pt.gois.dtServices.business;

import javax.ejb.Stateless;
import javax.persistence.Query;

import pt.gois.dtServices.entity.Imagem;;

@Stateless
public class ImagemSB extends GeneralSB<Imagem> implements ImagemSBLocal{

	public ImagemSB() {
		super(Imagem.class);
	}
	
	public byte [] getBin( Integer id ){
		Query query = getEM().createNamedQuery("Imagem.findById");
		query.setParameter("id", id);
		return ( (Imagem)query.getSingleResult() ).getImagem();
	}
}
