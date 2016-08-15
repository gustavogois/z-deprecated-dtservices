package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.Imagem;
import pt.gois.dtServices.entity.ProcessoExterno;

@Local
public interface ProcessoExternoSBLocal extends GeneralSBLocal<ProcessoExterno>{
	
	List<Imagem> getImages(Integer id);
}
