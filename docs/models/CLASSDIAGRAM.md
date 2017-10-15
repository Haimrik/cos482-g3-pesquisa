# Diagrama de Classes (ou modelo JDL)

```plantuml format="png" classes="uml myDiagram" alt="My super diagram placeholder" title="My super diagram"
@startuml
' Cadastro

class Aluno {
  Int id
  String nome
  String dre
  Date dataDeEntrada
  
  escolherOrientador(Orientador novoOrientador)
}

class Professor {
  Int id
  String nome
  String linkLattes
  String programa
  String linhaDePesquisa
  List<String> areasDeInteresse
}


' Seminario

class Seminario {
  Int id
  String titulo
  DateTime dataEHora
  String local
}

Seminario "0..*" -left- "1" OrganizadorDeSeminario : organizador
OrganizadorDeSeminario <|-down- Aluno
OrganizadorDeSeminario <|-down- Professor


' Defesa

enum TipoDefesa {
  Qualificacao
  DefesaDeTese
}

class Defesa {
  DateTime dataEHora
  String local
  File arquivoTexto
  
  submeterTexto(File arquivoTexto)
  EstadoAprovacaoDefesa obterEstado()
}

note right of Defesa::obterEstado
  Se algum membro da banca rejeitou,
  o estado final é Rejeitado

  Caso contrario, mas se algum membro
  da  banca está com estado Pendente,
  estado final é Pendente

  Por fim, se todos os estados dos
  membros estão como Aprovado, estado
  final é aprovado
end note

class ParticipacaoBanca {
  Bool confirmado
  confirmarParticipacao()
  aprovarDefesa()
  rejeitarDefesa()
}

enum EstadoAprovacaoDefesa {
  Pendente
  Aprovado
  Rejeitado
}

class MembroDaBanca {
}

Defesa "0..*" -up- "1" Aluno
Defesa "1" - "1..*" ParticipacaoBanca
Defesa -down- TipoDefesa
ParticipacaoBanca -down- EstadoAprovacaoDefesa
ParticipacaoBanca "0..*" -up- "1" MembroDaBanca
MembroDaBanca <|-up- Professor

' Orientação

class Reuniao {
  DateTime dataEHora
  String local
}

Aluno "0..*" -left- "1" Orientador
Professor -|> Orientador
Reuniao "0..*" -down- "1" Orientador
Reuniao "0..*" -left- "1" Aluno

' Publicação

class Publicacao {
  Bool pertenceAoPrograma
}

Aluno -up-|> Autor
Professor -up-|> Autor
Publicacao "*" -up- "*" Autor : coautor
Aluno "1" -left- "0..*" Publicacao : autor

@enduml
```
