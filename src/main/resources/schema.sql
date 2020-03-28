-- MySQL Workbench Synchronization
-- Generated: 2019-12-19 19:15
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: André

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE TABLE IF NOT EXISTS `gear`.`TB_Funcionario` (
  `idFuncionario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `CPF` CHAR(11) NOT NULL,
  `RG` CHAR(9) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `celular` VARCHAR(11) NULL DEFAULT NULL,
  `idUnidade` INT(11) NOT NULL,
  PRIMARY KEY (`idFuncionario`, `idUnidade`),
  INDEX `indexCPF` (`CPF` ASC),
  INDEX `fk_TB_Funcionario_TB_Unidade1_idx` (`idUnidade` ASC),
  CONSTRAINT `fk_TB_Funcionario_TB_Unidade1`
    FOREIGN KEY (`idUnidade`)
    REFERENCES `gear`.`TB_Unidade` (`idUnidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `gear`.`TB_Unidade` (
  `idUnidade` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`idUnidade`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `gear`.`TB_Produto` (
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `descricao` VARCHAR(240) NULL DEFAULT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `tipo` ENUM('Insumo', 'Equipamento') NOT NULL,
  PRIMARY KEY (`idProduto`),
  INDEX `index` (`nome` ASC, `preco` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `gear`.`TB_EstoqueUnidade` (
  `idProduto` INT(11) NOT NULL,
  `idUnidade` INT(11) NOT NULL,
  `quantidade` INT(11) NOT NULL,
  `situacao` ENUM('Danificado', 'Perfeito estado', 'Inutilizável') NOT NULL,
  PRIMARY KEY (`idProduto`, `idUnidade`),
  INDEX `fk_TB_Produto_has_TB_Unidade_TB_Unidade1_idx` (`idUnidade` ASC),
  INDEX `fk_TB_Produto_has_TB_Unidade_TB_Produto1_idx` (`idProduto` ASC),
  CONSTRAINT `fk_TB_Produto_has_TB_Unidade_TB_Produto1`
    FOREIGN KEY (`idProduto`)
    REFERENCES `gear`.`TB_Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_Produto_has_TB_Unidade_TB_Unidade1`
    FOREIGN KEY (`idUnidade`)
    REFERENCES `gear`.`TB_Unidade` (`idUnidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `gear`.`TB_OrdemServico` (
  `idOrdemServico` INT(11) NOT NULL,
  `classificacao` ENUM('Corretiva', 'Preventiva') NOT NULL,
  `demanda` ENUM('Normal', 'Urgente') NOT NULL,
  `hora_solicitacao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nome_solicitante` VARCHAR(60) NOT NULL,
  `hora_atendimento` DATETIME NULL DEFAULT NULL,
  `local` VARCHAR(60) NOT NULL,
  `descricao` VARCHAR(600) NOT NULL,
  PRIMARY KEY (`idOrdemServico`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `gear`.`TB_ExecutorOrdemServico` (
  `idOrdemServico` INT(11) NOT NULL,
  `idFuncionario` INT(11) NOT NULL,
  PRIMARY KEY (`idOrdemServico`, `idFuncionario`),
  INDEX `fk_TB_OrdemServico_has_TB_Funcionario_TB_Funcionario1_idx` (`idFuncionario` ASC),
  INDEX `fk_TB_OrdemServico_has_TB_Funcionario_TB_OrdemServico1_idx` (`idOrdemServico` ASC),
  CONSTRAINT `fk_TB_OrdemServico_has_TB_Funcionario_TB_OrdemServico1`
    FOREIGN KEY (`idOrdemServico`)
    REFERENCES `gear`.`TB_OrdemServico` (`idOrdemServico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_OrdemServico_has_TB_Funcionario_TB_Funcionario1`
    FOREIGN KEY (`idFuncionario`)
    REFERENCES `gear`.`TB_Funcionario` (`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `gear`.`TB_Itens` (
  `TB_OrdemServico_idOrdemServico` INT(11) NOT NULL,
  `TB_Produto_idProduto` INT(11) NOT NULL,
  `quantidade` INT(11) NOT NULL,
  PRIMARY KEY (`TB_OrdemServico_idOrdemServico`, `TB_Produto_idProduto`),
  INDEX `fk_TB_OrdemServico_has_TB_Produto_TB_Produto1_idx` (`TB_Produto_idProduto` ASC),
  INDEX `fk_TB_OrdemServico_has_TB_Produto_TB_OrdemServico1_idx` (`TB_OrdemServico_idOrdemServico` ASC),
  CONSTRAINT `fk_TB_OrdemServico_has_TB_Produto_TB_OrdemServico1`
    FOREIGN KEY (`TB_OrdemServico_idOrdemServico`)
    REFERENCES `gear`.`TB_OrdemServico` (`idOrdemServico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_TB_OrdemServico_has_TB_Produto_TB_Produto1`
    FOREIGN KEY (`TB_Produto_idProduto`)
    REFERENCES `gear`.`TB_Produto` (`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;