-- MySQL dump 10.13  Distrib 5.7.11, for Win64 (x86_64)
--
-- Host: localhost    Database: dt_services
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `concelho`
--

DROP TABLE IF EXISTS `concelho`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `concelho` (
  `DD` varchar(120) NOT NULL,
  `CC` varchar(120) NOT NULL,
  `nome` varchar(120) NOT NULL,
  KEY `concelho_idx` (`CC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `concelho`
--

LOCK TABLES `concelho` WRITE;
/*!40000 ALTER TABLE `concelho` DISABLE KEYS */;
/*!40000 ALTER TABLE `concelho` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distrito`
--

DROP TABLE IF EXISTS `distrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `distrito` (
  `DD` varchar(120) NOT NULL,
  `nome` varchar(120) NOT NULL,
  UNIQUE KEY `distrito_idx` (`nome`),
  KEY `distrito_idx1` (`DD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distrito`
--

LOCK TABLES `distrito` WRITE;
/*!40000 ALTER TABLE `distrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `distrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entidadedefacturacao`
--

DROP TABLE IF EXISTS `entidadedefacturacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entidadedefacturacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solicitanteId` int(11) NOT NULL,
  `nome` varchar(60) DEFAULT NULL COMMENT 'Nome da Entidade de Facturação',
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  `nif` varchar(9) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `solicitante_entidadedefacturacao_fk` (`solicitanteId`),
  CONSTRAINT `solicitante_entidadedefacturacao_fk` FOREIGN KEY (`solicitanteId`) REFERENCES `solicitante` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Entidade responsável pelo faturamento dos serviços.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entidadedefacturacao`
--

LOCK TABLES `entidadedefacturacao` WRITE;
/*!40000 ALTER TABLE `entidadedefacturacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `entidadedefacturacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadosprocesso`
--

DROP TABLE IF EXISTS `estadosprocesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadosprocesso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processoId` int(11) NOT NULL,
  `tipoId` int(11) NOT NULL,
  `dtInicio` date NOT NULL,
  `dtFim` date DEFAULT NULL,
  `observacoes` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipos_de_estado_estados_fk` (`tipoId`),
  KEY `processointerno_estados_fk` (`processoId`),
  CONSTRAINT `processointerno_estados_fk` FOREIGN KEY (`processoId`) REFERENCES `processointerno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipos_de_estado_estados_fk` FOREIGN KEY (`tipoId`) REFERENCES `tipos_de_estado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadosprocesso`
--

LOCK TABLES `estadosprocesso` WRITE;
/*!40000 ALTER TABLE `estadosprocesso` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadosprocesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadosservico`
--

DROP TABLE IF EXISTS `estadosservico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadosservico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `servicoId` int(11) NOT NULL,
  `tipoId` int(11) NOT NULL,
  `dtInicio` date NOT NULL,
  `dtFim` date DEFAULT NULL,
  `observacoes` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipos_de_estado_estadosservico_fk` (`tipoId`),
  KEY `servico_estadosservico_fk` (`servicoId`),
  CONSTRAINT `servico_estadosservico_fk` FOREIGN KEY (`servicoId`) REFERENCES `servico` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tipos_de_estado_estadosservico_fk` FOREIGN KEY (`tipoId`) REFERENCES `tipos_de_estado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadosservico`
--

LOCK TABLES `estadosservico` WRITE;
/*!40000 ALTER TABLE `estadosservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `estadosservico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_tipos_estado`
--

DROP TABLE IF EXISTS `grupo_tipos_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupo_tipos_estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_tipos_estado`
--

LOCK TABLES `grupo_tipos_estado` WRITE;
/*!40000 ALTER TABLE `grupo_tipos_estado` DISABLE KEYS */;
INSERT INTO `grupo_tipos_estado` VALUES (1,'Processo externo'),(2,'Processo interno'),(3,'Serviços');
/*!40000 ALTER TABLE `grupo_tipos_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historico`
--

DROP TABLE IF EXISTS `historico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idObjeto` int(11) NOT NULL,
  `tipoObjeto` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `descricao` varchar(300) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historico`
--

LOCK TABLES `historico` WRITE;
/*!40000 ALTER TABLE `historico` DISABLE KEYS */;
/*!40000 ALTER TABLE `historico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagem`
--

DROP TABLE IF EXISTS `imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imagem` longblob NOT NULL,
  `descricao` varchar(120) NOT NULL,
  `filename` varchar(100) NOT NULL,
  `size` int(11) NOT NULL,
  `mimeType` varchar(20) NOT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagem`
--

LOCK TABLES `imagem` WRITE;
/*!40000 ALTER TABLE `imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagem_imovel`
--

DROP TABLE IF EXISTS `imagem_imovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagem_imovel` (
  `imovelId` int(11) NOT NULL,
  `imagemId` int(11) NOT NULL,
  PRIMARY KEY (`imovelId`,`imagemId`),
  KEY `imagem_imagem_imovel_fk` (`imagemId`),
  CONSTRAINT `imagem_imagem_imovel_fk` FOREIGN KEY (`imagemId`) REFERENCES `imagem` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `imovel_imagem_imovel_fk` FOREIGN KEY (`imovelId`) REFERENCES `imovel` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagem_imovel`
--

LOCK TABLES `imagem_imovel` WRITE;
/*!40000 ALTER TABLE `imagem_imovel` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagem_imovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imagem_servico`
--

DROP TABLE IF EXISTS `imagem_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imagem_servico` (
  `servicoId` int(11) NOT NULL,
  `imagemId` int(11) NOT NULL,
  PRIMARY KEY (`servicoId`,`imagemId`),
  KEY `imagem_imagem_servico_fk` (`imagemId`),
  CONSTRAINT `imagem_imagem_servico_fk` FOREIGN KEY (`imagemId`) REFERENCES `imagem` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `servico_imagem_servico_fk` FOREIGN KEY (`servicoId`) REFERENCES `servico` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imagem_servico`
--

LOCK TABLES `imagem_servico` WRITE;
/*!40000 ALTER TABLE `imagem_servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `imagem_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imovel`
--

DROP TABLE IF EXISTS `imovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imovel` (
  `id` int(11) NOT NULL,
  `inquilino` varchar(60) DEFAULT NULL COMMENT 'Inquilino atual do imóvel.',
  `crp` varchar(60) DEFAULT NULL COMMENT 'Certidão de Registro Predial.',
  `ruaPorta` varchar(100) NOT NULL,
  `complemento` varchar(120) DEFAULT NULL,
  `localidade` varchar(120) DEFAULT NULL,
  `codigoPostal` varchar(9) DEFAULT NULL,
  `cc` varchar(120) NOT NULL,
  `dd` varchar(120) NOT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `processoexterno_imovel_fk` FOREIGN KEY (`id`) REFERENCES `processoexterno` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Representa os imóveis sobre os quais são executados os serviços selecionados no Processo.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imovel`
--

LOCK TABLES `imovel` WRITE;
/*!40000 ALTER TABLE `imovel` DISABLE KEYS */;
/*!40000 ALTER TABLE `imovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localidade`
--

DROP TABLE IF EXISTS `localidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidade` (
  `DD` varchar(120) NOT NULL,
  `CC` varchar(120) NOT NULL,
  `LLLL` varchar(120) NOT NULL,
  `Localidade` varchar(120) NOT NULL,
  `ART_COD` varchar(120) NOT NULL,
  `ART_TIPO` varchar(120) NOT NULL,
  `PRI_PREP` varchar(120) NOT NULL,
  `ART_TITULO` varchar(120) NOT NULL,
  `SEG_PREP` varchar(120) NOT NULL,
  `ART_DESIG` varchar(120) NOT NULL,
  `ART_LOCAL` varchar(120) NOT NULL,
  `troco` varchar(120) NOT NULL,
  `porta` varchar(120) NOT NULL,
  `cliente` varchar(120) NOT NULL,
  `codigoPostal4` varchar(4) NOT NULL,
  `codigoPostal3` varchar(3) NOT NULL,
  `codigoPostalDesignacao` varchar(120) NOT NULL,
  KEY `localidade_idx` (`codigoPostal4`,`codigoPostal3`),
  KEY `localidade_idx1` (`Localidade`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localidade`
--

LOCK TABLES `localidade` WRITE;
/*!40000 ALTER TABLE `localidade` DISABLE KEYS */;
/*!40000 ALTER TABLE `localidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(10) NOT NULL,
  `codigo` varchar(10) DEFAULT NULL,
  `table_1` varchar(10) DEFAULT NULL,
  `tableId` int(11) DEFAULT NULL,
  `descricao` longtext NOT NULL,
  `updateDt` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateUser` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1220 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1197,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 19:51:58',NULL),(1198,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 19:52:11',NULL),(1199,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 19:55:12',NULL),(1200,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 19:55:33',NULL),(1201,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 19:55:50',NULL),(1202,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  and ( nome like :nome  )  - Params( {nome=Tipo de Serviço 01} )','2016-12-30 20:17:07',NULL),(1203,'INFO',NULL,NULL,NULL,'pt.gois.dtServices.entity.TipoServico pt.gois.dtServices.entity.TipoServico@23 criada','2016-12-30 20:17:08',NULL),(1204,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 20:17:09',NULL),(1205,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  and ( nome like :nome  )  - Params( {nome=Tipo de Serviço 01xxxx} )','2016-12-30 20:18:15',NULL),(1206,'INFO',NULL,NULL,NULL,'pt.gois.dtServices.entity.TipoServico pt.gois.dtServices.entity.TipoServico@23 alterada','2016-12-30 20:18:16',NULL),(1207,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 20:18:16',NULL),(1208,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 20:18:23',NULL),(1209,'INFO',NULL,NULL,NULL,'pt.gois.dtServices.entity.TipoServico pt.gois.dtServices.entity.TipoServico@23 removida','2016-12-30 20:18:32',NULL),(1210,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 20:18:33',NULL),(1211,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  and ( nome like :nome  )  - Params( {nome=Tipo de Serviço 01} )','2016-12-30 20:19:10',NULL),(1212,'INFO',NULL,NULL,NULL,'pt.gois.dtServices.entity.TipoServico pt.gois.dtServices.entity.TipoServico@24 criada','2016-12-30 20:19:10',NULL),(1213,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 20:19:11',NULL),(1214,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  and ( nome like :nome  )  - Params( {nome=Tipo de Serviço 02} )','2016-12-30 20:23:58',NULL),(1215,'INFO',NULL,NULL,NULL,'pt.gois.dtServices.entity.TipoServico pt.gois.dtServices.entity.TipoServico@25 criada','2016-12-30 20:23:58',NULL),(1216,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 20:23:58',NULL),(1217,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  and ( nome like :nome  )  - Params( {nome=Tipo de Serviço 03} )','2016-12-30 20:24:30',NULL),(1218,'INFO',NULL,NULL,NULL,'pt.gois.dtServices.entity.TipoServico pt.gois.dtServices.entity.TipoServico@26 criada','2016-12-30 20:24:30',NULL),(1219,'INFO',NULL,NULL,NULL,'Pesquisaando: SELECT distinct obj FROM pt.gois.dtServices.entity.TipoServico obj where 1 = 1  - Params( {} )','2016-12-30 20:24:30',NULL);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processoexterno`
--

DROP TABLE IF EXISTS `processoexterno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processoexterno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solicitanteId` int(11) DEFAULT NULL,
  `descricao` varchar(300) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `solicitante_processocliente_fk` (`solicitanteId`),
  CONSTRAINT `solicitante_processocliente_fk` FOREIGN KEY (`solicitanteId`) REFERENCES `solicitante` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processoexterno`
--

LOCK TABLES `processoexterno` WRITE;
/*!40000 ALTER TABLE `processoexterno` DISABLE KEYS */;
/*!40000 ALTER TABLE `processoexterno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processointerno`
--

DROP TABLE IF EXISTS `processointerno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processointerno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processoExternoId` int(11) NOT NULL,
  `idProcCliente` varchar(9) DEFAULT NULL,
  `comChaves` tinyint(1) DEFAULT NULL COMMENT 'Indica se o pedido é com chaves.',
  `observacoes` varchar(300) DEFAULT NULL,
  `previsaoInicio` date DEFAULT NULL,
  `previsaoFim` date DEFAULT NULL,
  `fatura` varchar(10) DEFAULT NULL,
  `numeroChave` varchar(10) DEFAULT NULL,
  `nomeSolicitante` varchar(60) NOT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `processoexterno_processointerno_fk` (`processoExternoId`),
  CONSTRAINT `processoexterno_processointerno_fk` FOREIGN KEY (`processoExternoId`) REFERENCES `processoexterno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processointerno`
--

LOCK TABLES `processointerno` WRITE;
/*!40000 ALTER TABLE `processointerno` DISABLE KEYS */;
/*!40000 ALTER TABLE `processointerno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processoId` int(11) NOT NULL,
  `tipoServico_SolicitanteId` int(11) NOT NULL,
  `dtCadastro` date DEFAULT NULL,
  `dtInicio` date DEFAULT NULL,
  `dtFim` date DEFAULT NULL,
  `valor` double NOT NULL,
  `observacoes` varchar(300) NOT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tiposervico_solicitante_servico_fk` (`tipoServico_SolicitanteId`),
  KEY `processo_servico_fk` (`processoId`),
  CONSTRAINT `processo_servico_fk` FOREIGN KEY (`processoId`) REFERENCES `processointerno` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `tiposervico_solicitante_servico_fk` FOREIGN KEY (`tipoServico_SolicitanteId`) REFERENCES `tiposervico_solicitante` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitante`
--

DROP TABLE IF EXISTS `solicitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solicitante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) DEFAULT NULL COMMENT 'Nome do Solicitante',
  `sigla` varchar(5) NOT NULL,
  `nif` varchar(10) DEFAULT NULL COMMENT 'Número do contribuinte.',
  `telefone` varchar(10) DEFAULT NULL COMMENT 'Telefone do Solicitante.',
  `ruaPorta` varchar(100) NOT NULL,
  `complemento` varchar(120) DEFAULT NULL,
  `localidade` varchar(120) DEFAULT NULL,
  `codigoPostal` varchar(9) DEFAULT NULL,
  `cc` varchar(120) NOT NULL,
  `dd` varchar(120) NOT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  `chaveSolicitanteProcesso` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='Representa os Solicitantes (clientes) das execuções dos serviços (processos)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitante`
--

LOCK TABLES `solicitante` WRITE;
/*!40000 ALTER TABLE `solicitante` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodeuser`
--

DROP TABLE IF EXISTS `tipodeuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodeuser` (
  `id` int(11) NOT NULL,
  `descricao` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodeuser`
--

LOCK TABLES `tipodeuser` WRITE;
/*!40000 ALTER TABLE `tipodeuser` DISABLE KEYS */;
INSERT INTO `tipodeuser` VALUES (0,'Root'),(1,'Admin'),(2,'Gerente');
/*!40000 ALTER TABLE `tipodeuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_de_estado`
--

DROP TABLE IF EXISTS `tipos_de_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos_de_estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grupo_estadoId` int(11) NOT NULL,
  `nome` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `grupo_tipos_estado_tipos_de_estado_fk` (`grupo_estadoId`),
  CONSTRAINT `grupo_tipos_estado_tipos_de_estado_fk` FOREIGN KEY (`grupo_estadoId`) REFERENCES `grupo_tipos_estado` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_de_estado`
--

LOCK TABLES `tipos_de_estado` WRITE;
/*!40000 ALTER TABLE `tipos_de_estado` DISABLE KEYS */;
INSERT INTO `tipos_de_estado` VALUES (1,1,'Criado'),(2,1,'Em execução'),(3,1,'Finalizado'),(4,1,'Suspenso'),(5,2,'Criado'),(6,2,'Em execução'),(7,2,'Finalizado - Aguardando Faturamento'),(8,2,'Faturado - Aguardando Pagamento'),(9,2,'Finalizado'),(10,2,'Suspenso'),(11,3,'Criado'),(12,3,'Em execução'),(13,3,'Finalizado'),(14,3,'Suspenso');
/*!40000 ALTER TABLE `tipos_de_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposervico`
--

DROP TABLE IF EXISTS `tiposervico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiposervico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) NOT NULL,
  `valor` double NOT NULL,
  `descricao` varchar(300) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposervico`
--

LOCK TABLES `tiposervico` WRITE;
/*!40000 ALTER TABLE `tiposervico` DISABLE KEYS */;
INSERT INTO `tiposervico` VALUES (5,'Tipo de Serviço 01',10,'Observações do Tipo de Serviço 01',NULL,NULL),(6,'Tipo de Serviço 02',15,'Observações do Tipo de Serviço 02',NULL,NULL),(7,'Tipo de Serviço 03',15.5,'Observações do Tipo de Serviço 03',NULL,NULL);
/*!40000 ALTER TABLE `tiposervico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiposervico_solicitante`
--

DROP TABLE IF EXISTS `tiposervico_solicitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiposervico_solicitante` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoServicoId` int(11) NOT NULL,
  `solicitanteId` int(11) NOT NULL,
  `valor` double NOT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tiposervico_tiposervico_solicitante_fk` (`tipoServicoId`),
  KEY `solicitante_tiposervico_solicitante_fk` (`solicitanteId`),
  CONSTRAINT `solicitante_tiposervico_solicitante_fk` FOREIGN KEY (`solicitanteId`) REFERENCES `solicitante` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `tiposervico_tiposervico_solicitante_fk` FOREIGN KEY (`tipoServicoId`) REFERENCES `tiposervico` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiposervico_solicitante`
--

LOCK TABLES `tiposervico_solicitante` WRITE;
/*!40000 ALTER TABLE `tiposervico_solicitante` DISABLE KEYS */;
/*!40000 ALTER TABLE `tiposervico_solicitante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoDeUserId` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(60) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `expiryDate` date DEFAULT NULL,
  `locked` tinyint(1) NOT NULL DEFAULT '0',
  `createDt` varchar(20) NOT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_idx` (`username`),
  KEY `tipodeuser_users_fk` (`tipoDeUserId`),
  CONSTRAINT `tipodeuser_users_fk` FOREIGN KEY (`tipoDeUserId`) REFERENCES `tipodeuser` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,0,'root','rootPass','root','',NULL,0,'2016-12-30 19:49:48','2016-12-30 19:49:48','0');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-30 20:55:45
