# Casos de Uso #

## Visão geral dos casos de uso ##

```plantuml format="png" classes="uml myDiagram" alt="My super diagram placeholder" title="My super diagram"
@startuml
left to right direction
skinparam packageStyle rect

:Orientador: as O
:Aluno: as A
:Sistema de Matrícula: as SM

(Cadastrar aluno) as (CA)
(Visualizar alunos) as (RA)
(Atualziar aluno) as (UA)
(Remover aluno) as (DA)

SM -down- (CA)
SM -down- (RA)
SM -down- (UA)
SM -down- (DA)

(Enviar publicação) as (CP)
(Visualizar publicações) as (RP)
(Atualizar publicação) as (UP)
(Remover publicação) as (DP)

A -down- (Agendar reunião com orientador)
A -down- (CP)
A -down- (RP)
A -down- (UP)
A -down- (DP)
A -down- (Submeter proposta)

O -down- (Agendar defesa)
O -down- (Agendar qualificação)
O -down- (Convidar para banca)
@enduml
```
