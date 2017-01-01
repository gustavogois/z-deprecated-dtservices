package pt.gois.dtServices.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pt.gois.dtServices.entity.Solicitante;
import pt.gois.dtServices.entity.TipoServico;
import pt.gois.dtServices.entity.TipoServicoSolicitante;

@Stateless
public class TipoServicoSolicitanteSB extends GeneralSB<TipoServicoSolicitante> 
	implements TipoServicoSolicitanteSBLocal{

	@EJB
	SolicitanteSBLocal sbSolicitante;
	
	@EJB
	TipoServicoSBLocal sbTipoDeServico;

	public TipoServicoSolicitanteSB() {
		super(TipoServicoSolicitante.class);
	}

	@Override
	public void addAllServices(Integer idSolicitante) {
		Solicitante solicitante = sbSolicitante.findById(idSolicitante);
		List<TipoServico> tiposDeServicos = sbTipoDeServico.findAll();
		ArrayList<TipoServicoSolicitante> tiposServicoSolicitante = new ArrayList<TipoServicoSolicitante>();
		for (TipoServico tipoServico : tiposDeServicos) {
			TipoServicoSolicitante tipoServicoSolicitante = new TipoServicoSolicitante();
			tipoServicoSolicitante.setSolicitante(solicitante);
			tipoServicoSolicitante.setTipoServico(tipoServico);
			tipoServicoSolicitante.setValor(tipoServico.getValor());
			save(tipoServicoSolicitante);
		}
	}
}
