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

## Detalhamento dos Casos de Uso


### UC01 - Cadastrar aluno
* **Pré-condições**: Aluno ainda não existe no sistema
* **Pos-condições**: Aluno existe no sistema

| Fluxo Principal                                         |
| ------------------------------------------------------- |
| 1. Secretaria informa [dados do aluno](#dados-do-aluno) |
| 2. Sistema confirma cadastro                            |


### UC02 - Visualizar aluno
* **Pré-condições**: Aluno existe no sistema
* **Pos-condições**: -

| Fluxo principal                                       |
| ----------------------------------------------------- |
| 1. Secretaria informa numero de cadastro do aluno     |
| 2. Sistema exibe os [dados do aluno](#dados-do-aluno) |


### UC03 - Atualizar aluno
* **Pré-condições**: Aluno existe no sistea
* **Pos-condições**: Aluno é modificado no sistema

| Fluxo principal                                               |
| ------------------------------------------------------------- |
| 1. Secretaria informa número de cadastro do aluno             |
| 2. Secretaria informa novos [dados do aluno](#dados-do-aluno) |
| 3. Sistema confirma atualização do aluno                      |


### UC04 - Remover aluno
* **Pré-condições**: Aluno existe no sistema
* **Pos-condições**: Aluno não existe mais no sistema

| Fluxo principal                                   |
| ------------------------------------------------- |
| 1. Secretaria informa número de cadastro do aluno |
| 2. Sistema confirma remoção do aluno              |


### UC05 - Cadastrar professor
* **Pré-condições**: Professor ainda não existe no sistema
* **Pos-condições**: Professor existe no sistema

| Fluxo principal                                                 |
| --------------------------------------------------------------- |
| 1. Secretaria informa [dados do professor](#dados-do-professor) |
| 2. Sistema confirma cadastro                                    |


### UC06 - Visualizar professor
* **Pré-condições**: Professor existe no sistema
* **Pos-condições**: -

| Fluxo principal                                               |
| ------------------------------------------------------------- |
| 1. Secretaria informa numero de cadastro do professor         |
| 2. Sistema exibe os [dados do professor](#dados-do-professor) |


### UC07 - Atualizar professor
* **Pré-condições**: Professor existe no sistea
* **Pos-condições**: Professor é modificado no sistema

| Fluxo principal                                                       |
| --------------------------------------------------------------------- |
| 1. Secretaria informa número de cadastro do professor                 |
| 2. Secretaria informa novos [dados do professor](#dados-do-professor) |
| 3. Sistema confirma atualização do professor                          |


### UC08 - Remover professor
* **Pré-condições**: Professor existe no sistema
* **Pos-condições**: Professor não existe mais no sistema

| Fluxo principal                                       |
| ----------------------------------------------------- |
| 1. Secretaria informa número de cadastro do professor |
| 2. Sistema confirma remoção do professor              |


### UC09 - Escolher professor orientador

* **Pré-condições**: Aluno existe no sistema e está autenticado
* **Pos-condições**: Professor é associado como orientador do aluno em estado não confirmado

| Fluxo principal                                                              |
| ---------------------------------------------------------------------------- |
| 1. Aluno informa nome completo ou parcial do professor                       |
| 2. Sistema exibe lista de professores que correspondem aos critérios         |
| 3. Aluno informa desejo de ser orientado por algum dos professores exibidos  |
| 4. Sistema confirma recebimento de pedido de escolha de professor orientador |

### UC10 - Agendar reunião com orientador

* **Pré-condições**:
  * Aluno existe no sistema
  * Aluno está autenticado
  * Aluno tem professor orientador escolhido
* **Pos-condições**: Reunião com orientador é agendada

| Fluxo principal                                        |
| ------------------------------------------------------ |
| 1. Aluno informa data e hora e duração da reunião      |
| 2. Sistema exibe confirmação do agendamento de reunião |


### UC11 - Submeter texto para defesa

* **Pré-condições**:
  * Aluno existe no sistema
  * Aluno está autenticado
  * Aluno já agendou defesa
* **Pos-condições**: Texto da defesa está atualizado 

| Fluxo principal                                                |
| -------------------------------------------------------------- |
| 1. Aluno informa identificação da defesa e envia o texto       |
| 2. Sistema exibe confirmação de atualização do texto da defesa |

### UC12 - Cadastrar publicação
* **Pré-condições**
  * Aluno existe no sistema
  * Aluno está autenticado
  * Publicação ainda não existe no sistema
* **Pos-condições**: Publicação existe no sistema

| Fluxo Principal                                                   |
| ----------------------------------------------------------------- |
| 1. Aluno informa [dados da publicação](#dados-da-publicacao)      |
| 2. Sistema confirma cadastro                                      |


### UC13 - Visualizar publicação
* **Pré-condições**
  * Aluno existe no sistema
  * Aluno está autenticado
  * Publicação existe no sistema
* **Pos-condições**: -

| Fluxo principal                                                 |
| --------------------------------------------------------------- |
| 1. Aluno informa numero de cadastro do publicação               |
| 2. Sistema exibe os [dados da publicação](#dados-da-publicacao) |


### UC14 - Atualizar publicação
* **Pré-condições**
  * Aluno existe no sistema
  * Aluno está autenticado
  * Publicação existe no sistea
* **Pos-condições**: Publicação é modificado no sistema

| Fluxo principal                                                         |
| ----------------------------------------------------------------------- |
| 1. Aluno informa número de cadastro do publicação                       |
| 2. Aluno informa novos [dados da publicação](#dados-da-publicacao)      |
| 3. Sistema confirma atualização do publicação                           |


### UC15 - Remover publicação
* **Pré-condições**
  * Aluno existe no sistema
  * Aluno está autenticado
  * Publicação existe no sistema
* **Pos-condições**: Publicação não existe mais no sistema

| Fluxo principal                                        |
| ------------------------------------------------------ |
| 1. Aluno informa número de cadastro do publicação      |
| 2. Sistema confirma remoção do publicação              |


### UC16 - Agendar defesa

* **Pré-condições**: -
* **Pos-condições**: -

| Fluxo principal |
| --------------- |


### UC17 - Modificar banca da defesa

* **Pré-condições**: -
* **Pos-condições**: -

| Fluxo principal |
| --------------- |



### UC18 - Confirmar participação na banca

* **Pré-condições**: -
* **Pos-condições**: -

| Fluxo principal |
| --------------- |



### UC19 - Aprovar defesa

* **Pré-condições**: -
* **Pos-condições**: -

| Fluxo principal |
| --------------- |


### UC20 - Rejeitar defesa

* **Pré-condições**: -
* **Pos-condições**: -

| Fluxo principal |
| --------------- |


### UC21 - Cadastrar seminário
* **Pré-condições**: Seminário ainda não existe no sistema
* **Pos-condições**: Seminário existe no sistema

| Fluxo Principal                                                               |
| ----------------------------------------------------------------------------- |
| 1. Organizador do seminário informa [dados do seminário](#dados-do-seminario) |
| 2. Sistema confirma cadastro                                                  |


### UC22 - Visualizar seminário
* **Pré-condições**: Seminário existe no sistema
* **Pos-condições**: -

| Fluxo principal                                                     |
| ------------------------------------------------------------------- |
| 1. Organizador do seminário informa numero de cadastro do seminário |
| 2. Sistema exibe os [dados do seminário](#dados-do-seminario)       |


### UC23 - Atualizar seminário
* **Pré-condições**: Seminário existe no sistea
* **Pos-condições**: Seminário é modificado no sistema

| Fluxo principal                                                                     |
| ----------------------------------------------------------------------------------- |
| 1. Organizador do seminário informa número de cadastro do seminário                 |
| 2. Organizador do seminário informa novos [dados do seminário](#dados-do-seminario) |
| 3. Sistema confirma atualização do seminário                                        |


### UC24 - Remover seminário
* **Pré-condições**: Seminário existe no sistema
* **Pos-condições**: Seminário não existe mais no sistema

| Fluxo principal                                                     |
| ------------------------------------------------------------------- |
| 1. Organizador do seminário informa número de cadastro do seminário |
| 2. Sistema confirma remoção do seminário                            |


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
