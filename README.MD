# gear :gear: (Em construção) 

[![Build Status](https://travis-ci.com/AndreM-Gomes/gear-project.svg?branch=master)](https://travis-ci.com/AndreM-Gomes/gear-project) [![codecov](https://codecov.io/gh/AndreM-Gomes/gear-project/branch/master/graph/badge.svg)](https://codecov.io/gh/AndreM-Gomes/gear-project) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

  Back-end REST em Spring para administração de inventários de equipes de manutenção
  Projeto com propósito de exercício no uso da stack Spring.
## Completo
  - CRUD de **produtos** do inventário;
  - CRUD de **unidades**("sede" das equipes);
  - CRUD de **itens** dos estoques;
  
## TODO

  - Transferência de itens entre unidades;
  - CRUD de **funcionários**;
  - CRUD de **ordens de serviço**;
  - Autenticação de funcionários;
  - Busca de itens por distância entre as unidades;
  - Documentação da API

## Pre-requisitos
  - Docker - [como instalar no Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
  - docker-compose - [ como instalar no Ubuntu](https://docs.docker.com/compose/install/)
  - git (A maioria das distribuições vem como o git instalado por default)

## Instalando e iniciando o projeto

  O projeto utiliza docker-compose para construir o ambiente de funcionamento (subindo os containers necessários e banco de dados)
```bash
git clone https://github.com/AndreM-Gomes/gear-project.git
cd gear-project
docker-compose down --remove-orphans
docker-compose up
```