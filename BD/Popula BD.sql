update hibernate_sequence set next_val=1;

insert into solicitante (id, nome, nif, telefone) values (1,"Banco Whitestar", "288653880", "243699012");
insert into solicitante (id, nome, nif, telefone) values (2,"Banco de Negócios", "734221954", "217788432");

insert into entidadedefacturacao (id, nome) values (3, "Caixa Econômica Montepio Geral");
insert into entidadedefacturacao (id, nome) values (4, "Outra Entidade de Facturação ");

insert into tiposervico (id, nome, valor, descricao) values (5, "Abertura de Porta (Fechadura Normal)", 10, "");
insert into tiposervico (id, nome, valor, descricao) values (6, "Abertura de Porta (Fechadura Especial)", 20, "");
insert into tiposervico (id, nome, valor, descricao) values (7, "Pintura", 5, "O preço está por metro quadrado.");

insert into grupo_tipos_estado (id, nome) values (8, "Processo externo");
insert into grupo_tipos_estado (id, nome) values (9, "Processo interno");
insert into grupo_tipos_estado (id, nome) values (10, "Serviços");

insert into tipos_de_estado (id, nome, grupo_estadoId) values (11, "Criado", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (12, "Em execução", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (13, "Finalizado", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (14, "Faturado", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (15, "Pago", 8);

insert into tipos_de_estado (id, nome, grupo_estadoId) values (16, "Criado", 9);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (17, "Em execução", 9);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (18, "Finalizado", 9);

insert into tipos_de_estado (id, nome, grupo_estadoId) values (19, "Criado", 10);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (20, "Em execução", 10);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (21, "Finalizado", 10);


