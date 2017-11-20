# Casos de Uso #

## Visão geral dos casos de uso ##

```plantuml format="png" classes="uml" alt="Diagrama de Casos de Uso" title="Diagrama de Casos de Uso"
@startuml
!include docs/uc.plantuml

:Membro da Banca: as MB
:Organizador de Seminario: as OS
:Professor: as P
:Orientador: as O
:Aluno: as A
:Administrador: as ADM
:Usuário: as U

U - (Autenticar no sistema)
U - (Desautenticar no sistema)

P -up-|> U
O -up-|> U
A -up-|> U
ADM -up-|> U

CRUD(ADM, aluno)
CRUD(ADM, professor)

A -down- (Escolher professor orientador)
A -down- (Agendar reunião com orientador)
A -down- (Submeter texto para defesa)
CREATE(A,publicação)
READ(U,publicação)
UPDATE(A,publicação)
DESTROY(A,publicação)

O -down- (Agendar defesa)
O -down- (Modificar banca da defesa)

P -down- (Confirmar participação na banca)

CRUD(OS,seminário)

MB -down- (Aprovar defesa)
MB -down- (Rejeitar defesa)

A -|> OS
P -|> OS
P -|> MB
O -|> P

@enduml
```

## Detalhamento dos Casos de Uso (contemplados para a primiera iteração)


### UC01 - Cadastrar aluno
* **Descrição**: O ator deseja cadastrar um novo aluno na plataforma
* **Atores**: Administrador
* **Pré-condições**:
    * Aluno ainda não existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: Aluno existe no sistema

| Fluxo Principal                                                                 |
| ------------------------------------------------------------------------------- |
| 1. O Administrador deverá acessar a página de Alunos.                           |
| 2. O Administrador deverá clicar no botão Cadastrar Aluno                       |
| 3. O sistema exibe o formulário com as informações do aluno a serem preenchidas |
| 4. As informações são: Nome, DRE, Data de Entrada, Orientador, Coorientado      |
| 5. O Administrador deverá clicar no botão Salvar                                |


### UC02 - Visualizar aluno
* **Descrição**:  O ator deseja ver os eventos que estão sendo anunciados na plataforma
* **Atores**: Administrador
* **Pré-condições**:
    * Aluno existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: -

| Fluxo principal                                                            |
| -------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de alunos                    |
| 2. O ator irá visualizar os alunos na plataforma                           |
| 3. O ator clica no aluno de seu interesse                                  |
| 4. O sistema exibe as informações sobre o aluno                            |
| 5. As informações são: Nome, DRE, Data de Entrada, Orientador, Coorientado |


### UC03 - Atualizar aluno
* **Descrição**: O ator deseja que seja possível editar um cadastro de aluno previamente realizado.
* **Atores**: Administrador
* **Pré-condições**:
    * Aluno existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: Aluno é modificado no sistema

| Fluxo principal                                                           |
| ------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de alunos                   |
| 2. O ator deverá clicar no botão indicado para editar)                    |
| 3. O ator poderá editar os seguintes campos: Nome Orientador Coorientador |
| 4. O ator  deverá clicar em salvar para que as mudanças sejam efetuadas.  |



### UC04 - Remover aluno
* **Descrição**: O ator deseja deletar um aluno
* **Atores**: Administrador
* **Pré-condições**:
    * Aluno existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: Aluno não existe mais no sistema

| Fluxo principal                                                                                         |
| ------------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de alunos.                                                |
| 2. O ator irá visualizar os alunos na plataforma                                                        |
| 3. O ator clica no botão Deletar ao lado do Aluno que deseja deletar                                    |
| 4. O Sistema irá exibir uma mensagem perguntando se o Administrador deseja realmente deletar esse aluno |
| 5. O Administrador deverá clicar no botão Deletar                                                       |


### UC05 - Cadastrar professor
* **Descrição**: O administrador do sistema deseja que seja possível cadastrar um novo professor no sistema
* **Atores**: Administrador
* **Pré-condições**:
    * Professor ainda não existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: Professor existe no sistema

| Fluxo principal                                                                                  |
| ------------------------------------------------------------------------------------------------ |
| 1. O ator deverá acessar a página de listagem de professores                                     |
| 2. O ator deverá clicar no botão Cadastrar Professor                                             |
| 3. O sistema exibe o formulário com as informações do professor a serem preenchidas.             |
| 4. As informações são: Nome, Link para o lattes, Programa, Linha de pesquisa, Áreas de interesse |


### UC06 - Visualizar professor
* **Descrição**: O ator deseja ver os eventos que estão sendo anunciados na plataforma
* **Atores**: Administrador
* **Pré-condições**:
    * Professor existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: -

