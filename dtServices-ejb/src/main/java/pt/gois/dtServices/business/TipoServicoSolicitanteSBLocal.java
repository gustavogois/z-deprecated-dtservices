package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.TipoServicoSolicitante;

@Local
public interface TipoServicoSolicitanteSBLocal extends GeneralSBLocal<TipoServicoSolicitante>{
	
	public void addAllServices(Integer idSolicitante);
	
}
