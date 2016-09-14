package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.Concelho;
import pt.gois.dtServices.entity.Distrito;
import pt.gois.dtServices.entity.EnderecoVW;

@Local
public interface EnderecoSBLocal extends GeneralSBLocal<EnderecoVW>{
	List<Distrito> getDistritos();
	List<Concelho> getConcelhos(Distrito distrito);
	
	List<EnderecoVW> getEnderecos(String query, boolean byCp);
}
