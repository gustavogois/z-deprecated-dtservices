package pt.gois.dtServices.business;

import javax.ejb.EJBException;
import javax.ejb.Stateless;

import pt.gois.dtServices.entity.TipoServico;

@Stateless
public class TipoServicoSB extends GeneralSB<TipoServico> implements TipoServicoSBLocal{

	public TipoServicoSB() {
		super(TipoServico.class);
	}

	@Override
	public void delete(TipoServico tipoServico) {
		try {
			super.delete(tipoServico);
		} catch (Exception e) {
			throw (EJBException) new EJBException(e).initCause(e);
		}
		
	}
}
