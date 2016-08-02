package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Endereco;

@Stateless
public class EnderecoSB extends GeneralSB<Endereco> implements EnderecoSBLocal{

	public EnderecoSB() {
		super(Endereco.class);
	}
}
