package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.Imagem;;

@Local
public interface ImagemSBLocal extends GeneralSBLocal<Imagem>{
	byte [] getBin( Integer id );
}
