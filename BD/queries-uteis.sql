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
select serv.id, tipo.nome as nomeTipo, tipoEst.nome as nomeEstado, serv.valor, max(est.dtInicio), serv.processoId
from servico serv 
inner join tiposervico_solicitante tss on tss.id = serv.tipoServico_SolicitanteId
inner join tiposervico tipo on tipo.id = tss.tipoServicoId
inner join estadosservico est on est.servicoId = serv.id
inner join tipos_de_estado tipoEst on tipoEst.id = est.tipoId;

select * from V_SERVICO;

select * from estadosservico;
alter table estadosservico change dtFim dtFim datetime;