package pt.gois.dtServices.business;

import java.util.List;

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
	
	public EstadosServico retornaEstadoAtual(Servico servico) {
		List<EstadosServico> estadosServicoList = servico.getEstadosServicos();
		if(estadosServicoList != null && estadosServicoList.size() > 0) {
			return estadosServicoList.get(estadosServicoList.size() - 1);
		} else {
			return null;
		}
	}
}
