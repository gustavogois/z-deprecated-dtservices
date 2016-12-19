package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.EstadosProcesso;

@Local
public interface EstadoProcessoSBLocal extends GeneralSBLocal<EstadosProcesso>{
	
	public String retornaNomeEstado(Integer idEstado);
}
