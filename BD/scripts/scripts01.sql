-- Refactoring geral BD 19/01

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
-- Table `dt_services`.`T_IMAGEM`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_IMAGEM` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_IMAGEM` (
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
-- Table `dt_services`.`T_SOLICITANTE`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_SOLICITANTE` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_SOLICITANTE` (
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
-- Table `dt_services`.`T_IMOVEL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_IMOVEL` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_IMOVEL` (
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
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_PROCESSO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_PROCESSO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_PROCESSO` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `solicitanteId` INT(11) NOT NULL,
  `imovelId` INT(11) NOT NULL,
  `codExterno` VARCHAR(9) NOT NULL,
  `codInterno` VARCHAR(9) NOT NULL,
  `comChaves` TINYINT(1) NULL DEFAULT NULL,
  `observacoes` VARCHAR(300) NULL DEFAULT NULL,
  `previsaoInicio` DATETIME(6) NULL,
  `previsaoFim` DATETIME(6) NULL DEFAULT NULL,
  `dtInicio` DATETIME(6) NULL,
  `dtFim` DATETIME(6) NULL,
  `numeroChave` VARCHAR(10) NULL DEFAULT NULL,
  `nomeRequisitante` VARCHAR(60) NOT NULL,
  `dtCadastro` DATETIME(6) NULL,
  PRIMARY KEY (`id`, `solicitanteId`, `imovelId`),
  INDEX `fk_T_PROCESSO_T_SOLICITANTE1_idx` (`solicitanteId` ASC),
  INDEX `fk_T_PROCESSO_T_IMOVEL1_idx` (`imovelId` ASC),
  CONSTRAINT `fk_T_PROCESSO_T_SOLICITANTE1`
    FOREIGN KEY (`solicitanteId`)
    REFERENCES `dt_services`.`T_SOLICITANTE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_PROCESSO_T_IMOVEL1`
    FOREIGN KEY (`imovelId`)
    REFERENCES `dt_services`.`T_IMOVEL` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_TIPO_SERVICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_TIPO_SERVICO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_TIPO_SERVICO` (
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
-- Table `dt_services`.`T_SERVICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_SERVICO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_SERVICO` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `processoId` INT(11) NOT NULL,
  `tipoServicoId` INT(11) NOT NULL,
  `dtCadastro` DATE NULL DEFAULT NULL,
  `dtInicio` DATE NULL DEFAULT NULL,
  `dtFim` DATE NULL DEFAULT NULL,
  `valor` DOUBLE NOT NULL,
  `observacoes` VARCHAR(300) NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `processo_servico_fk` (`processoId` ASC),
  INDEX `fk_T_SERVICO_T_TIPO_SERVICO1_idx` (`tipoServicoId` ASC),
  CONSTRAINT `processo_servico_fk`
    FOREIGN KEY (`processoId`)
    REFERENCES `dt_services`.`T_PROCESSO` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_SERVICO_T_TIPO_SERVICO1`
    FOREIGN KEY (`tipoServicoId`)
    REFERENCES `dt_services`.`T_TIPO_SERVICO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_IMAGEM_SERVICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_IMAGEM_SERVICO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_IMAGEM_SERVICO` (
  `servicoId` INT(11) NOT NULL,
  `imagemId` INT(11) NOT NULL,
  PRIMARY KEY (`servicoId`, `imagemId`),
  INDEX `imagem_imagem_servico_fk` (`imagemId` ASC),
  CONSTRAINT `imagem_imagem_servico_fk`
    FOREIGN KEY (`imagemId`)
    REFERENCES `dt_services`.`T_IMAGEM` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `servico_imagem_servico_fk`
    FOREIGN KEY (`servicoId`)
    REFERENCES `dt_services`.`T_SERVICO` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

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
-- Table `dt_services`.`T_LOG`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_LOG` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_LOG` (
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
-- Table `dt_services`.`T_TIPO_USER`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_TIPO_USER` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_TIPO_USER` (
  `id` INT(11) NOT NULL,
  `descricao` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_USERS` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_USERS` (
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
    REFERENCES `dt_services`.`T_TIPO_USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_HISTORICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_HISTORICO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_HISTORICO` (
  `id` INT NOT NULL,
  `tipoObjetoId` INT NULL,
  `objetoId` INT NULL,
  `data` DATETIME(6) NULL,
  `descricao` VARCHAR(60) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_ESTADO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_ESTADO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_ESTADO` (
  `id` INT NOT NULL,
  `idTipo` INT NOT NULL,
  `dataInicio` DATETIME(6) NULL,
  `dataFim` DATETIME(6) NULL,
  `observacoes` VARCHAR(300) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_ESTADO_SERVICO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_ESTADO_SERVICO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_ESTADO_SERVICO` (
  `servicoId` INT(11) NOT NULL,
  `estadoId` INT NOT NULL,
  INDEX `fk_T_ESTADO_SERVICO_T_SERVICO1_idx` (`servicoId` ASC),
  INDEX `fk_T_ESTADO_SERVICO_T_ESTADO1_idx` (`estadoId` ASC),
  CONSTRAINT `fk_T_ESTADO_SERVICO_T_SERVICO1`
    FOREIGN KEY (`servicoId`)
    REFERENCES `dt_services`.`T_SERVICO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_ESTADO_SERVICO_T_ESTADO1`
    FOREIGN KEY (`estadoId`)
    REFERENCES `dt_services`.`T_ESTADO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_ESTADO_PROCESSO`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_ESTADO_PROCESSO` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_ESTADO_PROCESSO` (
  `estadoId` INT NOT NULL,
  `processoId` INT(11) NOT NULL,
  INDEX `fk_T_ESTADO_PROCESSO_T_ESTADO1_idx` (`estadoId` ASC),
  INDEX `fk_T_ESTADO_PROCESSO_T_PROCESSO1_idx` (`processoId` ASC),
  CONSTRAINT `fk_T_ESTADO_PROCESSO_T_ESTADO1`
    FOREIGN KEY (`estadoId`)
    REFERENCES `dt_services`.`T_ESTADO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_ESTADO_PROCESSO_T_PROCESSO1`
    FOREIGN KEY (`processoId`)
    REFERENCES `dt_services`.`T_PROCESSO` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`T_IMAGEM_IMOVEL`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`T_IMAGEM_IMOVEL` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`T_IMAGEM_IMOVEL` (
  `imovelId` INT(11) NOT NULL,
  `imagemId` INT(11) NOT NULL,
  INDEX `fk_T_IMAGEM_IMOVEL_T_IMOVEL1_idx` (`imovelId` ASC),
  INDEX `fk_T_IMAGEM_IMOVEL_T_IMAGEM1_idx` (`imagemId` ASC),
  CONSTRAINT `fk_T_IMAGEM_IMOVEL_T_IMOVEL1`
    FOREIGN KEY (`imovelId`)
    REFERENCES `dt_services`.`T_IMOVEL` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_T_IMAGEM_IMOVEL_T_IMAGEM1`
    FOREIGN KEY (`imagemId`)
    REFERENCES `dt_services`.`T_IMAGEM` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

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

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
