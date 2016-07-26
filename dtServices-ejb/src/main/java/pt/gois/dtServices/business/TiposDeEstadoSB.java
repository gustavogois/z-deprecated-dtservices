package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.TipoDeEstado;

@Stateless
public class TiposDeEstadoSB extends GeneralSB<TipoDeEstado> implements TipoDeEstadoSBLocal{

	public TiposDeEstadoSB() {
		super(TipoDeEstado.class);
	}
}
