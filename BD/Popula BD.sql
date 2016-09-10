/* Lembrar de executar o comando abaixo para a carga de endereços 
mysql -u root -p dt_services < BD\cp\codigo_postal.sql
*/

insert into solicitante (id, nome, nif, telefone) values (null,"Banco Whitestar", "288653880", "243699012");
insert into solicitante (id, nome, nif, telefone) values (null,"Banco de Negócios", "734221954", "217788432");

insert into entidadedefacturacao (id, nome) values (null, "Caixa Econômica Montepio Geral");
insert into entidadedefacturacao (id, nome) values (null, "Outra Entidade de Facturação ");

insert into tiposervico (id, nome, valor, descricao) values (null, "Abertura de Porta (Fechadura Normal)", 10, "");
insert into tiposervico (id, nome, valor, descricao) values (null, "Abertura de Porta (Fechadura Especial)", 20, "");
insert into tiposervico (id, nome, valor, descricao) values (null, "Pintura", 5, "O preço está por metro quadrado.");

insert into grupo_tipos_estado (id, nome) values (1, "Processo externo");
insert into grupo_tipos_estado (id, nome) values (2, "Processo interno");
insert into grupo_tipos_estado (id, nome) values (3, "Serviços");

insert into tipos_de_estado (id, nome, grupo_estadoId) values (1, "Criado", 1);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (2, "Em execução", 1);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (3, "Finalizado", 1);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (4, "Suspenso", 1);

insert into tipos_de_estado (id, nome, grupo_estadoId) values (5, "Criado", 2);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (6, "Em execução", 2);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (7, "Finalizado - Aguardando Faturamento", 2);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (8, "Faturado - Aguardando Pagamento", 2);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (9, "Finalizado", 2);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (10, "Suspenso", 2);

insert into tipos_de_estado (id, nome, grupo_estadoId) values (11, "Criado", 3);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (12, "Em execução", 3);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (13, "Finalizado", 3);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (14, "Suspenso", 3);

INSERT INTO tipoDeUser( id, descricao ) VALUES( 0, 'Root' );
INSERT INTO tipoDeUser( id, descricao ) VALUES( 1, 'Admin' );
INSERT INTO tipoDeUser( id, descricao ) VALUES( 2, 'Gerente' );

INSERT INTO users(id, tipoDeUserId, username, password, name, phone, expiryDate, createDt, updateDt,updateUser)
VALUES(null, 0, 'root','rootPass','root','',null, now(),now(),0);