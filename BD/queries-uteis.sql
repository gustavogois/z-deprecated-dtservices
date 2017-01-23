-- ALTER TABLE t_ref_tipo_estado ADD grupo varchar(20);
-- ALTER TABLE t_servico DROP dtInicio;
-- ALTER TABLE t_ref_tipo_estado CHANGE nome nome varchar(50) not null;
RENAME TABLE t_users TO users;

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

SELECT dt_services.t_processo.codExterno,
	dt_services.t_processo.codInterno,
	dt_services.t_servico.id,
	dt_services.t_servico.valor
FROM dt_services.t_servico
	INNER JOIN dt_services.t_processo ON 
	 dt_services.t_servico.processoId = dt_services.t_processo.id ;

SELECT dt_services.t_processo.codExterno AS codExterno,
	dt_services.t_processo.codInterno AS codInterno,
	dt_services.t_servico.id AS idServico,
	dt_services.t_servico.valor AS valor,
	dt_services.t_estado_servico.id AS idEstado,
	dt_services.t_estado_servico.dataInicio AS dataInicio
FROM dt_services.t_estado_servico
	INNER JOIN dt_services.t_servico ON 
	 dt_services.t_estado_servico.servicoId = dt_services.t_servico.id 
	INNER JOIN dt_services.t_processo ON 
	 dt_services.t_servico.processoId = dt_services.t_processo.id ;


SELECT dt_services.t_processo.`codExterno` AS codExterno,
	dt_services.t_processo.`codInterno` AS codInterno,
	dt_services.t_servico.id AS idServico,
	dt_services.t_servico.valor AS valor,
	dt_services.t_estado_servico.`dataInicio` AS dataInicio,
	dt_services.t_estado_servico.id AS idEstado,
	dt_services.t_tipo_servico.nome AS nomeServico,
	dt_services.t_ref_tipo_estado.nome AS nomeEstado,
	dt_services.v_processo.`nomeEstado` AS nomeEstProc
FROM dt_services.t_estado_servico
	INNER JOIN dt_services.t_servico ON 
	 dt_services.t_estado_servico.`servicoId` = dt_services.t_servico.id 
	INNER JOIN dt_services.t_processo ON 
	 dt_services.t_servico.`processoId` = dt_services.t_processo.id 
	INNER JOIN dt_services.t_tipo_servico ON 
	 dt_services.t_servico.`tipoServicoId` = dt_services.t_tipo_servico.id 
	INNER JOIN dt_services.t_ref_tipo_estado ON 
	 dt_services.t_estado_servico.`tipoEstadoId` = dt_services.t_ref_tipo_estado.id
	inner join dt_services.v_processo on dt_services.v_processo.id = dt_services.t_processo.id