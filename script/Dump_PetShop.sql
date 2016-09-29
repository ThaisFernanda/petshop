/*
SQLyog Community v12.2.6 (64 bit)
MySQL - 5.5.25a : Database - petshop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`petshop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `petshop`;

/*Table structure for table `animal` */

DROP TABLE IF EXISTS `animal`;

CREATE TABLE `animal` (
  `id_animal` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(45) DEFAULT NULL,
  `raca` varchar(45) DEFAULT NULL,
  `cor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_animal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `animal` */

/*Table structure for table `atendimento` */

DROP TABLE IF EXISTS `atendimento`;

CREATE TABLE `atendimento` (
  `id_atendimento` int(11) NOT NULL AUTO_INCREMENT,
  `id_servico` int(11) DEFAULT NULL,
  `id_pessoa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_atendimento`),
  KEY `fk_servico_id_idx` (`id_servico`),
  KEY `fk_pessoa_id_idx` (`id_pessoa`),
  CONSTRAINT `fk_pessoas_id` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_servico_id` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id_servico`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `atendimento` */

/*Table structure for table `endereco` */

DROP TABLE IF EXISTS `endereco`;

CREATE TABLE `endereco` (
  `id_endereco` int(11) NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(45) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cidade` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `endereco` */

/*Table structure for table `pessoa` */

DROP TABLE IF EXISTS `pessoa`;

CREATE TABLE `pessoa` (
  `id_pessoa` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefone_fixo` varchar(45) DEFAULT NULL,
  `telefone_celular` varchar(45) DEFAULT NULL,
  `telefone_outro` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `cargo` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `id_endereco` int(11) NOT NULL,
  PRIMARY KEY (`id_pessoa`,`id_endereco`),
  KEY `fk_endereco_id_idx` (`id_endereco`),
  CONSTRAINT `fk_endereco_id` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pessoa` */

/*Table structure for table `pessoa_animal` */

DROP TABLE IF EXISTS `pessoa_animal`;

CREATE TABLE `pessoa_animal` (
  `id_pessoa` int(11) NOT NULL,
  `id_animal` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_animal` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pessoa_id_idx` (`id_pessoa`),
  KEY `fk_animal_id_idx` (`id_animal`),
  CONSTRAINT `fk_animal_id` FOREIGN KEY (`id_animal`) REFERENCES `animal` (`id_animal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pessoa_id` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_pessoa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pessoa_animal` */

/*Table structure for table `servico` */

DROP TABLE IF EXISTS `servico`;

CREATE TABLE `servico` (
  `id_servico` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(45) DEFAULT NULL,
  `valor` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_servico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `servico` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
