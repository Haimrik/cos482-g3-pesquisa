# Plano de Testes

## Estratégia

Serão realizados testes unitários e funcionais. O objetivo dos testes é assegurar que todas as componentes críticas do sistema, isto é, aquelas que dependem e interagem com a camada de dados e aquelas que realizam e implementam requisitos do sistema funcionem como o previsto.

* Teste Unitário

    Teste dos componentes básicos utilizados pelo sistema. Se tratam de testes básicos, que devem garantir apenas que os componentes e métodos fundamentais do sistema estão se comportando da maneira prevista.

    1. Cronograma: Testes unitários serão realizados durante todas as etapas de validação dos componentes do sistema. Toda versão estável e de desenvolvimento deverá passar pelos testes unitários.
    2. Recursos: Esse tipo de teste depende do ambiente de desenvolvimento. Os testes são realizados automaticamente pelo framework de desenvolvimento.


* Teste Funcional

    Teste dos componentes responsáveis por regras e requisitos do sistema.

    1. Cronograma: Testes funcionais serão realizados durante as etapas finais de validação dos componentes do sistema. Toda versão estável deverá passar pelos testes funcionais.
    2. Recursos: Esse tipo de teste depende do ambiente de desenvolvimento e do desenvolvedor do componente a ser testado. Os testes são realizados automaticamente pelo framework de desenvolvimento, mas podem requerer validação humana.


## Casos de Teste

   Definicão de casos a serem implementados e executados nas rodadas de teste.

   * Template de Caso de Teste:
        * Cenário  de teste: verificacão da funcionalidade a ser testada
        * Caso de teste: Caso de uso contemplado
        * Pré-condicoões: requisitos para execucão do teste
        * Etapas de teste: passo a passo da execucão do teste
        * Dados de teste: Dados a serem utilizados nas execucoes dos testes
        * Resultado esperado: deficicão de sucesso do teste
        * Pós-condicões: Estado esperado do sistema


## T1
        * Cenário de teste: Verificação da funcionalidade de Cadastro de Aluno
        * Caso de teste: UC1
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: Realizar o UC1 conforme descrito no Fluxo Principal
        * Dados de teste: Nome: Henrique Maio, DRE: 113079417, Data de Entrada: 01/01/2013, Orientador: Professor Orientador, Coorientador: Coorientador.
        * Resultado esperado: O usuário deverá ser cadastrado no sistema e ser exibido na lista de Alunos
        * Pós-condicões:

## T2
        * Cenário de teste: Verificar cadastro de Aluno sem preencher campos obrigatórios
        * Caso de teste: UC1
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Nome: Henrique Maio, DRE: NULL, Data de Entrada: 01/01/2013, Orientador: Professor Orientador, Coorientador: Coorientador.
        * Resultado esperado: O sistema não deverá permitir o cadastro do Aluno
        * Pós-condicões:


## T3
        * Cenário de teste: Verificação da funcionalidade de Editar Cadastro de Aluno
        * Caso de teste: UC2
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Nome: Henrique Maio, Orientador: Professor Orientador, Coorientador: Coorientador.
        * Resultado esperado: As alterações deverão ser salvas no sistema
        * Pós-condicões:


## T4
        * Cenário de teste: Verificar a edição do cadastro de Aluno sem preencher campos obrigatórios
        * Caso de teste: UC2
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Nome: NULL, Orientador: Professor Orientador, Coorientador: Coorientador.
        * Resultado esperado: O sistema não deverá permitir salvar essa alteração
        * Pós-condicões:

## T5
        * Cenário de teste: Verificação da funcionalidade Visualizar Cadastro do Aluno
        * Caso de teste: UC3
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso. O ator deverá clicar no aluno Henrique Maio
        * Dados de teste: Nome: Henrique Maio, DRE: 113079417, Data de Entrada: 01/01/2013, Orientador: Professor Orientador, Coorientador: Coorientador.
        * Resultado esperado: As informações do aluno Henrique Maio deverão ser exibidas
        * Pós-condicões:

## T6
        * Cenário de teste: Verificação da funcionalidade Deletar Aluno
        * Caso de teste: UC4
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso. O ator deverá clicar em Deletar no aluno Henrique Maio
        * Dados de teste:
        * Resultado esperado: O Aluno não será mais exibido na lista de Alunos
        * Pós-condicões:

## T7
        * Cenário de teste: Verificação da funcionalidade de Cadastro de Professor
        * Caso de teste: UC5
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: Realizar o UC5 conforme descrito no Fluxo Principal
        * Dados de teste: Nome: Toacy Olibeira, Link para o lattes: http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4703923H4, Programa: PESC, Linha de pesquisa: Engenharia de Sistemas, Áreas de interesse: Qualidade de Software
        * Resultado esperado: O professor deverá ser cadastrado no sistema e ser exibido na lista de Professores
        * Pós-condicões:

## T8
        * Cenário de teste: Verificar cadastro de Professor sem preencher campos obrigatórios
        * Caso de teste: UC5
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Nome: NULL, Link para o lattes: http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4703923H4, Programa: PESC, Linha de pesquisa: Engenharia de Sistemas, Áreas de interesse: Qualidade de Software
        * Resultado esperado: O sistema não deverá permitir o cadastro do Professor
        * Pós-condicões:

