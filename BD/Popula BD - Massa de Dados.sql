-- tiposervico (5, "Abertura de Porta (Fechadura Normal)", 10, "");
-- solicitante (1,"Banco Whitestar", "288653880", "243699012");
insert into tiposervico_solicitante (id, tipoServicoId, solicitanteId, valor) values (22, 5, 1, 12);

-- tiposervico (5, "Abertura de Porta (Fechadura Normal)", 10, "");
-- solicitante (2,"Banco de Negócios", "734221954", "217788432");
insert into tiposervico_solicitante (id, tipoServicoId, solicitanteId, valor) values (23, 5, 2, 12);

insert into imovel (id, inquilino, crp, endereco, codigoPostal) values (24, "João da Silva", 
	"Cascais 1ªCRP, número 7049, matriz predial 16920, fracção B", 
    "RUA ONDINA PEREIRA LOTE 3 175 RC ESQ, Buzano, São Domingos de Rana, Cascais", "2785-343");

-- solicitante (1,"Banco Whitestar", "288653880", "243699012");
insert into processocliente (id, codigo, descricao, dtFim, dtInicio, solicitanteId, estado_atual_Id)
			values
            (25, "PROC102030", "", null, null, 1, 16);
            
-- entidadedefacturacao (3, "Caixa Econômica Montepio Geral");
-- solicitante (1,"Banco Whitestar", "288653880", "243699012");
-- imovel (11, "João da Silva")
insert into processo(id, comChaves, estado, observacoes, entidadeFacturacaoId, processoClienteId, estado_atual_Id)
	values
    (26, true, 11, "", 3, 25, 11);

            
-- solicitante (1,"Banco Whitestar", "288653880", "243699012");
-- tiposervico (5, "Abertura de Porta (Fechadura Normal)", 10, "");
-- tiposervico_solicitante id = 22
-- processo 26            
insert into servico (id, dtCadastro, dtFim, dtInicio, observacoes, valor, processoId, tipoServico_SolicitanteId, estado_atual_Id)
			values
            (27, null, null, null, "", 50, 26, 22, 19);
            