package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.EstadosServico;

@Stateless
public class EstadoServicoSB extends GeneralSB<EstadosServico> implements EstadoServicoSBLocal{

	public EstadoServicoSB() {
		super(EstadosServico.class);
	}

}
