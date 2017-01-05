-- Estados do Processo
select est.processoId, te.nome 
from processointerno pi
inner join estadosprocesso est on pi.id = est.processoId
inner join tipos_de_estado te on te.id = est.tipoId
where pi.idProcCliente like 'WTS6';

-- Serviços e Estados
select pi.id, pi.idProcCliente, te.nome, serv.id, serv.valor, ts.nome, te2.nome
from processointerno pi
inner join estadosprocesso est on pi.id = est.processoId
inner join tipos_de_estado te on te.id = est.tipoId
inner join servico serv on serv.processoId = pi.id
inner join tiposervico_solicitante tss on tss.id = serv.tipoServico_SolicitanteId
inner join tiposervico ts on ts.id = tss.tipoServicoId
inner join estadosservico ests on serv.id = ests.servicoId
inner join tipos_de_estado te2 on te2.id = ests.tipoId
where pi.idProcCliente like 'WTS6';

select * from tiposervico_solicitante;
SELECT pe.id as 'peid', sol.nome as 'solicitante', 
	pi.id as 'piid', pi.idProcCliente as 'sigla'
FROM processoexterno pe
	inner join solicitante sol ON 
	 pe.solicitanteId = sol.id
	 inner join processointerno pi on pi.processoExternoId = pe.id
order by peid, solicitante
-- group by pe.id, sol.nome