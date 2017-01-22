/* Lembrar de executar o comando abaixo para a carga de endereços 
mysql -u root -p dt_services_cp < BD\cp\codigo_postal.sql
*/

delete from t_ref_tipo_estado;
insert into t_ref_tipo_estado values (1, "Criado", "Processo");
insert into t_ref_tipo_estado values (2, "Em execução", "Processo");
insert into t_ref_tipo_estado values (3, "Finalizado - Aguardando Faturamento", "Processo");
insert into t_ref_tipo_estado values (4, "Faturado - Aguardando Pagamento", "Processo");
insert into t_ref_tipo_estado values (9, "Finalizado", "Processo");
insert into t_ref_tipo_estado values (10, "Suspenso", "Processo");
insert into t_ref_tipo_estado values (11, "Criado", "Servico");
insert into t_ref_tipo_estado values (12, "Em execução", "Servico");
insert into t_ref_tipo_estado values (13, "Finalizado", "Servico");
insert into t_ref_tipo_estado values (14, "Suspenso", "Servico");

delete from t_tipo_user;
INSERT INTO t_tipo_user( id, descricao ) VALUES( 0, 'Root' );
INSERT INTO t_tipo_user( id, descricao ) VALUES( 1, 'Admin' );
INSERT INTO t_tipo_user( id, descricao ) VALUES( 2, 'Gerente' );

delete from t_users;
INSERT INTO t_users(id, tipoDeUserId, username, password, name, phone, expiryDate, createDt, updateDt,updateUser)
VALUES(null, 0, 'root','rootPass','root','',null, now(),now(),0);