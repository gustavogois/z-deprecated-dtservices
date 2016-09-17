insert into solicitante (id, nome, nif, telefone) values (null,"Banco Whitestar", "288653880", "243699012");
insert into solicitante (id, nome, nif, telefone) values (null,"Banco de Negócios", "734221954", "217788432");

insert into entidadedefacturacao (id, solicitanteId, nome, updateDt, updateUser, nif) 
	values (null, 1, "Caixa Econômica Montepio Geral", null, null, 222333444);
insert into entidadedefacturacao (id, solicitanteId, nome, updateDt, updateUser, nif) 
	values (null, 2, "Outra Entidade de Facturação ", null, null, 999888777);

insert into tiposervico (id, nome, valor, descricao) values (null, "Abertura de Porta (Fechadura Normal)", 10, "");
insert into tiposervico (id, nome, valor, descricao) values (null, "Abertura de Porta (Fechadura Especial)", 20, "");
insert into tiposervico (id, nome, valor, descricao) values (null, "Pintura", 5, "O preço está por metro quadrado.");