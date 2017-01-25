package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.TipoEstado;

@Local
public interface TipoEstadoSBLocal extends GeneralSBLocal<TipoEstado>{
	
	// Processo
	public static final Integer PI_CRIADO = 1;
	public static final Integer PI_EM_EXECUCAO = 2;
	public static final Integer PI_AGUARDANDO_FATURAMENTO = 3;
	public static final Integer PI_AGUARDANDO_PAGAMENTO = 4;
	public static final Integer PI_PAGO = 9;
	public static final Integer PI_SUSPENSO = 10;
	
	// Serviços
	public static final Integer SRV_CRIADO = 11;
	public static final Integer SRV_EM_EXECUCAO = 12;
	public static final Integer SRV_FINALIZADO = 13;
	public static final Integer SRV_SUSPENSO = 14;
	
	
	public List<TipoEstado> findByGroup(Integer group);
	
	public List<TipoEstado> findNextStates(Integer idGroup, Integer idActualState);

}
