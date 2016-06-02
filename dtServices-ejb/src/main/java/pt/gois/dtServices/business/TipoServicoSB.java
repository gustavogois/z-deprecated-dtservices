package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.TipoServico;

@Stateless
public class TipoServicoSB extends GeneralSB<TipoServico> implements TipoServicoSBLocal{

	public TipoServicoSB() {
		super(TipoServico.class);
	}
}
