package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.ProcessoView;

@Stateless
public class ProcessoViewSB extends GeneralSB<ProcessoView> implements ProcessoViewSBLocal{
	

	public ProcessoViewSB() {
		super(ProcessoView.class);
	}
	
}
