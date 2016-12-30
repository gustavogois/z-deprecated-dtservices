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
-- Table `dt_services`.`concelho`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`concelho` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`concelho` (
  `DD` VARCHAR(120) NOT NULL,
  `CC` VARCHAR(120) NOT NULL,
  `nome` VARCHAR(120) NOT NULL,
  INDEX `concelho_idx` (`CC` ASC))
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
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`solicitante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`solicitante` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`solicitante` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NULL DEFAULT NULL COMMENT 'Nome do Solicitante',
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
  `chaveSolicitanteProcesso` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COMMENT = 'Representa os Solicitantes (clientes) das execuções dos serviços (processos)';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`entidadedefacturacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`entidadedefacturacao` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`entidadedefacturacao` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `solicitanteId` INT(11) NOT NULL,
  `nome` VARCHAR(60) NULL DEFAULT NULL COMMENT 'Nome da Entidade de Facturação',
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  `nif` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `solicitante_entidadedefacturacao_fk` (`solicitanteId` ASC),
  CONSTRAINT `solicitante_entidadedefacturacao_fk`
    FOREIGN KEY (`solicitanteId`)
    REFERENCES `dt_services`.`solicitante` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COMMENT = 'Entidade responsável pelo faturamento dos serviços.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`processoexterno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`processoexterno` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`processoexterno` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `solicitanteId` INT(11) NULL DEFAULT NULL,
  `descricao` VARCHAR(300) NULL DEFAULT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `solicitante_processocliente_fk` (`solicitanteId` ASC),
  CONSTRAINT `solicitante_processocliente_fk`
    FOREIGN KEY (`solicitanteId`)
    REFERENCES `dt_services`.`solicitante` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`processointerno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`processointerno` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`processointerno` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `processoExternoId` INT(11) NOT NULL,
  `idProcCliente` VARCHAR(9) NULL DEFAULT NULL,
  `comChaves` TINYINT(1) NULL DEFAULT NULL COMMENT 'Indica se o pedido é com chaves.',
  `observacoes` VARCHAR(300) NULL DEFAULT NULL,
  `previsaoInicio` DATE NULL DEFAULT NULL,
  `previsaoFim` DATE NULL DEFAULT NULL,
  `fatura` VARCHAR(10) NULL DEFAULT NULL,
  `numeroChave` VARCHAR(10) NULL DEFAULT NULL,
  `nomeSolicitante` VARCHAR(60) NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `processoexterno_processointerno_fk` (`processoExternoId` ASC),
  CONSTRAINT `processoexterno_processointerno_fk`
    FOREIGN KEY (`processoExternoId`)
    REFERENCES `dt_services`.`processoexterno` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`grupo_tipos_estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`grupo_tipos_estado` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`grupo_tipos_estado` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`tipos_de_estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`tipos_de_estado` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`tipos_de_estado` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `grupo_estadoId` INT(11) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `grupo_tipos_estado_tipos_de_estado_fk` (`grupo_estadoId` ASC),
  CONSTRAINT `grupo_tipos_estado_tipos_de_estado_fk`
    FOREIGN KEY (`grupo_estadoId`)
    REFERENCES `dt_services`.`grupo_tipos_estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`estadosprocesso`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`estadosprocesso` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`estadosprocesso` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `processoId` INT(11) NOT NULL,
  `tipoId` INT(11) NOT NULL,
  `dtInicio` DATE NOT NULL,
  `dtFim` DATE NULL DEFAULT NULL,
  `observacoes` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipos_de_estado_estados_fk` (`tipoId` ASC),
  INDEX `processointerno_estados_fk` (`processoId` ASC),
  CONSTRAINT `processointerno_estados_fk`
    FOREIGN KEY (`processoId`)
    REFERENCES `dt_services`.`processointerno` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tipos_de_estado_estados_fk`
    FOREIGN KEY (`tipoId`)
    REFERENCES `dt_services`.`tipos_de_estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`tiposervico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`tiposervico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`tiposervico` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `descricao` VARCHAR(300) NULL DEFAULT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`tiposervico_solicitante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`tiposervico_solicitante` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`tiposervico_solicitante` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `tipoServicoId` INT(11) NOT NULL,
  `solicitanteId` INT(11) NOT NULL,
  `valor` DOUBLE NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `tiposervico_tiposervico_solicitante_fk` (`tipoServicoId` ASC),
  INDEX `solicitante_tiposervico_solicitante_fk` (`solicitanteId` ASC),
  CONSTRAINT `solicitante_tiposervico_solicitante_fk`
    FOREIGN KEY (`solicitanteId`)
    REFERENCES `dt_services`.`solicitante` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `tiposervico_tiposervico_solicitante_fk`
    FOREIGN KEY (`tipoServicoId`)
    REFERENCES `dt_services`.`tiposervico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`servico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`servico` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `processoId` INT(11) NOT NULL,
  `tipoServico_SolicitanteId` INT(11) NOT NULL,
  `dtCadastro` DATE NULL DEFAULT NULL,
  `dtInicio` DATE NULL DEFAULT NULL,
  `dtFim` DATE NULL DEFAULT NULL,
  `valor` DOUBLE NOT NULL,
  `observacoes` VARCHAR(300) NOT NULL,
  `updateDt` DATETIME NULL DEFAULT NULL,
  `updateUser` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `tiposervico_solicitante_servico_fk` (`tipoServico_SolicitanteId` ASC),
  INDEX `processo_servico_fk` (`processoId` ASC),
  CONSTRAINT `processo_servico_fk`
    FOREIGN KEY (`processoId`)
    REFERENCES `dt_services`.`processointerno` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `tiposervico_solicitante_servico_fk`
    FOREIGN KEY (`tipoServico_SolicitanteId`)
    REFERENCES `dt_services`.`tiposervico_solicitante` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`estadosservico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`estadosservico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`estadosservico` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `servicoId` INT(11) NOT NULL,
  `tipoId` INT(11) NOT NULL,
  `dtInicio` DATE NOT NULL,
  `dtFim` DATE NULL DEFAULT NULL,
  `observacoes` VARCHAR(300) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipos_de_estado_estadosservico_fk` (`tipoId` ASC),
  INDEX `servico_estadosservico_fk` (`servicoId` ASC),
  CONSTRAINT `servico_estadosservico_fk`
    FOREIGN KEY (`servicoId`)
    REFERENCES `dt_services`.`servico` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `tipos_de_estado_estadosservico_fk`
    FOREIGN KEY (`tipoId`)
    REFERENCES `dt_services`.`tipos_de_estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 29
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`historico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`historico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`historico` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idObjeto` INT(11) NOT NULL,
  `tipoObjeto` INT(11) NOT NULL,
  `data` DATETIME NOT NULL,
  `descricao` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`imagem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`imagem` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`imagem` (
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
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`imovel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`imovel` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`imovel` (
  `id` INT(11) NOT NULL,
  `inquilino` VARCHAR(60) NULL DEFAULT NULL COMMENT 'Inquilino atual do imóvel.',
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
  PRIMARY KEY (`id`),
  CONSTRAINT `processoexterno_imovel_fk`
    FOREIGN KEY (`id`)
    REFERENCES `dt_services`.`processoexterno` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Representa os imóveis sobre os quais são executados os serviços selecionados no Processo.';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`imagem_imovel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`imagem_imovel` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`imagem_imovel` (
  `imovelId` INT(11) NOT NULL,
  `imagemId` INT(11) NOT NULL,
  PRIMARY KEY (`imovelId`, `imagemId`),
  INDEX `imagem_imagem_imovel_fk` (`imagemId` ASC),
  CONSTRAINT `imagem_imagem_imovel_fk`
    FOREIGN KEY (`imagemId`)
    REFERENCES `dt_services`.`imagem` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `imovel_imagem_imovel_fk`
    FOREIGN KEY (`imovelId`)
    REFERENCES `dt_services`.`imovel` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`imagem_servico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`imagem_servico` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`imagem_servico` (
  `servicoId` INT(11) NOT NULL,
  `imagemId` INT(11) NOT NULL,
  PRIMARY KEY (`servicoId`, `imagemId`),
  INDEX `imagem_imagem_servico_fk` (`imagemId` ASC),
  CONSTRAINT `imagem_imagem_servico_fk`
    FOREIGN KEY (`imagemId`)
    REFERENCES `dt_services`.`imagem` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `servico_imagem_servico_fk`
    FOREIGN KEY (`servicoId`)
    REFERENCES `dt_services`.`servico` (`id`)
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
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`log` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`log` (
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
AUTO_INCREMENT = 1189
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`tipodeuser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`tipodeuser` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`tipodeuser` (
  `id` INT(11) NOT NULL,
  `descricao` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `dt_services`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dt_services`.`users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `dt_services`.`users` (
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
    REFERENCES `dt_services`.`tipodeuser` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 2
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

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
