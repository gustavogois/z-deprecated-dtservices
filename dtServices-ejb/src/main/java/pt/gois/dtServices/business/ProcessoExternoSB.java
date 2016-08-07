package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.ProcessoExterno;

@Stateless
public class ProcessoExternoSB extends GeneralSB<ProcessoExterno> implements ProcessoExternoSBLocal{

	public ProcessoExternoSB() {
		super(ProcessoExterno.class);
	}
}
