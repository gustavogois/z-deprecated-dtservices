package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.KeySolicitanteProcInt;
import pt.gois.dtServices.entity.ProcessoInterno;

@Stateless
public class ProcessoInternoSB extends GeneralSB<ProcessoInterno> implements ProcessoInternoSBLocal{

	public ProcessoInternoSB() {
		super(ProcessoInterno.class);
	}

	@Override
	public void salvar(ProcessoInterno processoInterno) {
		
//		ProcessoExterno processoExterno = getEM().find(ProcessoExterno.class, processoInterno.getProcessoExterno().getId());
//		processoInterno.setProcessoExterno(processoExterno);
		
		processoInterno.setIdProcCliente(geraIdProcCliente(processoInterno));
		if( processoInterno.getId() != null ){
			
			save( processoInterno );
			
		} else {
			
			create( processoInterno );
		}
		
	}

	private String geraIdProcCliente(ProcessoInterno processoInterno) {
//		getEM().find(KeySolicitanteProcInt, primaryKey)
		return "";
	}
}
