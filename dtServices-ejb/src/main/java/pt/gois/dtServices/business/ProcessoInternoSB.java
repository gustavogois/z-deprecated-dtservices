package pt.gois.dtServices.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.ProcessoExterno;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Solicitante;

@Stateless
public class ProcessoInternoSB extends GeneralSB<ProcessoInterno> implements ProcessoInternoSBLocal{
	
	@EJB
	SolicitanteSBLocal sbSolicitante;

	@EJB
	TipoDeEstadoSBLocal sbTipoDeEstado;
	
	public ProcessoInternoSB() {
		super(ProcessoInterno.class);
	}
	
	public ProcessoInterno findById(Object id){
		ProcessoInterno pi = super.findById(id);
		pi.getEstadosProcesso().size();
		return pi;
	}

	@Override
	public void salvar(ProcessoInterno processoInterno) {
		
		ProcessoExterno processoExterno = getEM().find(ProcessoExterno.class, processoInterno.getProcessoExterno().getId());
		processoInterno.setProcessoExterno(processoExterno);
		
		processoInterno.setIdProcCliente(geraIdProcCliente(processoInterno));
		
		if( processoInterno.getId() != null ){
			
			save( processoInterno );
			
		} else {
			
			EstadosProcesso criado = new EstadosProcesso();
			criado.setTiposDeEstado(sbTipoDeEstado.findById(TipoDeEstadoSBLocal.PI_CRIADO));
			criado.setProcessoInterno(processoInterno);
			
			processoInterno.addEstadosProcesso(criado);
			
			create( processoInterno );
		}
		
	}

	private String geraIdProcCliente(ProcessoInterno processoInterno) {
		Solicitante solicitante = sbSolicitante.
			findById(processoInterno.getProcessoExterno().getSolicitante().getId());
		solicitante.setChaveSolicitanteProcesso(solicitante.getChaveSolicitanteProcesso()+1);
		return solicitante.getSigla() + solicitante.getChaveSolicitanteProcesso();
	}
}