| Fluxo principal                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------ |
| 1. O ator deverá acessar a página de listagem de professores                                                                   |
| 2. O ator irá visualizar os professores cadastrados na plataforma                                                              |
| 3. O ator clica no professor de seu interesse.                                                                                 |
| 4. O sistema exibe as informações sobre o professor: Nome, Link para o lattes, Programa, Linha de pesquisa, Áreas de interesse |


### UC07 - Atualizar professor
* **Descrição**: O ator do sistema deseja que seja possível editar um cadastro de professor previamente realizado
* **Atores**: Administrador
* **Pré-condições**:
    * Professor existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: Professor é modificado no sistema

| Fluxo principal                                                                                              |
| ------------------------------------------------------------------------------------------------------------ |
| 1. O ator deverá acessar a página de listagem de professores                                                 |
| 2. O ator deverá clicar no botão indicado para editar.                                                       |
| 3. O ator poderá editar os campos: Nome, Link para o lattes, Programa, Linha de pesquisa, Áreas de interesse |
| 4. O ator deverá clicar em salvar para que as mudanças sejam efetuadas                                       |


### UC08 - Remover professor
* **Descrição**: O ator deseja deletar o um Professor do sistema.
* **Atores**: Administrador
* **Pré-condições**:
    * Professor existe no sistema
    * O ator deve estar logado no sistema
* **Pos-condições**: Professor não existe mais no sistema

| Fluxo principal                                                                                    |
| -------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de professores                                       |
| 2. O ator irá visualizar os professores registrados na plataforma                                  |
| 3. O ator deverá clicar no botão Deletar ao lado do Professor que deseja deletar                   |
| 4. O Sistema irá exibir uma mensagem perguntando se o ator deseja realmente deletar esse Professor |


### UC09 - Escolher professor orientador
* **Descrição**: O ator deseja que seja possível escolher o professor orientador
* **Atores**: Aluno
* **Pré-condições**:
    * Ator existe no sistema e está autenticado
    * O ator deve estar logado no sistema
* **Pos-condições**: Professor é associado como orientador do ator em estado não confirmado

| Fluxo principal                                                                             |
| ------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página do seu cadastro                                           |
| 2. O ator deverá clicar no botão de edição de dados do seu cadastro                         |
| 3. O ator deverá escolher um professor da lista de professores disponibilizada pelo sistema |
| 4. O ator deverá clicar no botão Salvar                                                     |


### UC10 - Cadastrar publicação
* **Descrição**: O aluno deseja uma nova publicação sua no sistema
* **Atores**: Aluno
* **Pré-condições**:
    * Aluno existe no sistema
    * O ator deve estar logado no sistema
    * Aluno tem professor orientador escolhido
* **Pos-condições**: Publicação é registrada no sistema

| Fluxo principal                                                                                           |
| --------------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de publicações                                                          |
| 2. O ator irá visualizar todas as publicações cadastradas no sistemas                                     |
| 3. O ator deverá clicar em Cadastrar Publicação                                                           |
| 4. O sistema exibe o formulário com as informações da publicação a serem preenchidas: link, colaboradores |
| 5. O autor deverá clicar no botão salvar                                                                  |


### UC11 - Visualizar publicação
* **Descrição**: O ator deseja ver os dados de uma publicação cadastrada no sistema
* **Atores**: Aluno
* **Pré-condições**:
    * O ator deve estar logado no sistema
    * A publicação deverá estar previamente cadastrada no sistema
* **Pos-condições**: -

| Fluxo principal                                                                                    |
| -------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de publicações                                       |
| 2. O ator irá clicar no botão de visualização dos dados cadastrados da sua publicação de interesse |
| 3. O sistema exibe as informações sobre a publicação: link, coautores                              |


### UC12 - Remover publicação
* **Descrição**:  O ator deseja deletar uma publicação realizada
* **Atores**: Aluno
* **Pré-condições**
    * Aluno existe no sistema
    * O ator deve estar logado no sistema
    * Publicação existe no sistema
* **Pos-condições**: Publicação não existe no sistema

| Fluxo Principal                                                                                     |
| --------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de publicações                                        |
| 2. O ator deverá encontrar a publicação que deseja deletar                                          |
| 3. O ator deverá clicar no botão Deletar ao lado da publicação que deseja deletar                   |
| 4. O Sistema irá exibir uma mensagem perguntando se o ator deseja realmente deletar essa publicação |
| 5. o ator deverá clicar no botão Deletar                                                            |


