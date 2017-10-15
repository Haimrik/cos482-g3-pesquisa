# Documento de Casos de Uso

**Caso de uso - Ator**

**1- Cadastrar Aluno - Secretaria**

**2- Editar Cadastro Aluno - Secretaria**

**3- Visualizar cadastro aluno - Secretaria**

**4- Deletar Aluno - Secretaria**

**5- Cadastrar Professor - Secretaria**

**6- Editar Cadastro Professor - Secretaria**

**7- Visualizar cadastro Professor - Secretaria**

**8- Deletar Professor - Secretaria**

**9- Cadastrar Professor Orientador - Aluno**

**10- Cadastrar Publicação - Aluno**

**11- Visualizar Publicação - Aluno**

**12 - Deletar Publicação - Aluno**

**13 - Cadastrar Seminário**

**14 - Editar cadastro de Seminário**

**15 - Visualizar Seminário**

**16 - Deletar Seminário**

---------------- FIM ESCOPO 1ª ITERAÇÃO --------------------------

17 - Agendar Reunião com Orientador - Aluno

14- Cadastrar Tese - Aluno

15- Cadastrar Proposta - Aluno

16- Agendar Seminário - Aluno

17- Enviar Tese - Aluno

18- Confirmar participação como Orientação - Orientador

19- Aprovar Proposta - Orientador

20- Visualizar Tese - Orientador

21- Aprovar Tese - Orientador

22- Cadastrar Banca - Orientador

23 - Visualizar Banca - Orientador

24 - Editar Banca - Orientador

25- Confirmar Participação na banca - Professor

Cadastrar Aluno

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja cadastrar um novo aluno na plataforma</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O Administrador deverá acessar a página de Alunos.
O Administrador deverá clicar no botão Cadastrar Aluno.
O sistema exibe o formulário com as informações do aluno a serem preenchidas. As informações são:
Nome
DRE
Data de Entrada
Orientador
Coorientador
O Administrador deverá clicar no botão Salvar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Editar cadastro alunos

<table>
  <tr>
    <td>Descrição</td>
    <td>O administrador do sistema deseja que seja possível editar um cadastro de aluno previamente realizado.</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema e o cadastro em questão já realizado.</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O administrador deverá acessar a página de listagem de alunos.
O administrador deverá clicar no botão indicado para editar.
O administrador poderá editar os seguintes campos:
Nome
Orientador
Coorientador
O administrador deverá clicar em salvar para que as mudanças sejam efetuadas.</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Visualizar Cadastro do Aluno

<table>
  <tr>
    <td>Descrição</td>
    <td>O usuário deseja ver os eventos que estão sendo anunciados na plataforma</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema
O aluno deverá estar previamente cadastrado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de listagem de alunos.
O ator irá visualizar os alunos na plataforma.
O ator clica no aluno de seu interesse.
O sistema exibe as informações sobre o aluno. As informações são:
Nome
DRE
Data de Entrada
Orientador
Coordenadores</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Deletar Aluno

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja deletar um aluno</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador
Sistema</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema
O aluno deverá estar previamente cadastrado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de listagem de alunos.
O ator irá visualizar os alunos na plataforma.
O ator clica no botão Deletar ao lado do Aluno que deseja deletar
O Sistema irá exibir uma mensagem perguntando se o Administrador deseja realmente deletar esse aluno
O Administrador deverá clicar no botão Deletar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Cadastrar Professor

<table>
  <tr>
    <td>Descrição</td>
    <td>O administrador do sistema deseja que seja possível cadastrar um novo professor no sistema.</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O administrador deve estar logado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O Administrador deverá acessar a página de listagem de professores.
O Administrador deverá clicar no botão Cadastrar Professor.
O sistema exibe o formulário com as informações do professor a serem preenchidas. As informações são:
Nome
Link para o lattes
Programa
Linha de pesquisa
Áreas de interesse
O Administrador deverá clicar no botão Salvar.</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Editar cadastro professor

<table>
  <tr>
    <td>Descrição</td>
    <td>O administrador do sistema deseja que seja possível editar um cadastro de professor previamente realizado.</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema e o cadastro em questão já realizado.</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O administrador deverá acessar a página de listagem de professores.
O administrador deverá clicar no botão indicado para editar.
O administrador poderá editar os seguintes campos:
Nome
link lattes
publicações
programa\
linha de pesquisa
O administrador deverá clicar em salvar para que as mudanças sejam efetuadas.</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Visualizar Cadastro do Professor

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja ver os eventos que estão sendo anunciados na plataforma</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema
O Professor deverá estar previamente cadastrado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de listagem de professores.
O ator irá visualizar os professores cadastrados na plataforma.
O ator clica no professor de seu interesse.
O sistema exibe as informações sobre o professor. As informações são:
Nome
Link para o lattes
Programa
Linha de pesquisa
Áreas de interesse</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Deletar Professor

