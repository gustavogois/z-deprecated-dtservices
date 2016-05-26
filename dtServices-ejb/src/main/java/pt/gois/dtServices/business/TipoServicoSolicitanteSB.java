package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.TipoServicoSolicitante;

@Stateless
public class TipoServicoSolicitanteSB extends GeneralSB<TipoServicoSolicitante> 
	implements TipoServicoSolicitanteSBLocal{

	public TipoServicoSolicitanteSB() {
		super(TipoServicoSolicitante.class);
	}
}
