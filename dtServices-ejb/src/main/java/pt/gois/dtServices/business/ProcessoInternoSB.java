package pt.gois.dtServices.business;

import java.util.List;

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
	
	@EJB
	EstadoProcessoSBLocal sbEstadoProcesso;
	
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
		
		if( processoInterno.getId() != null ){
			
			save( processoInterno );
			
		} else {
			
			processoInterno.setIdProcCliente(geraIdProcCliente(processoInterno));
			
			create( processoInterno );
		}
		
	}

	private String geraIdProcCliente(ProcessoInterno processoInterno) {
		Solicitante solicitante = sbSolicitante.
			findById(processoInterno.getProcessoExterno().getSolicitante().getId());
		solicitante.setChaveSolicitanteProcesso(solicitante.getChaveSolicitanteProcesso()+1);
		return solicitante.getSigla() + solicitante.getChaveSolicitanteProcesso();
	}
	
	public String retornaNomeEstadoAtual(Integer idProcesso) {
		ProcessoInterno processoInterno = this.findById(idProcesso);
		EstadosProcesso estadoAtual = retornaEstadoAtual(processoInterno);
		if(estadoAtual != null) {
			return sbEstadoProcesso.retornaNomeEstado(estadoAtual.getId());
		} else {
			return "";
		}
	}
	
	public EstadosProcesso retornaEstadoAtual(ProcessoInterno processoInterno) {
		List<EstadosProcesso> estadosProcessoList = processoInterno.getEstadosProcesso();
		if(estadosProcessoList != null && estadosProcessoList.size() > 0) {
			return estadosProcessoList.get(estadosProcessoList.size() - 1);
		} else {
			return null;
		}
	}
}
