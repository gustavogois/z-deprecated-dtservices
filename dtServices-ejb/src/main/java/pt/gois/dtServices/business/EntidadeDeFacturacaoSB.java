package pt.gois.dtServices.business;

import javax.ejb.Stateless;

import pt.gois.dtServices.entity.EntidadeDeFacturacao;
import pt.gois.dtServices.entity.Solicitante;

@Stateless
public class EntidadeDeFacturacaoSB extends GeneralSB<EntidadeDeFacturacao> implements EntidadeDeFacturacaoSBLocal{

	public EntidadeDeFacturacaoSB() {
		super(EntidadeDeFacturacao.class);
	}

	@Override
	public Integer salvar(EntidadeDeFacturacao entidade, Integer idSolicitante) {
		// TODO Auto-generated method stub
		
		if( entidade.getId() != null ){
			
			idSolicitante = entidade.getSolicitante().getId();

			save( entidade );
			
		} else {
			
			Solicitante solicitante = getEM().find(Solicitante.class, idSolicitante);
			solicitante.getEntidadeDeFacturacao().add(entidade);
			entidade.setSolicitante(solicitante);
			
			create( entidade );
			
		}
		
		return idSolicitante;

	}
}
