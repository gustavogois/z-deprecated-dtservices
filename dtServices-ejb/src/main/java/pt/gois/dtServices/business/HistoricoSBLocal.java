package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.Historico;

@Local
public interface HistoricoSBLocal extends GeneralSBLocal<Historico>{
	public List<Historico> findByObjectAndType(Integer idObject, Integer typeObject);
}
