package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Processo;

@Stateless
public class ProcessoSB extends GeneralSB<Processo> implements ProcessoSBLocal{

	public ProcessoSB() {
		super(Processo.class);
	}
}
