package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Tiposervico;

@Stateless
public class TipoServicoSB extends GeneralSB<Tiposervico> implements TipoServicoSBLocal{

	public TipoServicoSB() {
		super(Tiposervico.class);
	}
}