<table>
  <tr>
    <td>Descrição</td>
    <td>O Administrador deseja que seja possível deletar o um Professor do sistema.</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador
Sistema</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O Administrador deve estar logado no sistema
O aluno deverá estar previamente cadastrado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O Administrador deverá acessar a página de listagem de professores.
O Administrador irá visualizar os professores registrados na plataforma.
O Administrador deverá clicar no botão Deletar ao lado do Professor que deseja deletar.
O Sistema irá exibir uma mensagem perguntando se o Administrador deseja realmente deletar esse Professor.
O Administrador deverá clicar no botão Deletar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>



Cadastrar Professor Orientador

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja cadastrar um professor como o seu orientador</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Aluno</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página do seu cadastro.
O ator deverá clicar no botão de edição de dados do seu cadastro.
O ator deverá escolher um professor da lista de professores disponibilizada pelo sistema
O ator deverá clicar no botão Salvar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  O professor escolhido será registrado como orientador do ator</td>
  </tr>
</table>


Cadastrar Publicação

<table>
  <tr>
    <td>Descrição</td>
    <td>O aluno deseja uma nova publicação sua no sistema.</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Aluno
Sistema</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O aluno deve estar logado no sistema.</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O aluno deverá acessar a página de publicações.
O aluno irá visualizar todas as publicações cadastradas no sistemas.
O aluno deverá clicar em Cadastrar Publicação
O sistema exibe o formulário com as informações da publicação a serem preenchidas. As informações são:
Link
Coautores
O aluno deverá clicar no botão Salvar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Visualizar Cadastro da Publicação

<table>
  <tr>
    <td>Descrição</td>
    <td>O usuário deseja ver os dados de uma publicação cadastrada no sistema</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Usuário Logado</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema
A publicação deverá estar previamente cadastrada no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de listagem de publicações.
O ator irá clicar no botão de visualização dos dados cadastrados da sua publicação de interesse
O sistema exibe as informações sobre a publicação. As informações são:
Link
Coautores</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Deletar Publicação

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja deletar uma publicação realizada</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Aluno</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de listagem de publicações.
O ator deverá encontrar a publicação que deseja deletar
O ator deverá clicar no botão Deletar ao lado da publicação que deseja deletar.
O Sistema irá exibir uma mensagem perguntando se o Aluno deseja realmente deletar essa publicação.
O Aluno deverá clicar no botão Deletar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Cadastrar Seminário

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja cadastrar um seminário no sistema</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Aluno ou professor</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de seminários.
O Aluno deverá clicar no botão Cadastrar Seminário.
O sistema exibe o formulário para cadastramento de seminário com as seguintes informações:
Data do Seminário
Hora do Seminário
Local do seminário
Título do seminário
O ator deverá clicar no botão Salvar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  O seminário estará cadastrado no sitema</td>
  </tr>
</table>


Editar cadastro de Seminário

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja editar um seminário cadastrado</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Professor
Aluno</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema
O ator deverá ter um Seminário agendado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de Seminários
O ator deverá encontrar o Seminário que ele deseja editar
O ator deverá clicar no Botão editar.
O ator poderá editar as seguintes informações:
Data do Seminário
Hora do Seminário
Local do Seminário
Título do Seminário
O ator deverá clicar no botão salvar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Visualizar Seminário

<table>
  <tr>
    <td>Descrição</td>
    <td>O ator deseja visualizar os seminários agendados no sistema</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Professor
Aluno</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O ator deve estar logado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de Seminários
O ator sistema irá exibir uma lista com os Seminários cadastrados com as seguintes informações:
Data do Seminário
Hora do Seminário
Local do Seminário
Título do Seminário</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>


Deletar Seminário

<table>
  <tr>
    <td>Descrição</td>
    <td>O Administrador deseja que seja possível deletar o um Professor do sistema.</td>
  </tr>
  <tr>
    <td>Atores</td>
    <td>Administrador
Sistema</td>
  </tr>
  <tr>
    <td>Pré-Condições</td>
    <td>O Administrador deve estar logado no sistema
O aluno deverá estar previamente cadastrado no sistema</td>
  </tr>
  <tr>
    <td>Fluxo Principal</td>
    <td>O ator deverá acessar a página de Seminários
O ator deverá encontrar o Seminário que ele deseja deletar.
O ator deverá clicar no Botão Deletar ao lado da publicação que deseja excluir.
O Sistema irá exibir uma mensagem perguntando se o ator deseja realmente deletar esse seminário.
O ator deverá clicar no botão Deletar</td>
  </tr>
  <tr>
    <td>Fluxos Alternativos</td>
    <td></td>
  </tr>
  <tr>
    <td>Fluxo de Exceção</td>
    <td></td>
  </tr>
  <tr>
    <td>Pós-Condições</td>
    <td>  </td>
  </tr>
</table>
