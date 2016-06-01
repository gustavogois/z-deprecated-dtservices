package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.TiposervicoSolicitante;

@Stateless
public class TipoServicoSolicitanteSB extends GeneralSB<TiposervicoSolicitante> 
	implements TipoServicoSolicitanteSBLocal{

	public TipoServicoSolicitanteSB() {
		super(TiposervicoSolicitante.class);
	}
}
