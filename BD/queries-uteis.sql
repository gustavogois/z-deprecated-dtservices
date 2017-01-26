-- ALTER TABLE t_solicitante ADD lastId int;
-- ALTER TABLE t_servico DROP dtInicio;
-- ALTER TABLE t_solicitante CHANGE lastId lastId int default 0;
-- RENAME TABLE t_log TO log;

-- Estados do Processo
select est.processoId, te.nome 
from processointerno pi
inner join estadosprocesso est on pi.id = est.processoId
inner join tipos_de_estado te on te.id = est.tipoId
where pi.idProcCliente like 'WTS6';

-- Serviços e Estados
select pi.id, pi.idProcCliente, serv.id, ts.nome, serv.valor, te2.nome
from processointerno pi
inner join servico serv on serv.processoId = pi.id
inner join tiposervico_solicitante tss on tss.id = serv.tipoServico_SolicitanteId
inner join tiposervico ts on ts.id = tss.tipoServicoId
inner join estadosservico ests on serv.id = ests.servicoId
inner join tipos_de_estado te2 on te2.id = ests.tipoId
where pi.idProcCliente like 'WTS6';

create or replace view V_SERVICO as
select serv.id, tipo.id as idTipo, tipo.nome as nomeTipo, tipoEst.id as idEstado, tipoEst.nome as nomeEstado, serv.valor, 
	est.dataInicio as dtEstado, serv.processoId, 
    datediff(
		(select proc.previsaoFim from t_processo proc where proc.id = serv.processoId), now()) as diasRestantes
from t_servico serv 
inner join 
	(select s.id, max(e.dataInicio) as maxdate
		from t_servico s
        inner join t_estado_servico e on e.servicoId = s.id
        group by id) groupserv
inner join t_tipo_servico tipo on tipo.id = serv.tipoServicoId
inner join t_estado_servico est on est.servicoId = serv.id
inner join t_ref_tipo_estado tipoEst on tipoEst.id = est.tipoEstadoId
where groupserv.id = serv.id
and groupserv.maxdate = est.dataInicio;
 
create or replace view V_PROCESSO as
select proc.id, proc.codExterno, proc.codInterno, tipoEst.id as idEstado, tipoEst.nome as nomeEstado, 
	est.dataInicio as dtEstado, proc.solicitanteId as idSolicitante, sol.nome as nomeSolicitante, proc.previsaoFim as previsaoFim
from t_processo proc 
inner join 
	(select p.id, max(e.dataInicio) as maxdate
	from t_processo p
	inner join t_estado_processo e on e.processoId = p.id
	group by id) groupproc
inner join t_estado_processo est on est.processoId = proc.id
inner join t_solicitante sol on sol.id = proc.solicitanteId
inner join t_ref_tipo_estado tipoEst on tipoEst.id = est.tipoEstadoId
where groupproc.id = proc.id
and groupproc.maxdate = est.dataInicio;

delete from t_solicitante
delete from t_estado_processo;
delete from t_imovel;
delete from t_processo;

select * from t_servico;
select * from t_estado_servico;
delete from t_servico;
delete from t_estado_servico;

select * from t_estado_processo;