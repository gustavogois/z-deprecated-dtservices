Para carregar as localidades usar o comando abaixo:

mysql -u USER_ADMIN -p DATABASE_NAME < codigo_postal.sql

Este script criara as tabelas caso nao existam e carga dos dados;

Script só para referencia:

DROP TABLE IF EXISTS AddressVW;

CREATE TABLE AddressVW AS
SELECT substr( id, 1, length(id) ) id, dd, concat_ws( '-', dd,cc ) cc, localidadeNome localidade, ruaPorta, complemento, cp codigoPostal, concat_WS( ' ', ruaporta, complemento, cp, localidadeNome ) completo, now() updateDt, 'root' updateUser 
FROM( 
SELECT @rowid := @rowid + 1 id
  , dd.dd dd
  , cc.cc cc
  , lo.localidade localidadeNome
  , concat( concat_WS( ' ', lo.art_tipo, lo.pri_prep, lo.art_titulo, lo.art_desig ), concat_WS( ' ', trim(lo.porta), trim(lo.troco) ) ) ruaPorta
  , concat_WS( ' ', lo.art_local, cc.nome, dd.nome ) complemento
  , concat_WS( '-', lo.codigoPostal4, lo.codigoPostal3 ) cp
FROM 
  distrito dd
  JOIN concelho cc ON dd.dd = cc.dd
  JOIN localidade lo ON cc.cc = lo.cc AND lo.dd = cc.dd
  , (SELECT @rowid:=0 ) AS init ) address
;

alter table AddressVW MODIFY updateDt DATE default null;

CREATE FULLTEXT INDEX addressvw_idx ON addressvw(completo);
CREATE FULLTEXT INDEX addressvw_cpidx ON addressvw(codigoPostal);

create or replace view concelhoVW as
Select dd, concat_WS( '-', dd, cc) cc, nome 
from concelho;
