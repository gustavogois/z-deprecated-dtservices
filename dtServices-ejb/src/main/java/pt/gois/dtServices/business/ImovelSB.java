package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Imovel;;

@Stateless
public class ImovelSB extends GeneralSB<Imovel> implements ImovelSBLocal{

	public ImovelSB() {
		super(Imovel.class);
	}
}
