-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema dt_services
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dt_services` ;

-- -----------------------------------------------------
-- Schema dt_services
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dt_services` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema dt_services_cp
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dt_services_cp` ;

-- -----------------------------------------------------
-- Schema dt_services_cp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dt_services_cp` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `dt_services` ;

-- -----------------------------------------------------
-- Table `dt_services`.`addressvw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`addressvw` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`addressvw` (
  `id` VARCHAR(23) CHARACTER SET 'cp850' NULL DEFAULT NULL,
  `dd` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  `cc` VARCHAR(241) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `localidade` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  `ruaPorta` TEXT CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `complemento` VARCHAR(362) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `codigoPostal` VARCHAR(8) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `codigoPostal1` VARCHAR(7) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `completo` LONGTEXT CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `updateDt` DATE NULL DEFAULT NULL,
  `updateUser` VARCHAR(4) CHARACTER SET 'cp850' NOT NULL DEFAULT '',
  INDEX `AddressVW_idx` (`id` ASC),
  FULLTEXT INDEX `addressvw_compx` (`completo` ASC),
  FULLTEXT INDEX `addressvw_cpx` (`codigoPostal1` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`concelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`concelho` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`concelho` (
  `DD` VARCHAR(120) NOT NULL,
  `CC` VARCHAR(120) NOT NULL,
  `nome` VARCHAR(120) NOT NULL,
  INDEX `concelho_idx` (`CC` ASC),
  INDEX `concelho_idx1` (`DD` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`concelhovw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`concelhovw` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`concelhovw` (
  `dd` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  `cc` VARCHAR(241) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `nome` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  INDEX `concelhoVW_idx` (`cc` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`distrito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`distrito` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`distrito` (
  `DD` VARCHAR(120) NOT NULL,
  `nome` VARCHAR(120) NOT NULL,
  UNIQUE INDEX `distrito_idx` (`nome` ASC),
  INDEX `distrito_idx1` (`DD` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`localidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`localidade` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`localidade` (
  `DD` VARCHAR(120) NOT NULL,
  `CC` VARCHAR(120) NOT NULL,
  `LLLL` VARCHAR(120) NOT NULL,
  `Localidade` VARCHAR(120) NOT NULL,
  `ART_COD` VARCHAR(120) NOT NULL,
  `ART_TIPO` VARCHAR(120) NOT NULL,
  `PRI_PREP` VARCHAR(120) NOT NULL,
  `ART_TITULO` VARCHAR(120) NOT NULL,
  `SEG_PREP` VARCHAR(120) NOT NULL,
  `ART_DESIG` VARCHAR(120) NOT NULL,
  `ART_LOCAL` VARCHAR(120) NOT NULL,
  `troco` VARCHAR(120) NOT NULL,
  `porta` VARCHAR(120) NOT NULL,
  `cliente` VARCHAR(120) NOT NULL,
  `codigoPostal4` VARCHAR(4) NOT NULL,
  `codigoPostal3` VARCHAR(3) NOT NULL,
  `codigoPostalDesignacao` VARCHAR(120) NOT NULL,
  INDEX `localidade_idx` (`codigoPostal4` ASC, `codigoPostal3` ASC),
  INDEX `localidade_idx1` (`Localidade` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_imovel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_imovel` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_imovel` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipoImovel` VARCHAR(60) NULL DEFAULT NULL,
  `crp` VARCHAR(60) NULL DEFAULT NULL COMMENT 'Certidão de Registro Predial.',
  `ruaPorta` VARCHAR(100) NOT NULL,
  `complemento` VARCHAR(120) NULL DEFAULT NULL,
  `localidade` VARCHAR(120) NULL DEFAULT NULL,
  `codigoPostal` VARCHAR(9) NULL DEFAULT NULL,
  `cc` VARCHAR(120) NOT NULL,
  `dd` VARCHAR(120) NOT NULL,
  `latitude` VARCHAR(50) NULL DEFAULT NULL,
  `longitude` VARCHAR(50) NULL DEFAULT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_solicitante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_solicitante` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_solicitante` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL COMMENT 'Nome do Solicitante',
  `sigla` VARCHAR(5) NOT NULL,
  `nif` VARCHAR(10) NULL DEFAULT NULL COMMENT 'Número do contribuinte.',
  `telefone` VARCHAR(10) NULL DEFAULT NULL COMMENT 'Telefone do Solicitante.',
  `ruaPorta` VARCHAR(100) NOT NULL,
  `complemento` VARCHAR(120) NULL DEFAULT NULL,
  `localidade` VARCHAR(120) NULL DEFAULT NULL,
  `codigoPostal` VARCHAR(9) NULL DEFAULT NULL,
  `cc` VARCHAR(120) NOT NULL,
  `dd` VARCHAR(120) NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_processo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_processo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_processo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `solicitanteId` INT(11) NOT NULL,
  `imovelId` INT(11) NOT NULL,
  `codExterno` VARCHAR(9) NOT NULL,
  `codInterno` VARCHAR(9) NOT NULL,
  `comChaves` TINYINT(1) NULL DEFAULT NULL,
  `observacoes` VARCHAR(300) NULL DEFAULT NULL,
  `previsaoInicio` DATETIME NULL DEFAULT NULL,
  `previsaoFim` DATETIME NULL DEFAULT NULL,
  `dtInicio` DATETIME NULL DEFAULT NULL,
  `dtFim` DATETIME NULL DEFAULT NULL,
  `numeroChave` VARCHAR(10) NULL DEFAULT NULL,
  `nomeRequisitante` VARCHAR(60) NOT NULL,
  `dtCadastro` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_T_PROCESSO_T_SOLICITANTE1_idx` (`solicitanteId` ASC),
  INDEX `fk_T_PROCESSO_T_IMOVEL1_idx` (`imovelId` ASC),
  CONSTRAINT `fk_T_PROCESSO_T_IMOVEL1`
    FOREIGN KEY (`imovelId`)
    REFERENCES `dt_services`.`t_imovel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_PROCESSO_T_SOLICITANTE1`
    FOREIGN KEY (`solicitanteId`)
    REFERENCES `dt_services`.`t_solicitante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_ref_tipo_estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_ref_tipo_estado` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_ref_tipo_estado` (
  `id` INT(11) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `grupo` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_estado_processo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_estado_processo` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_estado_processo` (
  `id` INT(11) NOT NULL,
  `tipoEstadoId` INT(11) NOT NULL,
  `processoId` INT(11) NOT NULL,
  `dataInicio` DATETIME NULL DEFAULT NULL,
  `dataFim` DATETIME NULL DEFAULT NULL,
  `observacoes` VARCHAR(300) NULL DEFAULT NULL,
  `userId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_estado_processo_t_ref_tipo_servico1_idx` (`tipoEstadoId` ASC),
  INDEX `fk_t_estado_processo_t_processo1_idx` (`processoId` ASC),
  CONSTRAINT `fk_t_estado_processo_t_processo1`
    FOREIGN KEY (`processoId`)
    REFERENCES `dt_services`.`t_processo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_estado_processo_t_ref_tipo_servico1`
    FOREIGN KEY (`tipoEstadoId`)
    REFERENCES `dt_services`.`t_ref_tipo_estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_tipo_servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_tipo_servico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_tipo_servico` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `descricao` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_servico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_servico` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `processoId` INT(11) NOT NULL,
  `tipoServicoId` INT(11) NOT NULL,
  `dtCadastro` DATE NULL DEFAULT NULL,
  `valor` DOUBLE NOT NULL,
  `observacoes` VARCHAR(300) NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `processo_servico_fk` (`processoId` ASC),
  INDEX `fk_T_SERVICO_T_TIPO_SERVICO1_idx` (`tipoServicoId` ASC),
  CONSTRAINT `fk_T_SERVICO_T_TIPO_SERVICO1`
    FOREIGN KEY (`tipoServicoId`)
    REFERENCES `dt_services`.`t_tipo_servico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `processo_servico_fk`
    FOREIGN KEY (`processoId`)
    REFERENCES `dt_services`.`t_processo` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_estado_servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_estado_servico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_estado_servico` (
  `id` INT(11) NOT NULL,
  `servicoId` INT(11) NOT NULL,
  `tipoEstadoId` INT(11) NOT NULL,
  `dataInicio` DATETIME NULL DEFAULT NULL,
  `dataFim` DATETIME NULL DEFAULT NULL,
  `observacoes` VARCHAR(300) NULL DEFAULT NULL,
  `userId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_t_estado_t_ref_tipo_servico1_idx` (`tipoEstadoId` ASC),
  INDEX `fk_t_estado_servico_t_servico1_idx` (`servicoId` ASC),
  CONSTRAINT `fk_t_estado_servico_t_servico1`
    FOREIGN KEY (`servicoId`)
    REFERENCES `dt_services`.`t_servico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_estado_t_ref_tipo_servico1`
    FOREIGN KEY (`tipoEstadoId`)
    REFERENCES `dt_services`.`t_ref_tipo_estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_historico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_historico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_historico` (
  `id` INT(11) NOT NULL,
  `tipoObjetoId` INT(11) NULL DEFAULT NULL,
  `objetoId` INT(11) NULL DEFAULT NULL,
  `data` DATETIME NULL DEFAULT NULL,
  `descricao` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_imagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_imagem` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_imagem` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `imagem` LONGBLOB NOT NULL,
  `descricao` VARCHAR(120) NOT NULL,
  `filename` VARCHAR(100) NOT NULL,
  `size` INT(11) NOT NULL,
  `mimeType` VARCHAR(20) NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_imagem_imovel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_imagem_imovel` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_imagem_imovel` (
  `imovelId` INT(11) NOT NULL,
  `imagemId` INT(11) NOT NULL,
  INDEX `fk_T_IMAGEM_IMOVEL_T_IMOVEL1_idx` (`imovelId` ASC),
  INDEX `fk_T_IMAGEM_IMOVEL_T_IMAGEM1_idx` (`imagemId` ASC),
  CONSTRAINT `fk_T_IMAGEM_IMOVEL_T_IMAGEM1`
    FOREIGN KEY (`imagemId`)
    REFERENCES `dt_services`.`t_imagem` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_IMAGEM_IMOVEL_T_IMOVEL1`
    FOREIGN KEY (`imovelId`)
    REFERENCES `dt_services`.`t_imovel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_imagem_servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_imagem_servico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_imagem_servico` (
  `servicoId` INT(11) NOT NULL,
  `imagemId` INT(11) NOT NULL,
  PRIMARY KEY (`servicoId`, `imagemId`),
  INDEX `imagem_imagem_servico_fk` (`imagemId` ASC),
  CONSTRAINT `imagem_imagem_servico_fk`
    FOREIGN KEY (`imagemId`)
    REFERENCES `dt_services`.`t_imagem` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `servico_imagem_servico_fk`
    FOREIGN KEY (`servicoId`)
    REFERENCES `dt_services`.`t_servico` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_log` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_log` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `level` VARCHAR(10) NOT NULL,
  `codigo` VARCHAR(10) NULL DEFAULT NULL,
  `table_1` VARCHAR(10) NULL DEFAULT NULL,
  `tableId` INT(11) NULL DEFAULT NULL,
  `descricao` LONGTEXT NOT NULL,
  `updateDt` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updateUser` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 381
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_tipo_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_tipo_user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_tipo_user` (
  `id` INT(11) NOT NULL,
  `descricao` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`t_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`t_users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`t_users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipoDeUserId` INT(11) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `name` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `expiryDate` DATE NULL DEFAULT NULL,
  `locked` TINYINT(1) NOT NULL DEFAULT '0',
  `createDt` VARCHAR(20) NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_idx` (`username` ASC),
  INDEX `tipodeuser_users_fk` (`tipoDeUserId` ASC),
  CONSTRAINT `tipodeuser_users_fk`
    FOREIGN KEY (`tipoDeUserId`)
    REFERENCES `dt_services`.`t_tipo_user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;
USE `dt_services_cp` ;

-- -----------------------------------------------------
-- Table `dt_services_cp`.`addressvw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services_cp`.`addressvw` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services_cp`.`addressvw` (
  `id` VARCHAR(23) CHARACTER SET 'cp850' NULL DEFAULT NULL,
  `dd` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  `cc` VARCHAR(241) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `localidade` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  `ruaPorta` TEXT CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `complemento` VARCHAR(362) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `codigoPostal` VARCHAR(8) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `codigoPostal1` VARCHAR(7) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `completo` LONGTEXT CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `updateDt` DATE NULL DEFAULT NULL,
  `updateUser` VARCHAR(4) CHARACTER SET 'cp850' NOT NULL DEFAULT '',
  INDEX `AddressVW_idx` (`id` ASC),
  FULLTEXT INDEX `addressvw_compx` (`completo` ASC),
  FULLTEXT INDEX `addressvw_cpx` (`codigoPostal1` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services_cp`.`concelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services_cp`.`concelho` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services_cp`.`concelho` (
  `DD` VARCHAR(120) NOT NULL,
  `CC` VARCHAR(120) NOT NULL,
  `nome` VARCHAR(120) NOT NULL,
  INDEX `concelho_idx` (`CC` ASC),
  INDEX `concelho_idx1` (`DD` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services_cp`.`concelhovw`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services_cp`.`concelhovw` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services_cp`.`concelhovw` (
  `dd` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  `cc` VARCHAR(241) CHARACTER SET 'latin1' NULL DEFAULT NULL,
  `nome` VARCHAR(120) CHARACTER SET 'latin1' NOT NULL,
  INDEX `concelhoVW_idx` (`cc` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services_cp`.`distrito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services_cp`.`distrito` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services_cp`.`distrito` (
  `DD` VARCHAR(120) NOT NULL,
  `nome` VARCHAR(120) NOT NULL,
  UNIQUE INDEX `distrito_idx` (`nome` ASC),
  INDEX `distrito_idx1` (`DD` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services_cp`.`localidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services_cp`.`localidade` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services_cp`.`localidade` (
  `DD` VARCHAR(120) NOT NULL,
  `CC` VARCHAR(120) NOT NULL,
  `LLLL` VARCHAR(120) NOT NULL,
  `Localidade` VARCHAR(120) NOT NULL,
  `ART_COD` VARCHAR(120) NOT NULL,
  `ART_TIPO` VARCHAR(120) NOT NULL,
  `PRI_PREP` VARCHAR(120) NOT NULL,
  `ART_TITULO` VARCHAR(120) NOT NULL,
  `SEG_PREP` VARCHAR(120) NOT NULL,
  `ART_DESIG` VARCHAR(120) NOT NULL,
  `ART_LOCAL` VARCHAR(120) NOT NULL,
  `troco` VARCHAR(120) NOT NULL,
  `porta` VARCHAR(120) NOT NULL,
  `cliente` VARCHAR(120) NOT NULL,
  `codigoPostal4` VARCHAR(4) NOT NULL,
  `codigoPostal3` VARCHAR(3) NOT NULL,
  `codigoPostalDesignacao` VARCHAR(120) NOT NULL,
  INDEX `localidade_idx` (`codigoPostal4` ASC, `codigoPostal3` ASC),
  INDEX `localidade_idx1` (`Localidade` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
USE `dt_services` ;

-- -----------------------------------------------------
-- Placeholder table for view `dt_services`.`v_processo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dt_services`.`v_processo` (`id` INT, `codExterno` INT, `codInterno` INT, `idEstado` INT, `nomeEstado` INT, `dtEstado` INT);
SHOW WARNINGS;

-- -----------------------------------------------------
-- Placeholder table for view `dt_services`.`v_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dt_services`.`v_servico` (`id` INT, `idTipo` INT, `nomeTipo` INT, `idEstado` INT, `nomeEstado` INT, `valor` INT, `dtEstado` INT, `processoId` INT);
SHOW WARNINGS;

-- -----------------------------------------------------
-- View `dt_services`.`v_processo`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `dt_services`.`v_processo` ;
SHOW WARNINGS;
DROP TABLE IF EXISTS `dt_services`.`v_processo`;
SHOW WARNINGS;
USE `dt_services`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dt_services`.`v_processo` AS select `pi`.`id` AS `id`,`pi`.`codExterno` AS `codExterno`,`pi`.`codInterno` AS `codInterno`,`tipoest`.`id` AS `idEstado`,`tipoest`.`nome` AS `nomeEstado`,`est`.`dataInicio` AS `dtEstado` from (((`dt_services`.`t_processo` `pi` join (select `p`.`id` AS `id`,max(`e`.`dataInicio`) AS `maxdate` from (`dt_services`.`t_processo` `p` join `dt_services`.`t_estado_processo` `e` on((`e`.`processoId` = `p`.`id`))) group by `p`.`id`) `groupproc`) join `dt_services`.`t_estado_processo` `est` on((`est`.`processoId` = `pi`.`id`))) join `dt_services`.`t_ref_tipo_estado` `tipoest` on((`tipoest`.`id` = `est`.`tipoEstadoId`))) where ((`groupproc`.`id` = `pi`.`id`) and (`groupproc`.`maxdate` = `est`.`dataInicio`));
SHOW WARNINGS;

-- -----------------------------------------------------
-- View `dt_services`.`v_servico`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `dt_services`.`v_servico` ;
SHOW WARNINGS;
DROP TABLE IF EXISTS `dt_services`.`v_servico`;
SHOW WARNINGS;
USE `dt_services`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dt_services`.`v_servico` AS select `serv`.`id` AS `id`,`tipo`.`id` AS `idTipo`,`tipo`.`nome` AS `nomeTipo`,`tipoest`.`id` AS `idEstado`,`tipoest`.`nome` AS `nomeEstado`,`serv`.`valor` AS `valor`,`est`.`dataInicio` AS `dtEstado`,`serv`.`processoId` AS `processoId` from ((((`dt_services`.`t_servico` `serv` join (select `s`.`id` AS `id`,max(`e`.`dataInicio`) AS `maxdate` from (`dt_services`.`t_servico` `s` join `dt_services`.`t_estado_servico` `e` on((`e`.`servicoId` = `s`.`id`))) group by `s`.`id`) `groupserv`) join `dt_services`.`t_tipo_servico` `tipo` on((`tipo`.`id` = `serv`.`tipoServicoId`))) join `dt_services`.`t_estado_servico` `est` on((`est`.`servicoId` = `serv`.`id`))) join `dt_services`.`t_ref_tipo_estado` `tipoest` on((`tipoest`.`id` = `est`.`tipoEstadoId`))) where ((`groupserv`.`id` = `serv`.`id`) and (`groupserv`.`maxdate` = `est`.`dataInicio`));
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
