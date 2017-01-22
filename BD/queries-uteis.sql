-- ALTER TABLE t_ref_tipo_estado ADD grupo varchar(20);
-- ALTER TABLE t_servico DROP dtInicio;
-- ALTER TABLE t_ref_tipo_estado CHANGE nome nome varchar(50) not null;

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
	est.dataInicio as dtEstado, serv.processoId
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
select pi.id, pi.codExterno, pi.codInterno, tipoEst.id as idEstado, tipoEst.nome as nomeEstado, 
	est.dataInicio as dtEstado, pi.processoExternoId
from t_processo pi 
inner join 
	(select p.id, max(e.dataInicio) as maxdate
	from t_processo p
	inner join t_estado_processo e on e.processoId = p.id
	group by id) groupproc
inner join t_estado_processo est on est.processoId = pi.id
inner join t_ref_tipo_estado tipoEst on tipoEst.id = est.tipoId
where groupproc.id = pi.id
and groupproc.maxdate = est.dtInicio;

alter table estadosprocesso change dtFim dtFim datetime;

desc t_estado_processo;
