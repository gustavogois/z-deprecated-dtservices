-- tiposervico (5, "Abertura de Porta (Fechadura Normal)", 10, "");
-- solicitante (1,"Banco Whitestar", "288653880", "243699012");
insert into tiposervico_solicitante (id, tipoServicoId, solicitanteId, valor) values (9, 5, 1, 12);

-- tiposervico (5, "Abertura de Porta (Fechadura Normal)", 10, "");
-- solicitante (2,"Banco de Negócios", "734221954", "217788432");
insert into tiposervico_solicitante (id, tipoServicoId, solicitanteId, valor) values (10, 5, 2, 12);

insert into imovel (id, inquilino, crp, endereco, codigoPostal) values (11, "João da Silva", 
	"Cascais 1ªCRP, número 7049, matriz predial 16920, fracção B", 
    "RUA ONDINA PEREIRA LOTE 3 175 RC ESQ, Buzano, São Domingos de Rana, Cascais", "2785-343");

-- entidadedefacturacao (3, "Caixa Econômica Montepio Geral");
-- solicitante (1,"Banco Whitestar", "288653880", "243699012");
-- imovel (11, "João da Silva")
insert into processo (id, entidadeFacturacaoId, solicitanteId, imovelId, comChaves, observacoes, dtSolicitacao, 
                      dtCadastro, dtInicioExecucao, dtFinalizacao, dtFaturamento, dtRecebimento, estado)
			values
            (12, 3, 1, 11, true, "", null, null, null, null, null, null, 0);
            
-- solicitante (1,"Banco Whitestar", "288653880", "243699012");
-- tiposervico (5, "Abertura de Porta (Fechadura Normal)", 10, "");
-- tiposervico_solicitante id = 9
-- processo 12            
insert into servico (id, processoId, tipoServico_SolicitanteId, estado, dtCadastro, dtInicio, dtFim, valor)
			values
            (13, 12, 9, 0, null, null, null, 50);
            
update hibernate_sequence set next_val=14;

-- insert into imovel (id, inquilino, crp, endereco, codigoPostal) values (10, "Maria Pereira", 
-- 	"Arroja 3aCRP, número 1020, matriz predial 8845, fracção C", 
--    "Praceta das Orquídeas, 3, 4e, Famões, Castro, Odivelas", "2531-831");
-- insert into imovel (id, inquilino, crp, endereco, codigoPostal) values (11, "Marcos Castro", 
-- 	"Colinas 6aCRP, número 200, matriz predial 3457, fracção A", 
--     "Praça de Portugal, 4, 4D, Plata, Lurdes, Odivelas", "2698-221");
