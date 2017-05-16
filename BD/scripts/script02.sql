-- 16/05 Correção do erro no cadastro do Processo

ALTER TABLE t_processo MODIFY codExterno VARCHAR(30);

commit;