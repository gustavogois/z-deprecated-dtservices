package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.ProcessoInterno;

@Stateless
public class ProcessoInternoSB extends GeneralSB<ProcessoInterno> implements ProcessoInternoSBLocal{

	public ProcessoInternoSB() {
		super(ProcessoInterno.class);
	}

}
