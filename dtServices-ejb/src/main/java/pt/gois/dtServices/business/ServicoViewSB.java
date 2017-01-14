package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.ServicoView;

@Stateless
public class ServicoViewSB extends GeneralSB<ServicoView> implements ServicoViewSBLocal{

	public ServicoViewSB() {
		super(ServicoView.class);
		// TODO Auto-generated constructor stub
	}
	
}
