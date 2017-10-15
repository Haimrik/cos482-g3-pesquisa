# Diagrama de Classes (ou modelo JDL)

```plantuml format="png" classes="uml myDiagram" alt="My super diagram placeholder" title="My super diagram"
@startuml
' Cadastro
package Cadastro {
  class Aluno {
    Int id
    String nome
    String dre
    Date dataDeEntrada

    escolherOrientador(Professor novoOrientador)
  }

  class Professor {
    Int id
    String nome
    String linkLattes
    String programa
    String linhaDePesquisa
    List<String> areasDeInteresse
  }

  Aluno "0..*" - "1" Professor : orientador
}


' Seminario
package Seminario {
  class Seminario {
    Int id
    String titulo
    DateTime dataEHora
    String local
  }
  Seminario "0..*" - "1" OrganizadorDeSeminario : organizador
  OrganizadorDeSeminario <|-down- Aluno
  OrganizadorDeSeminario <|-down- Professor
}

' Orientação

package Reuniao {

  class Reuniao {
    DateTime dataEHora
    String local
  }

  Reuniao "0..*" -up- "1" Professor
  Reuniao "0..*" -up- "1" Aluno
}


' Defesa
package Defesa {
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

  Defesa "0..*" -up- "1" Aluno
  Defesa "1" - "1..*" ParticipacaoBanca
  Defesa -down- TipoDefesa
  ParticipacaoBanca -down- EstadoAprovacaoDefesa
  ParticipacaoBanca "0..*" -up- "1" Professor
}

' Publicação

package Publicacao {
  class Publicacao {
    Bool pertenceAoPrograma
  }

  Aluno -up-|> Autor
  Professor -up-|> Autor
  Publicacao "*" - "*" Autor : coautor
  Aluno "1" -up- "0..*" Publicacao : autor
}

@enduml
```
