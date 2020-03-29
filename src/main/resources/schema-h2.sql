CREATE TABLE IF NOT EXISTS TB_Unidade(
     `idUnidade` INT(11) NOT NULL AUTO_INCREMENT,
     `nome` VARCHAR(60) NOT NULL,
     PRIMARY KEY (`idUnidade`));

CREATE TABLE IF NOT EXISTS TB_Funcionario(
  `idFuncionario` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `CPF` CHAR(11) NOT NULL,
  `RG` CHAR(9) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `celular` VARCHAR(11) NULL DEFAULT NULL,
  `idUnidade` INT(11) NOT NULL,
  PRIMARY KEY (`idFuncionario`, `idUnidade`),
  FOREIGN KEY (`idUnidade`) REFERENCES TB_Unidade(`idUnidade`));


CREATE TABLE IF NOT EXISTS TB_Produto(
  `idProduto` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(60) NOT NULL,
  `descricao` VARCHAR(240) NULL DEFAULT NULL,
  `preco` DECIMAL(10,2) NOT NULL,
  `tipo` ENUM('INSUMO','EQUIPAMENTO') NOT NULL,
  PRIMARY KEY (`idProduto`));

CREATE TABLE IF NOT EXISTS TB_EstoqueUnidade(
    idItem INT(11) NOT NULL AUTO_INCREMENT,
  `idProduto` INT(11) NOT NULL,
  `idUnidade` INT(11) NOT NULL,
  `quantidade` INT(11) NOT NULL,
  `situacao` ENUM('Danificado', 'Perfeito estado', 'Inutilizável') NOT NULL,
  PRIMARY KEY (idItem),
  FOREIGN KEY (`idProduto`) REFERENCES TB_Produto(`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`idUnidade`) REFERENCES TB_Unidade(`idUnidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS TB_OrdemServico(
  `idOrdemServico` INT(11) NOT NULL,
  `classificacao` ENUM('Corretiva', 'Preventiva') NOT NULL,
  `demanda` ENUM('Normal', 'Urgente') NOT NULL,
  `hora_solicitacao` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nome_solicitante` VARCHAR(60) NOT NULL,
  `hora_atendimento` DATETIME NULL DEFAULT NULL,
  `local` VARCHAR(60) NOT NULL,
  `descricao` VARCHAR(600) NOT NULL,
  PRIMARY KEY (`idOrdemServico`));

CREATE TABLE IF NOT EXISTS TB_ExecutorOrdemServico(
  `idOrdemServico` INT(11) NOT NULL,
  `idFuncionario` INT(11) NOT NULL,
  PRIMARY KEY (`idOrdemServico`, `idFuncionario`),
  FOREIGN KEY (`idOrdemServico`) REFERENCES TB_OrdemServico(`idOrdemServico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`idFuncionario`) REFERENCES TB_Funcionario(`idFuncionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS TB_Itens (
  `TB_OrdemServico_idOrdemServico` INT(11) NOT NULL,
  `TB_Produto_idProduto` INT(11) NOT NULL,
  `quantidade` INT(11) NOT NULL,
  PRIMARY KEY (`TB_OrdemServico_idOrdemServico`, `TB_Produto_idProduto`),
  FOREIGN KEY (`TB_OrdemServico_idOrdemServico`) REFERENCES TB_OrdemServico(`idOrdemServico`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`TB_Produto_idProduto`) REFERENCES TB_Produto(`idProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



