# Diagrama de Classes (ou modelo JDL)

```plantuml format="png" classes="uml myDiagram" alt="My super diagram placeholder" title="My super diagram"
@startuml
class Professor {
  Int id
  String nome
  String linkLattes
  String programa
  String linhaDePesquisa
  List<String> areasDeInteresse
}

class PublicacaoProfessores {
  Int id
  String link
}

Professor "1" -- "*" PublicacaoProfessores : autor
Professor "*" -- "*" PublicacaoAlunos : coautor

class Coorientador {
  Bool aprovarProposta()
  Bool aprovarTese()
  Bool confirmarParticipacaoBanca()
}

Coorientador --|> Professor

class Orientador {
  Bool aprovarProposta()
  Bool aprovarTese()
  Bool confirmarOrientacaoAluno()
}
Orientador --|> Professor

class Aluno {
  Int id
  String nome
  String dre
  Date dataDeEntrada
  
  agendarSeminario()
  realizarPublicacao()
  agendarDefesa()
  escreverProposta()
  escreverTese()
}

Aluno "*" -- "1" Orientador : orientador
Aluno "*" -- "*" Coorientador : coorientador

class PublicacaoAlunos {
  Int id
  String link
}

Aluno "1" -- "*" PublicacaoAlunos : aluno
Orientador "1" -- "*" PublicacaoAlunos : orientador
@enduml
```
