delete from solicitante;
insert into solicitante (id, nome, nif, telefone) values (1,"Banco Whitestar", "288653880", "243699012");
insert into solicitante (id, nome, nif, telefone) values (2,"Banco de Negócios", "734221954", "217788432");

delete from entidadedefacturacao;
insert into entidadedefacturacao (id, nome) values (3, "Caixa Econômica Montepio Geral");
insert into entidadedefacturacao (id, nome) values (4, "Outra Entidade de Facturação ");

delete from servico;
insert into servico (id, nome, valor, solicitanteId) values (5, "Abertura de Porta (Fechadura Normal)", 10, 1);
insert into servico (id, nome, valor, solicitanteId) values (6, "Abertura de Porta (Fechadura Especial)", 20, 1);
insert into servico (id, nome, valor, solicitanteId) values (7, "Abertura de Porta (Fechadura Normal)", 20, 2);
insert into servico (id, nome, valor, solicitanteId) values (8, "Abertura de Porta (Fechadura Especial)", 40, 2);


delete from hibernate_sequence;
insert into hibernate_sequence values (9);

select * from solicitante;
select * from entidadedefacturacao;
select * from servico;


