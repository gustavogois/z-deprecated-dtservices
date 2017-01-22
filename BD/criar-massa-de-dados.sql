delete from tiposervico;
insert into tiposervico values (1, 'Tipo de Serviço 01', 10, '', null, '');
insert into tiposervico values (2, 'Tipo de Serviço 02', 20, '', null, '');
insert into tiposervico values (3, 'Tipo de Serviço 03', 30, '', null, '');
insert into tiposervico values (4, 'Tipo de Serviço 04', 40, '', null, '');
insert into tiposervico values (5, 'Tipo de Serviço 05', 50, '', null, '');

delete from solicitante;
insert into solicitante values (1, 'Solicitante 01', 'S1-', '', '', 
	'rua qualquer', '', '', '', 'cc qualquer', 'dd qualquer', null, '', 0);
insert into solicitante values (2, 'Solicitante 02', 'S2-', '', '', 
	'rua qualquer', '', '', '', 'cc qualquer', 'dd qualquer', null, '', 0);
insert into solicitante values (3, 'Solicitante 03', 'S3-', '', '', 
	'rua qualquer', '', '', '', 'cc qualquer', 'dd qualquer', null, '', 0);

-- Relaciona Solicitante 01 aos tipos de serviços 01, 02 e 03
delete from tiposervico_solicitante;
insert into tiposervico_solicitante values (1, 1, 1, 10, null, '');
insert into tiposervico_solicitante values (2, 2, 1, 20, null, '');
insert into tiposervico_solicitante values (3, 3, 1, 30, null, '');

-- Relaciona Solicitante 02 aos tipos de serviços 03, 04 e 05
insert into tiposervico_solicitante values (4, 3, 2, 35, null, '');
insert into tiposervico_solicitante values (5, 4, 2, 40, null, '');
insert into tiposervico_solicitante values (6, 5, 2, 60, null, '');

-- Processo externo 01, Solicitante 01
delete from processoexterno;
insert into processoexterno values (1, 1, '', null, '');

-- Processo externo 02, Solicitante 02
insert into processoexterno values (2, 2, '', null, '');

-- Processo interno 01, Processo externo 01 (Solicitante 01)
delete from processointerno;
delete from estadosprocesso;
insert into processointerno values (1, 1, 'S1-1', 0, '', null, null, '', '', 'João da Silva', null, '');
insert into estadosprocesso values (1, 1, 5, now(), null, '');
-- Processo interno 02, Processo externo 01 (Solicitante 01)
insert into processointerno values (2, 1, 'S1-2', 0, '', null, null, '', '', 'Luiza', null, '');
insert into estadosprocesso values (2, 2, 5, now(), null, '');
-- Processo interno 03, Processo externo 01 (Solicitante 01)
insert into processointerno values (3, 1, 'S1-3', 0, '', null, null, '', '', 'Maria', null, '');
insert into estadosprocesso values (3, 3, 5, now(), null, '');

-- Processo interno 04, Processo externo 02 (Solicitante 02)
insert into processointerno values (4, 2, 'S2-1', 0, '', null, null, '', '', 'Tiago', null, '');
insert into estadosprocesso values (4, 4, 5, now(), null, '');
-- Processo interno 05, Processo externo 02 (Solicitante 02)
insert into processointerno values (5, 2, 'S2-2', 0, '', null, null, '', '', 'Marta', null, '');
insert into estadosprocesso values (5, 5, 5, now(), null, '');

-- Serviço 01, ProcessoInterno 01, TSS 01 (solicitante 01, TS 01) - CRIADO
delete from servico;
delete from estadosservico;
-- alter table estadosservico change dtInicio dtInicio datetime;
insert into servico values (1, 1, 1, null, null, null, 18, 'Obs', null, null);
insert into estadosservico values (1, 1, 11, now(), null, '');
-- Serviço 02, ProcessoInterno 01, TSS 02 (solicitante 01, TS 02) 
insert into servico values (2, 1, 2, null, null, null, 20, 'Obs', null, null);
insert into estadosservico values (2, 2, 11, now(), null, '');
-- Serviço 03, ProcessoInterno 01, TSS 03 (solicitante 01, TS 03) 
insert into servico values (3, 1, 3, null, null, null, 25, 'Obs', null, null);
insert into estadosservico values (3, 3,11, now(), null, '');

-- Serviço 04, ProcessoInterno 02, TSS 04 (solicitante 01, TS 03) 
insert into servico values (4, 2, 4, null, null, null, 19, 'Obs', null, null);
insert into estadosservico values (4, 4, 11, now(), null, '');
-- Serviço 05, ProcessoInterno 02, TSS 05 (solicitante 01, TS 04) 
insert into servico values (5, 2, 5, null, null, null, 21, 'Obs', null, null);
insert into estadosservico values (5, 5, 11, now(), null, '');
-- Serviço 06, ProcessoInterno 02, TSS 06 (solicitante 01, TS 05) 
insert into servico values (6, 2, 5, null, null, null, 21, 'Obs', null, null);
insert into estadosservico values (6, 6, 11, now(), null, '');
