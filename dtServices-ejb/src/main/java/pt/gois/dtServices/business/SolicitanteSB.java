package pt.gois.dtServices.business;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Solicitante;;

@Stateless
public class SolicitanteSB extends GeneralSB<Solicitante> implements SolicitanteSBLocal{

	public SolicitanteSB() {
		super(Solicitante.class);
	}
	
}
