# Casos de Uso #

## Visão geral dos casos de uso ##

```plantuml format="png" classes="uml" alt="Diagrama de Casos de Uso" title="Diagrama de Casos de Uso"
@startuml
left to right direction
skinparam packageStyle rect

!define CREATE(actor,model) actor -down- (Cadastrar model) 
!define READ(actor,model) actor -down- (Visualizar model) 
!define UPDATE(actor,model) actor -down- (Atualizar model) 
!define DESTROY(actor,model) actor -down- (Remover model) 
!definelong CRUD(actor, model)
  CREATE(actor,model)
  READ(actor,model)
  UPDATE(actor,model)
  DESTROY(actor,model)
!enddefinelong

:Membro da Banca: as MB
:Organizador de Seminario: as OS
:Professor: as P
:Orientador: as O
:Aluno: as A
:Secretaria: as S

CRUD(S, aluno)
CRUD(S, professor)

A -down- (Escolher professor orientador)
A -down- (Agendar reunião com orientador)
A -down- (Submeter texto para defesa)
CRUD(A,publicação)

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
| -------------------------------------------------------                         |
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

| Fluxo principal                                       |
| ----------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de alunos |
| 2. O ator irá visualizar os alunos na plataforma        |
| 3. O ator clica no aluno de seu interesse               |
| 4. O sistema exibe as informações sobre o aluno         |
| 5. As informações são: Nome, DRE, Data de Entrada, Orientador, Coorientado  |


### UC03 - Atualizar aluno
* **Descrição**: O ator deseja que seja possível editar um cadastro de aluno previamente realizado.
* **Atores**: Administrador
* **Pré-condições**: 
  * Aluno existe no sistema
  * O ator deve estar logado no sistema
* **Pos-condições**: Aluno é modificado no sistema

| Fluxo principal                                               |
| ------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de alunos       | 
| 2. O ator deverá clicar no botão indicado para editar)        |
| 3. O ator poderá editar os seguintes campos: Nome Orientador Coorientador |
| 4. O ator  deverá clicar em salvar para que as mudanças sejam efetuadas.  |



### UC04 - Remover aluno
* **Descrição**: O ator deseja deletar um aluno
* **Atores**: Administrador
* **Pré-condições**: 
  * Aluno existe no sistema
  * O ator deve estar logado no sistema
* **Pos-condições**: Aluno não existe mais no sistema

| Fluxo principal                                   |
| ------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de alunos. |
| 2. O ator irá visualizar os alunos na plataforma         |
| 3. O ator clica no botão Deletar ao lado do Aluno que deseja deletar |
| 4. O Sistema irá exibir uma mensagem perguntando se o Administrador deseja realmente deletar esse aluno |
| 5. O Administrador deverá clicar no botão Deletar |


### UC05 - Cadastrar professor
* **Descrição**: O administrador do sistema deseja que seja possível cadastrar um novo professor no sistema
* **Atores**: Administrador
* **Pré-condições**: 
  * Professor ainda não existe no sistema
  * O ator deve estar logado no sistema
* **Pos-condições**: Professor existe no sistema

| Fluxo principal                                                 |
| --------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de professores    |
| 2. O ator deverá clicar no botão Cadastrar Professor            |
| 3. O sistema exibe o formulário com as informações do professor a serem preenchidas. |
| 4. As informações são: Nome, Link para o lattes, Programa, Linha de pesquisa, Áreas de interesse |


### UC06 - Visualizar professor
* **Descrição**: O ator deseja ver os eventos que estão sendo anunciados na plataforma
* **Atores**: Administrador
* **Pré-condições**: 
  * Professor existe no sistema
  * O ator deve estar logado no sistema
* **Pos-condições**: -

| Fluxo principal                                               |
| ------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de professores  |
| 2. O ator irá visualizar os professores cadastrados na plataforma |
| 3. O ator clica no professor de seu interesse. |
| 4. O sistema exibe as informações sobre o professor: Nome, Link para o lattes, Programa, Linha de pesquisa, Áreas de interesse |


### UC07 - Atualizar professor
* **Descrição**: O ator do sistema deseja que seja possível editar um cadastro de professor previamente realizado
* **Atores**: Administrador
* **Pré-condições**: 
  * Professor existe no sistema
  * O ator deve estar logado no sistema
* **Pos-condições**: Professor é modificado no sistema

| Fluxo principal                                                       |
| --------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de professores          |
| 2. O ator deverá clicar no botão indicado para editar.                |
| 3. O ator poderá editar os campos: Nome, Link para o lattes, Programa, Linha de pesquisa, Áreas de interesse |
| 4. O ator deverá clicar em salvar para que as mudanças sejam efetuadas |


### UC08 - Remover professor
* **Descrição**: O ator deseja deletar o um Professor do sistema.
* **Atores**: Administrador
* **Pré-condições**: 
  * Professor existe no sistema
  * O ator deve estar logado no sistema
* **Pos-condições**: Professor não existe mais no sistema

| Fluxo principal                                       |
| ----------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de professores      |   
| 2. O ator irá visualizar os professores registrados na plataforma |
| 3. O ator deverá clicar no botão Deletar ao lado do Professor que deseja deletar |
| 4. O Sistema irá exibir uma mensagem perguntando se o ator deseja realmente deletar esse Professor |


### UC09 - Cadastrar professor orientador
* **Descrição**: O ator deseja que seja possível deletar o um Professor do sistema.
* **Atores**: Administrador
* **Pré-condições**: 
  * Ator existe no sistema e está autenticado
  * O ator deve estar logado no sistema
* **Pos-condições**: Professor é associado como orientador do ator em estado não confirmado

| Fluxo principal                                                              |
| ---------------------------------------------------------------------------- |
| 1. O ator deverá acessar a página do seu cadastro                            |
| 2. O ator deverá clicar no botão de edição de dados do seu cadastro          |
| 3. O ator deverá escolher um professor da lista de professores disponibilizada pelo sistema   |
| 4. O ator deverá clicar no botão Salvar                                      |


### UC10 - Cadastrar publicação
* **Descrição**: O aluno deseja uma nova publicação sua no sistema
* **Atores**: Aluno 
* **Pré-condições**:
  * Aluno existe no sistema
  * O ator deve estar logado no sistema
  * Aluno tem professor orientador escolhido
* **Pos-condições**: Publicação é registrada no sistema

| Fluxo principal                                        |
| ------------------------------------------------------ |
| 1. O ator deverá acessar a página de publicações      |
| 2. O ator irá visualizar todas as publicações cadastradas no sistemas |
| 3. O ator deverá clicar em Cadastrar Publicação |
| 4. O sistema exibe o formulário com as informações da publicação a serem preenchidas: link, colaboradores |
| 5. O autor deverá clicar no botão salvar |


### UC11 - Visualizar Cadastro da Publicação
* **Descrição**: O ator deseja ver os dados de uma publicação cadastrada no sistema
* **Atores**: Usuário logado
* **Pré-condições**:
  * O ator deve estar logado no sistema
  * A publicação deverá estar previamente cadastrada no sistema
* **Pos-condições**: -

| Fluxo principal                                                |
| -------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de publicações       |
| 2. O ator irá clicar no botão de visualização dos dados cadastrados da sua publicação de interesse  |
| 3. O sistema exibe as informações sobre a publicação: link, coautores |


### UC12 - Remover publicação
* **Descrição**:  O ator deseja deletar uma publicação realizada
* **Atores**: Aluno
* **Pré-condições**
  * Aluno existe no sistema
  * O ator deve estar logado no sistema
  * Publicação existe no sistema
* **Pos-condições**: Publicação não existe no sistema

| Fluxo Principal                                                   |
| ----------------------------------------------------------------- |
| 1. O ator deverá acessar a página de listagem de publicações      |
| 2. O ator deverá encontrar a publicação que deseja deletar        |
| 3. O ator deverá clicar no botão Deletar ao lado da publicação que deseja deletar |
| 4. O Sistema irá exibir uma mensagem perguntando se o ator deseja realmente deletar essa publicação |
| 5. o ator deverá clicar no botão Deletar |


### UC13 - Cadastrar Seminário
* **Descrição**: O ator deseja cadastrar um seminário no sistema
* **Atores**: Aluno ou professor
* **Pré-condições**
  * O ator deve estar logado no sistema
* **Pos-condições**:  Seminário existe no sistema

| Fluxo principal                                                 |
| --------------------------------------------------------------- |
| 1. O ator deverá acessar a página de seminários                 |
| 2. O Aluno deverá clicar no botão Cadastrar Seminário           |
| 3. O sistema exibe o formulário para cadastramento de seminário com as seguintes informações : Data do Seminário, Hora do Seminário, Local do seminário, Título do seminário |
| 4. O ator deverá clicar no botão Salvar                         |


### UC14 - Editar cadastro de Seminário
* **Descrição**:  O ator deseja editar um seminário cadastrado
* **Atores**: Aluno ou professor
* **Pré-condições**
  * O ator deve estar logado no sistema
  * Seminário existe no sistema
* **Pos-condições**: Seminário é modificado no sistema

| Fluxo principal                                                         |
| ----------------------------------------------------------------------- |
| 1. O ator deverá acessar a página de Seminários                         |
| 2. O ator deverá encontrar o Seminário que ele deseja editar            |
| 3. O ator deverá clicar no Botão editar                                 |
| 4. O ator poderá editar as seguintes informações: Data do Seminário, Hora do Seminário, Local do Seminário, Título do Seminário |


### UC15 - Visualizar Seminário
* **Descrição**: O ator deseja visualizar os seminários agendados no sistema
* **Atores**: Aluno ou professor
* **Pré-condições**
  * O ator deve estar logado no sistema
  * Seminário existe no sistea
* **Pos-condições**: -

| Fluxo principal                                        |
| ------------------------------------------------------ |
| 1. O ator deverá acessar a página de Seminários        |
| 2. O ator deverá escolher o seminário desejado a partir da lista disponibilizada pelo sistema |
| 3. O sistema exibirá as seguintes informações: Data do Seminário, Hora do Seminário, Local do Seminário, Título do Seminário |


### UC16 - Deletar Seminário
* **Descrição**: O ator deseja que seja possível deletar o um Professor do sistema.
* **Atores**: Administrador
* **Pré-condições**:
  * O ator deve estar logado no sistema
  * Seminário existe no sistema
* **Pos-condições**: O seminário não existe no sistema

| Fluxo principal |
| --------------- |
| 1. O ator deverá acessar a página de listagem de seminários      |
| 2. O ator deverá encontrar o seminário que deseja deletar        |
| 3. O ator deverá clicar no botão Deletar ao lado do seminário que deseja deletar |
| 4. O Sistema irá exibir uma mensagem perguntando se o ator deseja realmente deletar esse seminário |
| 5. o ator deverá clicar no botão Deletar |


### UC17 - Modificar cadastro de publicação
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


## Declaração de casos de uso não contemplados para iteração 


###  Escolher professor orientador

### Agendar reunião com orientador

### Submeter texto para defesa

### Agendar defesa

### Modificar banca da defesa

### Confirmar participação na banca

### Aprovar defesa

### Rejeitar defesa


## Tabelas auxiliares

### Dados do aluno

| Campo | Descrição |
| ----- | --------- |
| Nome  | Nome Completo |
| DRE   | Cadastro na universidade |


### Dados do professor

| Campo | Descrição |
| ----- | --------- |
| Nome  | Nome do professor |

### Dados da publicação

| Campo              | Descrição                                                                       |
| ------------------ | ------------------------------------------------------------------------------- |
| TipoPublicacao     | Pode ser Revista, Congresso ou Periódico                                        |
| PertenceAoPrograma | Verdadeiro ou falso. Se verdadeiro, esta publicação faz da pesquisa do programa |
| Coautores          | Lista de coautores                                                              |

### Dados do seminário

| Campo     | Descricão                                            |
| --------- | ---------------------------------------------------- |
| Titulo    | Título do seminário                                  |
| DataEHora | Quando o seminário ira acontecer                     |
| Local     | Local onde o seminário irá acontecer. Ex: Sala H310A |