### UC13 - Cadastrar Seminário
* **Descrição**: O ator deseja cadastrar um seminário no sistema
* **Atores**: Organizador de Seminário
* **Pré-condições**
    * O ator deve estar logado no sistema
* **Pos-condições**:  Seminário existe no sistema

| Fluxo principal                                                                                                                                                              |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de seminários                                                                                                                              |
| 2. O Aluno deverá clicar no botão Cadastrar Seminário                                                                                                                        |
| 3. O sistema exibe o formulário para cadastramento de seminário com as seguintes informações : Data do Seminário, Hora do Seminário, Local do seminário, Título do seminário |
| 4. O ator deverá clicar no botão Salvar                                                                                                                                      |


### UC14 - Atuaizar seminário
* **Descrição**:  O ator deseja editar um seminário cadastrado
* **Atores**: Organizador de Seminário
* **Pré-condições**
    * O ator deve estar logado no sistema
    * Seminário existe no sistema
* **Pos-condições**: Seminário é modificado no sistema

| Fluxo principal                                                                                                                 |
| ------------------------------------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de Seminários                                                                                 |
| 2. O ator deverá encontrar o Seminário que ele deseja editar                                                                    |
| 3. O ator deverá clicar no Botão editar                                                                                         |
| 4. O ator poderá editar as seguintes informações: Data do Seminário, Hora do Seminário, Local do Seminário, Título do Seminário |


### UC15 - Visualizar Seminário
* **Descrição**: O ator deseja visualizar os seminários agendados no sistema
* **Atores**: Organizador de Seminário
* **Pré-condições**
    * O ator deve estar logado no sistema
    * Seminário existe no sistea
* **Pos-condições**: -

| Fluxo principal                                                                                                              |
| ---------------------------------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de Seminários                                                                              |
| 2. O ator deverá escolher o seminário desejado a partir da lista disponibilizada pelo sistema                                |
| 3. O sistema exibirá as seguintes informações: Data do Seminário, Hora do Seminário, Local do Seminário, Título do Seminário |


### UC16 - Remover seminário
* **Descrição**: O ator deseja que seja possível deletar o um Professor do sistema.
* **Atores**: Organizador de Seminário
* **Pré-condições**:
    * O ator deve estar logado no sistema
    * Seminário existe no sistema
* **Pos-condições**: O seminário não existe no sistema

| Fluxo principal                                                                                    |
| -------------------------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de seminários                                        |
| 2. O ator deverá encontrar o seminário que deseja deletar                                          |
| 3. O ator deverá clicar no botão Deletar ao lado do seminário que deseja deletar                   |
| 4. O Sistema irá exibir uma mensagem perguntando se o ator deseja realmente deletar esse seminário |
| 5. o ator deverá clicar no botão Deletar                                                           |


### UC17 - Modificar publicação
* **Descrição**:  O ator deseja editar uma publicação cadastrada
* **Atores**: Aluno
* **Pré-condições**
    * O ator deve estar logado no sistema
    * Publicação existe no sistema
* **Pos-condições**: Publicação é modificada no sistema

| Fluxo principal                                                         |
| ----------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de publicações                        |
| 2. O ator deverá encontrar a publicação que ele deseja editar           |
| 3. O ator deverá clicar no Botão editar                                 |
| 4. O ator poderá editar as seguintes informações: link, coautores       |


### UC18 - Escolher Professor Orientador
* **Descrição**:  O ator deseja escolher um professor orientador para sua pesquisa
* **Atores**: Aluno
* **Pré-condições**
    * O ator deve estar logado no sistema
    * O professor deve estar cadastrado no sistema
* **Pos-condições**: Um professor é associado ao aluno como seu orientador

| Fluxo principal                                                         |
| ----------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de gerenciamento da pesquisa          |
| 2. O ator deverá escolher seu orientador em uma lista                   |
| 3. O ator deverá confirmar a escolha                                    |
| 4. O professor escolhido será associado ao ator                         |


### UC19 - Agendar Reuniões com o Orientador
* **Descrição**:  O ator deseja agendar uma reunião com o professor orientador
* **Atores**: Aluno
* **Pré-condições**
    * O ator deve estar logado no sistema
    * O ator deverá ter um professor orientador associado
* **Pos-condições**: Uma reunião é agendada com o professor orientador

| Fluxo principal                                                                   |
| --------------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de gerenciamento                                |
| 2. O ator deverá selecionar a opção de agendar reunião                            |
| 3. O ator deverá escolher um horário dentre os disponibilizados pelo orientador   |
| 4. O ator deverá confirmar o horário escolhido                                    |

## Declaração de casos de uso não contemplados para iteração

Os casos de uso não especificados neste documento neste momento serão contemplados na próxima iteração.