## T9
        * Cenário de teste: Verificação da funcionalidade de Editar Cadastro de professor
        * Caso de teste: UC6
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Nome: Toacy Olibeira, Link para o lattes: http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4703923H4, Programa: PESC, Linha de pesquisa: Engenharia de Sistemas, Áreas de interesse: Qualidade de Software
        * Resultado esperado: As alterações deverão ser salvas no sistema
        * Pós-condicões:

## T10
        * Cenário de teste: Verificar a edição do cadastro de professor sem preencher campos obrigatórios
        * Caso de teste: UC6
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Nome: NULL, Link para o lattes: http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4703923H4, Programa: PESC, Linha de pesquisa: Engenharia de Sistemas, Áreas de interesse: Qualidade de Software
        * Resultado esperado: O sistema não deverá permitir salvar essa alteração
        * Pós-condicões:

## T11
        * Cenário de teste: Verificação da funcionalidade Visualizar Cadastro do Professor
        * Caso de teste: UC7
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso. O ator deverá clicar no professor Toacy Oliveira
        * Dados de teste: Nome: Toacy Olibeira, Link para o lattes: http://buscatextual.cnpq.br/buscatextual/visualizacv.do?id=K4703923H4, Programa: PESC, Linha de pesquisa: Engenharia de Sistemas, Áreas de interesse: Qualidade de Software
        * Resultado esperado: As informações do professor Toacy Oliveira deverão ser exibidas
        * Pós-condicões:

## T12
        * Cenário de teste: Verificação da funcionalidade Deletar Professor
        * Caso de teste: UC8
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso. O ator deverá clicar em Deletar no professor Toacy Oliveira
        * Dados de teste:
        * Resultado esperado: O Professor não será mais exibido na lista de professores
        * Pós-condicões:

# T13
        * Cenário de teste: Verificação da funcionalidade de Cadastrar Publicação
        * Caso de teste: UC10
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: Realizar o UC10 conforme descrito no Fluxo Principal
        * Dados de teste: Link: www.google.com , Coautores: Toacy Oliveira
        * Resultado esperado: A publicação deverá ser cadastrada no sistema e ser exibida na lista de Publicações
        * Pós-condicões:

## T14
        * Cenário de teste: Verificar cadastro de publicação sem preencher campos obrigatórios
        * Caso de teste: UC10
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Link: NULL, Coautores: Toacy Oliveira
        * Resultado esperado: O sistema não deverá permitir o cadastro do Aluno
        * Pós-condicões:

## T15
        * Cenário de teste: Verificação da funcionalidade Visualizar Cadastro da Publicação
        * Caso de teste: UC11
        * Pré-condições: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso.
        * Dados de teste:
        * Resultado esperado: A lista com todas as publicações cadastradas deverá ser exibida
        * Pós-condições:


## T16
        * Cenário de teste: Verificação da funcionalidade Deletar Publicação
        * Caso de teste: UC12
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso. O ator deverá clicar em Deletar na publicação do aluno Henrique Maio
        * Dados de teste:
        * Resultado esperado: A Publicação não será mais exibida na lista de publicações
        * Pós-condicões:

# T17
        * Cenário de teste: Verificação da funcionalidade de Cadastrar Seminário
        * Caso de teste: UC11
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: Realizar o UC10 conforme descrito no Fluxo Principal
        * Dados de teste: Data do Seminário: 01/01/2018, Hora do Seminário: 10:00, Local do seminário: UFRJ, Título do seminário: Trabalho de QS
        * Resultado esperado: O seminário deverá ser cadastrado no sistema e ser exibida na lista de Seminários
        * Pós-condicões:

## T18
        * Cenário de teste: Verificar cadastro de seminários sem preencher campos obrigatórios
        * Caso de teste: UC10
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Link:  Data do Seminário: NULL, Hora do Seminário: 10:00, Local do seminário: UFRJ, Título do seminário: Trabalho de QS
        * Resultado esperado: O sistema não deverá permitir o cadastro do seminário
        * Pós-condicões:

## T19
        * Cenário de teste: Verificação da funcionalidade de Editar Cadastro de seminário
        * Caso de teste: UC14
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Data do Seminário: 01/01/2018, Hora do Seminário: 11:00, Local do seminário: UFRJ, Título do seminário: Trabalho de QS
        * Resultado esperado: As alterações deverão ser salvas no sistema
        * Pós-condicões:

## T20
        * Cenário de teste: Verificar a edição do seminário sem preencher campos obrigatórios
        * Caso de teste: UC14
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso
        * Dados de teste: Nome: Data do Seminário: NULL, Hora do Seminário: 10:00, Local do seminário: UFRJ, Título do seminário: Trabalho de QS
        * Resultado esperado: O sistema não deverá permitir salvar essa alteração
        * Pós-condicões:

## T21
        * Cenário de teste: Verificação da funcionalidade Visualizar Seminário
        * Caso de teste: UC15
        * Pré-condições: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso.
        * Dados de teste:
        * Resultado esperado: A lista com todos os seminários cadastrados deverá ser exibida
        * Pós-condições:

## T22
        * Cenário de teste: Verificação da funcionalidade Deletar Seminário
        * Caso de teste: UC12
        * Pré-condicoões: O ator deve estar logado no sistema
        * Etapas de teste: O Ator deverá seguir o Fluxo Principal do caso de uso. O ator deverá clicar em Deletar no seminário do aluno Henrique Maio
        * Dados de teste:
        * Resultado esperado: A Publicação não será mais exibida na lista de publicações
        * Pós-condicões:
