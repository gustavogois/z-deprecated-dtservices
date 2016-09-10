select * from tiposervico_solicitante tss
inner join solicitante sol on sol.id = tss.solicitanteId and tss.solicitanteId = 2
