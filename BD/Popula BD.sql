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

delete from imovel;
insert into imovel (id, inquilino, crp, endereco, codigoPostal) values (9, "João da Silva", 
	"Cascais 1ªCRP, número 7049, matriz predial 16920, fracção B", 
    "RUA ONDINA PEREIRA LOTE 3 175 RC ESQ, Buzano, São Domingos de Rana, Cascais - 2785-343", "");
insert into imovel (id, inquilino, crp, endereco, codigoPostal) values (10, "Maria Pereira", 
	"Arroja 3aCRP, número 1020, matriz predial 8845, fracção C", 
    "Praceta das Orquídeas, 3, 4e, Famões, Castro, Odivelas - 2531-831", "");
insert into imovel (id, inquilino, crp, endereco, codigoPostal) values (11, "Marcos Castro", 
	"Colinas 6aCRP, número 200, matriz predial 3457, fracção A", 
    "Praça de Portugal, 4, 4D, Plata, Lurdes, Odivelas - 2698-221", "");

delete from hibernate_sequence;
insert into hibernate_sequence values (12);

