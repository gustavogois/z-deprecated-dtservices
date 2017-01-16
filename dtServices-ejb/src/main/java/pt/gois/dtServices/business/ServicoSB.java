package pt.gois.dtServices.business;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import pt.gois.dtServices.entity.EstadosServico;
import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.ServicoView;
import pt.gois.dtServices.entity.TiposDeEstado;
import pt.gois.dtServices.entity.User;

@Stateless
public class ServicoSB extends GeneralSB<Servico> implements ServicoSBLocal{

	@EJB
	ProcessoInternoSBLocal sbPI;
	
	@EJB
	ServicoViewSBLocal sbServicoView;
	
	public ServicoSB() {
		super(Servico.class);
	}

	@Override
	public Servico findByIdWithEstadosServico(Integer id) {
		String sql = "select serv from Servico serv inner join fetch serv.estadosServicos where serv.id = :id";
		Query query = getEM().createQuery(sql);
		query.setParameter("id", id);
		return (Servico)query.getSingleResult();
		
	}
	
	@Override
	public void salvar(Servico servico, User user) {
		
		if( servico.getId() != null ){
			
			save( servico );
			
		} else {
			
			create( servico );
		}
		
		sbPI.checkStatusProcessoInterno(servico.getProcessoInterno().getId(), user);
		
	}

	@Override
	public boolean canStart(Servico servico) {
		if(servico == null || servico.getId() == null) {
			return false;
		} else {
			Integer idTipo = (sbServicoView.findById(servico.getId())).getIdTipo();
			return idTipo.equals(TipoDeEstadoSBLocal.SRV_CRIADO) || 
					idTipo.equals(TipoDeEstadoSBLocal.SRV_SUSPENSO) ? true : false;
			
		}
	}

	@Override
	public boolean canSuspend(Servico servico) {
		if(servico == null || servico.getId() == null) {
			return false;
		} else {
			Integer idTipo = (sbServicoView.findById(servico.getId())).getIdTipo();
			return idTipo.equals(TipoDeEstadoSBLocal.SRV_EM_EXECUCAO) ? true : false;
		}
	}

	@Override
	public boolean canFinalize(Servico servico) {
		if(servico == null || servico.getId() == null) {
			return false;
		} else {
			Integer idTipo = (sbServicoView.findById(servico.getId())).getIdTipo();
			return idTipo.equals(TipoDeEstadoSBLocal.SRV_EM_EXECUCAO) ||
				idTipo.equals(TipoDeEstadoSBLocal.SRV_SUSPENSO);
		}
	}

}
