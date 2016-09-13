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

DROP FUNCTION searchAddress;

CREATE FUNCTION searchAddress(
  p_search varchar(30),
  p_full int
) RETURNS int  DETERMINISTIC
BEGIN
    DECLARE p_has int;
    SELECT 1 INTO p_has;
    
    IF p_full > 0 THEN
      select count(1) into p_has
      from addressvw
      where MATCH(completo) AGAINST(p_search);
    ELSE
      select count(1) into p_has
      from addressvw
      where MATCH(codigoPostal) AGAINST(p_search);
    END IF;
    
    return p_has;
END;

create or replace view concelhoVW as
Select dd, concat_WS( '-', dd, cc) cc, nome 
from concelho;
