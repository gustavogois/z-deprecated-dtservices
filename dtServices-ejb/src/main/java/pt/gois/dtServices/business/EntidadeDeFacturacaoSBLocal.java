package pt.gois.dtServices.business;

import javax.ejb.Local;

import pt.gois.dtServices.entity.EntidadeDeFacturacao;

@Local
public interface EntidadeDeFacturacaoSBLocal extends GeneralSBLocal<EntidadeDeFacturacao>{
	public Integer salvar(EntidadeDeFacturacao entidade, Integer idSolicitante);
}
