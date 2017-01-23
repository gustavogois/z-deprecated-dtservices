delete from t_estado_servico;
delete from t_estado_processo;
delete from t_servico;
delete from t_processo;
delete from t_imovel;
delete from t_solicitante;
delete from t_tipo_servico;

insert into t_tipo_servico values (1, 'Tipo de serviço 01', 10,'');
insert into t_tipo_servico values (2, 'Tipo de serviço 02', 20,'');
insert into t_tipo_servico values (3, 'Tipo de serviço 03', 30,'');
insert into t_tipo_servico values (4, 'Tipo de serviço 04', 40,'');

insert into t_solicitante values (1, 'Solicitante 01', 'SL1-', '', '', 'rua qualquer', '', '', '', 'xxx', 'xxx', null, '');
insert into t_solicitante values (2, 'Solicitante 02', 'SL2-', '', '', 'rua qualquer', '', '', '', 'xxx', 'xxx', null, '');

insert into t_imovel values (1, 'casa', '', 'rua xyz', '', '', '', 'xxx', 'yyy', '', '', null, '');
insert into t_imovel values (2, 'apartamento', '', 'rua xyz', '', '', '', 'xxx', 'yyy', '', '', null, '');

insert into t_processo values (1, 1, 1, 'XYZ01', 'SL1-1', 1, '', null, null, null, null, '', 'João da Silva', null);
insert into t_processo values (2, 2, 2, 'MNP01', 'SL2-1', 1, '', null, null, null, null, '', 'João da Silva', null);

-- Serviços do Processo 01
insert into t_servico values (1, 1, 1, '2017-01-10 10:00:00', 13, '', null, '');
insert into t_servico values (2, 1, 2, '2017-01-10 10:00:10', 17, '', null, '');
-- Serviços do Processo 02
insert into t_servico values (3, 2, 3, '2017-01-11 10:00:00', 25, '', null, '');
insert into t_servico values (4, 2, 4, '2017-01-11 10:00:10', 30, '', null, '');

-- Processo 01
insert into t_estado_processo values (1, 1, 1, '2017-01-10 10:00:00', null, '', 3);
-- Passa o processo 1 para em execução, porque o serviço 01 entrou em execução
insert into t_estado_processo values (2, 2, 1, '2017-01-10 10:01:00', null, '', 3);
-- Processo 02
insert into t_estado_processo values (3, 1, 2, '2017-01-11 10:00:00', null, '', 3);
-- Passa o processo 2 para em execução, porque o serviço 03 entrou em execução
insert into t_estado_processo values (4, 2, 2, '2017-01-11 10:01:00', null, '', 3);

-- Estados dos serviços 01 e 02 do processo 01
-- estado criado do serviço 01
insert into t_estado_servico values (1, 1, 11, '2017-01-10 10:00:00', null, '', 3);
-- estado em execução do serviço 01
insert into t_estado_servico values (2, 1, 12, '2017-01-10 10:01:00', null, '', 3);
-- estado criado do serviço 02
insert into t_estado_servico values (3, 2, 11, '2017-01-10 10:02:00', null, '', 3);
-- estado em execução do serviço 02
insert into t_estado_servico values (4, 2, 12, '2017-01-10 10:02:10', null, '', 3);
-- estado suspenso do serviço 02
insert into t_estado_servico values (5, 2, 14, '2017-01-10 10:02:20', null, '', 3);

-- Estados dos serviços 03 e 04 do processo 02
-- estado criado do serviço 03
insert into t_estado_servico values (6, 3, 11, '2017-01-11 10:00:00', null, '', 3);
-- estado em execução do serviço 03
insert into t_estado_servico values (7, 3, 12, '2017-01-11 10:01:00', null, '', 3);
-- estado criado do serviço 04
insert into t_estado_servico values (8, 4, 11, '2017-01-11 10:02:00', null, '', 3);
-- estado em execução do serviço 04
insert into t_estado_servico values (9, 4, 12, '2017-01-11 10:02:10', null, '', 3);
-- estado suspenso do serviço 04
insert into t_estado_servico values (10, 4, 14, '2017-01-11 10:02:20', null, '', 3);