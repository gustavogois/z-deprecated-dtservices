package pt.gois.dtServices.business;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import pt.gois.dtServices.entity.Servico;
import pt.gois.dtServices.entity.User;

@Stateless
public class ServicoSB extends GeneralSB<Servico> implements ServicoSBLocal{

	@EJB
	ProcessoSBLocal sbPI;
	
	@EJB
	ServicoViewSBLocal sbServicoView;
	
	public ServicoSB() {
		super(Servico.class);
	}

	@Override
	public Servico findByIdWithEstadosServico(Integer id) {
		String sql = "select serv from Servico serv inner join fetch serv.estadoServicos where serv.id = :id";
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
		
		sbPI.checkStatusProcesso(servico.getProcesso().getId(), new Date(), 
				"Criado automaticamente pelo sistema por uma mudança de estado de um Serviço do Processo.", user);
		
	}

	@Override
	public boolean canStart(Servico servico) {
		if(servico == null || servico.getId() == null) {
			return false;
		} else {
			Integer idEstado = (sbServicoView.findById(servico.getId())).getIdEstado();
			return idEstado.equals(TipoEstadoSBLocal.SRV_CRIADO) || 
					idEstado.equals(TipoEstadoSBLocal.SRV_SUSPENSO) ? true : false;
			
		}
	}

	@Override
	public boolean canSuspend(Servico servico) {
		if(servico == null || servico.getId() == null) {
			return false;
		} else {
			Integer idEstado = (sbServicoView.findById(servico.getId())).getIdEstado();
			return idEstado.equals(TipoEstadoSBLocal.SRV_EM_EXECUCAO) ? true : false;
		}
	}

	@Override
	public boolean canFinalize(Servico servico) {
		if(servico == null || servico.getId() == null) {
			return false;
		} else {
			Integer idEstado = (sbServicoView.findById(servico.getId())).getIdEstado();
			return idEstado.equals(TipoEstadoSBLocal.SRV_EM_EXECUCAO) ||
					idEstado.equals(TipoEstadoSBLocal.SRV_SUSPENSO);
		}
	}

}
