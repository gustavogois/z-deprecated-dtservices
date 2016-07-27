package pt.gois.dtServices.business;

import java.util.List;

import javax.ejb.Local;

import pt.gois.dtServices.entity.TipoDeEstado;

@Local
public interface TipoDeEstadoSBLocal extends GeneralSBLocal<TipoDeEstado>{
	
	public static final Integer CRIADO = 1;
	public static final Integer EM_EXECUCAO = 2;
	public static final Integer FINALIZADO = 3;
	public static final Integer FATURADO = 4;
	public static final Integer PAGO = 5;
	
	public static final Integer PROCESSO_EXTERNO = 1;
	public static final Integer PROCESSO_INTERNO = 2;
	public static final Integer SERVICOS = 3;
	
	public List<TipoDeEstado> findByGroup(Integer group);

}
