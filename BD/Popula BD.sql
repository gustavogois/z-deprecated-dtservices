

insert into solicitante (id, nome, nif, telefone) values (null,"Banco Whitestar", "288653880", "243699012");
insert into solicitante (id, nome, nif, telefone) values (null,"Banco de Negócios", "734221954", "217788432");

insert into entidadedefacturacao (id, nome) values (null, "Caixa Econômica Montepio Geral");
insert into entidadedefacturacao (id, nome) values (null, "Outra Entidade de Facturação ");

insert into tiposervico (id, nome, valor, descricao) values (null, "Abertura de Porta (Fechadura Normal)", 10, "");
insert into tiposervico (id, nome, valor, descricao) values (null, "Abertura de Porta (Fechadura Especial)", 20, "");
insert into tiposervico (id, nome, valor, descricao) values (null, "Pintura", 5, "O preço está por metro quadrado.");

insert into grupo_tipos_estado (id, nome) values (null, "Processo externo");
insert into grupo_tipos_estado (id, nome) values (null, "Processo interno");
insert into grupo_tipos_estado (id, nome) values (null, "Serviços");

insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Criado", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Em execução", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Finalizado", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Faturado", 8);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Pago", 8);

insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Criado", 9);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Em execução", 9);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Finalizado", 9);

insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Criado", 10);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Em execução", 10);
insert into tipos_de_estado (id, nome, grupo_estadoId) values (null, "Finalizado", 10);


