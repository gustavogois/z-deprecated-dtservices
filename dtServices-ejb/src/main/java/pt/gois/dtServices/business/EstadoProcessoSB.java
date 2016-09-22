package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.EntidadeDeFacturacao;
import pt.gois.dtServices.entity.EstadosProcesso;
import pt.gois.dtServices.entity.Solicitante;

@Stateless
public class EstadoProcessoSB extends GeneralSB<EstadosProcesso> implements EstadoProcessoSBLocal{

	public EstadoProcessoSB() {
		super(EstadosProcesso.class);
	}

}
