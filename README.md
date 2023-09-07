# Categorizador Automático de Despesas
## Objetivo
Ler os lançamentos de despesas anterires de arquivos do Excel e com base nesse histório identificar a categoria de um novo lançamento de despesa.
## Requisitos de execução - runtime
Esta aplicação escrita em Java 17 roda nos Sistemas Operacionais Windows, Linux e Mac OS.
## Requisitos de desenvolvimento
* A tela foi escrita em Swing utilizando o plugin WindowBuilder do Eclipse.
* Os testes unitários utilizam JUnit 5.

## Como executar a aplicação
1. Faça download do arquivo [categorizador-automatico-de-despesas-*.jar](../../releases/latest)
2. Crie uma pasta categorizador-automatico-de-despesas em C:\
3. Crie um atalho no Desktop e configure como segue:

**Destino:** `java -DdirArquivosExcel="C:\Orçamento doméstico" -jar C:\categorizador-automatico-de-despesas\categorizador-automatico-de-despesas-*.jar`

**Iniciar em:** `C:\categorizador-automatico-de-despesas`

Obs: o diretório "C:\Orçamento doméstico" deve ser substituído pelo caminho completo da pasta dos Orçamentos dométicos.

## Modelo da planilha de Orçamento Doméstico
Um modelo da planilha de Orçamento Doméstico, preenchido com dados fictícios, está disponível no repositório: [Orçamento doméstico - Modelo - 2018.xlsx](/Or%C3%A7amento%20dom%C3%A9stico%20-%20Modelo%20-%202018.xlsx)