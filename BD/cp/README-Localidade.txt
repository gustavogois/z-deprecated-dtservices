Para carregar as localidades usar o comando abaixo:

mysql -u USER_ADMIN -p DATABASE_NAME < codigo_postal.sql

Este script criara as tabelas caso nao existam e carga dos dados;


select *
from 
  distrito dd
  join concelho cc on dd.dd = cc.dd
  join localidade lo on cc.cc = lo.cc and lo.dd = cc.dd
;

create index localidade_DDCC_idx on localidade(dd,cc);
