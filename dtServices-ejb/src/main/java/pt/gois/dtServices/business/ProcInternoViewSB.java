package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.ProcInternoView;

@Stateless
public class ProcInternoViewSB extends GeneralSB<ProcInternoView> implements ProcInternoViewSBLocal{
	

	public ProcInternoViewSB() {
		super(ProcInternoView.class);
	}
	
}
