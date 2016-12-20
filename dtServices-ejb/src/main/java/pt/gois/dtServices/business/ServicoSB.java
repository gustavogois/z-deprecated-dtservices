package pt.gois.dtServices.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.ProcessoInterno;
import pt.gois.dtServices.entity.Servico;

@Stateless
public class ServicoSB extends GeneralSB<Servico> implements ServicoSBLocal{

	@EJB
	EstadoServicoSBLocal sbEstadoServico;
	
	public ServicoSB() {
		super(Servico.class);
	}

	@Override
	public void salvar(Servico servico, Integer idProcessoInterno) {
		
		if( servico.getId() != null ){
			
			save( servico );
			
		} else {
			
			ProcessoInterno processoInterno = getEM().find(ProcessoInterno.class, idProcessoInterno);
			servico.setProcessoInterno(processoInterno);
			
			create( servico );
		}
		
	}

	@Override
	public String retornaNomeEstadoAtual(Integer idServico) {
		Servico servico = this.findById(idServico);
		EstadosServico estadoAtual = servico.retornaEstadoAtual();
		if(estadoAtual != null) {
			return sbEstadoServico.retornaNomeEstado(estadoAtual.getId());
		} else {
			return "";
		}
	}
}
