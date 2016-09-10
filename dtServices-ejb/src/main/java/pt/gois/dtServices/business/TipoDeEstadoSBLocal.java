package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.TipoDeEstado;

@Local
public interface TipoDeEstadoSBLocal extends GeneralSBLocal<TipoDeEstado>{
	
	// Grupos
	public static final Integer PROCESSO_EXTERNO = 1;
	public static final Integer PROCESSO_INTERNO = 2;
	public static final Integer SERVICOS = 3;
	
	// Processo Externo
	public static final Integer PE_CRIADO = 1;
	public static final Integer PE_EM_EXECUCAO = 2;
	public static final Integer PE_FINALIZADO = 3;
	public static final Integer PE_SUSPENSO = 4;
	
	// Processo Interno
	public static final Integer PI_CRIADO = 5;
	public static final Integer PI_EM_EXECUCAO = 6;
	public static final Integer PI_AGUARDANDO_FATURAMENTO = 7;
	public static final Integer PI_AGUARDANDO_PAGAMENTO = 8;
	public static final Integer PI_PAGO = 9;
	public static final Integer PI_SUSPENSO = 10;
	
	// Serviços
	public static final Integer SRV_CRIADO = 11;
	public static final Integer SRV_EM_EXECUCAO = 12;
	public static final Integer SRV_FINALIZADO = 13;
	public static final Integer SRV_SUSPENSO = 14;
	
	
	public List<TipoDeEstado> findByGroup(Integer group);
	
	public List<TipoDeEstado> findNextStates(Integer idGroup, Integer idActualState);

}
