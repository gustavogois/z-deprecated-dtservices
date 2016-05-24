delete from solicitante;
insert into solicitante (id, nome, nif, telefone) values (1,"Banco Whitestar", "288653880", "243699012");
insert into solicitante (id, nome, nif, telefone) values (2,"Banco de Negócios", "734221954", "217788432");

delete from entidadedefacturacao;
insert into entidadedefacturacao (id, nome) values (3, "Caixa Econômica Montepio Geral");
insert into entidadedefacturacao (id, nome) values (4, "Outra Entidade de Facturação ");

delete from hibernate_sequence;
insert into hibernate_sequence values (5);


