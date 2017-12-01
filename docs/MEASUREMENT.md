# Plano de medição organizacional

## 1. Escopo

### 1. Contexto organizacional

A organização referida se trata do conjunto de alunos responsáveis pelo grupo G3, organizado a fim de atender o trabalho prático da disciplina Qualidade de Software do curso de Engenharia de Computação e Informação na UFRJ, no segundo período de 2017. 

O sistema proposto tem o objetivo simplificar o modo como os estudantes e os professores lidam com todo o processo de Pesquisa tanto no Mestrado quanto no Doutorado. Por ser um processo atualmente gerenciado de maneira direta e pessoal, requer que diversos papéis e documentos sejam passados entre diferentes pessoas para avaliação e validação, o que torna A solução será um sistema Web desenvolvido com a plataforma de desenvolvimento Jhipster.

A equipe, por sua vez, é constituída de alunos do curso de Engenharia de Computação e Informação, possuindo a mesma formação acadêmica, porém contando com experiências profissionais diversas.


### 2. Problema

Quanto ao projeto em si e seus destinatários, a abordagem é realizada em cima do problema de submeter a pesquisa para o mestrado e doutorado afeta diversos alunos na UFRJ e em outras instituições de ensino que são afetados pela ineficiência, burocracia e demora em acompanhar, aprovar e avaliar a pesquisa desde a sua submissão até avaliação. Uma solução eficiente seria um sistema web que permitisse um fluxo mais rápido no compartilhamento dos documentos envolvidos no processo e na comunicação e entre os envolvidos.

No que diz respeito aos problemas operacionais de desenvolvimento, a falta de tempo a ser dedicado no projeto e o desinteresse geral no domínio abordado se apresentam como principais barreiras para o desenvolvimento do produto a ser entregue, bem como se seus diversos artefatos.


### 3. Motivação

O projeto e seu desenvolvimento existem motivados pela demanda imposta pela equipe docente da disciplina.

## 2. Plano GQ(I)M

### 1. Modelo GQ(I)M

::dot:: alt="Modelo GQ(I)M" title="Modelo GQ(I)M"
  digraph gqim {
    N0 -> N10;
    N0 -> N11;
    N0 -> N12;
    N0 -> N13;
    N10 -> N20;
    N10 -> N21;
    N11 -> N22;
    N12 -> N23;
    N13 -> N24;
    N24 -> N30;
    N24 -> N31;

    N0 [label="Obter nota 10 até 10"];

    N10 [label="Executar projeto em \n conformidade com o \n processo até 12/17"];
    N11 [label="Entregar artefatos em \n conformidade com \n especificações"];
    N12 [label="Executar de 15% a 25% \n das tarefas por \n membro da equipe"];
    N13 [label="Executar tarefas de \n cada iteração no \n prazo especificado"];

    N20 [label="Caracterizar quantidade \n de tarefas sem relação \n com atividades do processo"];
    N21 [label="Caracterizar quantidade \n de tarefas sem relação \n com tarefas"];
    N22 [label="Caracterizar percentual \n de pontos de tarefas \n entregues no prazo"];
    N23 [label="Caracterizar quantidade \n de pontos realizadas por \n membro da equipe"];
    N24 [label="Avaliar qualidade do \n artefato entregue"];

    N30 [label="Caracterizar densidade \n de defeitos encontrados \n em testes de software"];
    N31 [label="Caracterizar número de \n defeitos encontrados em \n revisão de artefato"];
  }
::end-dot::

### 2. Definição operacional de indicadores e respectivas medidas

#### 1. IDDT
* Nome: Índice de densidade de defeitos em teste
* Definicão: Mede a densidade de defeitos em testes
* Objetivos: Indicar a qualidade do código produzido e sua evoclucão temporal
* Questões: Qual a densidade de defeitos em teste do produto de software?
* Hipóteses: Falhas em testes indicam qualidade do software produzido
* Entidades Mensuráveis: Testes de software
* Unidade de Medida: Testes falhos por testes totais
* Intervalo de Dados: Reais de 0 a 1
* Tipo de Escala: razão
* Valores da Escala: Números fracionários com qualquer grau de precisão
* Funcão de Medicão: IDDT = Tf/ Tt (número de testes falhos pelo número de testes totais
* Momento: Execucão da rodada de testes
* Peridiocidade: Pelo menos uma vez por iteracão
* Procedimento de medicão: Leitura do relatório automático de testes
* Ferramentas: Testes do JHipster e planilha de medicões
* Agentes: Testador, responsável pela execucão dos testes

#### 2. INDA
* Nome: Índice de Número de Defeitos por Artefato						
* Definicão: Mede a densidade de defeitos em artefatos
* Objetivos: Indicar a qualidade dos artefatos produzidos e sua evoclucão temporal
* Questões: Qual a densidade de defeitos em artefatos relacionados ao produto de software?
* Hipóteses: Falhas em artefatos indicam qualidade do software produzido
* Entidades Mensuráveis: Artefatos
* Unidade de Medida: Testes falhos por testes totais
* Intervalo de Dados: Reais de 0 a 1
* Tipo de Escala: razão
* Valores da Escala: Números fracionários com qualquer grau de precisão
* Funcão de Medicão: INDA = Tf/ Tt (número de testes falhos pelo número de testes totais
* Momento: Execucão da rodada de avaliacões
* Peridiocidade: Pelo menos uma vez por iteracão
* Procedimento de medicão: Leitura dos artefatos
* Ferramentas: Planilha de medicões
* Agentes: Testador, responsável pela avaliacão dos artefatos

#### 3. IQTNAP
* Nome: Índice de quantidade de tarefas não relacionadas a atividades do processo											
* Definicão: Mede a quantidade de tarefas não relacionadas a atividades do processo
* Objetivos: Indicar a conformidade com o processo de software
* Questões: Qual é a quantidade de tarefas sem relacionamento com o modelo?
* Hipóteses: A conformidade com o processo indica qualidade do software produzido
* Entidades Mensuráveis: Tarefas e modelo do processo
* Unidade de Medida: Número de tarefas
* Intervalo de Dados: Inteiros positivos e zero
* Tipo de Escala: ordinal
* Valores da Escala: Números inteiros
* Funcão de Medicão: IQTNAP, a contagem de ocorrências 
* Momento: Execucão da rodada de testes
* Peridiocidade: Pelo menos uma vez por iteracão
* Procedimento de medicão: Leitura das tarefas e do modelo
* Ferramentas: Planilha de medicões
* Agentes: Avaliador, responsável pela avaliacão das tarefas

#### 4. IQAPNT
* Nome: Índice de quantidade de atividades do processo não relacionadas a tarefas											
* Definicão: Mede a quantidade de atividades do processo não relacionadas a tarefas
* Objetivos: Indicar a conformidade com o processo de software
* Questões: Qual é a quantidade de atividades do processo não relacionadas a tarefas?
* Hipóteses: A conformidade com o processo indica qualidade do software produzido
* Entidades Mensuráveis: Tarefas e modelo do processo
* Unidade de Medida: Número de tarefas
* Intervalo de Dados: Inteiros positivos e zero
* Tipo de Escala: ordinal
* Valores da Escala: Números inteiros
* Funcão de Medicão: IQAPNT, a contagem de ocorrências 
* Momento: Execucão da rodada de testes
* Peridiocidade: Pelo menos uma vez por iteracão
* Procedimento de medicão: Leitura das tarefas e do modelo
* Ferramentas: Planilha de medicões
* Agentes: Avaliador, responsável pela avaliacão das tarefas

#### 5. IPPE
* Nome: Índice de percentual de pontos entregues																
* Definicão: Mede o percentual de pontos entregues
* Objetivos: Indicar a conformidade com o cronograma
* Questões: Qual é o percentual de pontos entregues?
* Hipóteses: A conformidade com o cronograma indica qualidade do software produzido
* Entidades Mensuráveis: Tarefas entregues 
* Unidade de Medida: Número de tarefas entregues
* Intervalo de Dados: Reais de 0 a 1
* Tipo de Escala: razão
* Valores da Escala: Números fracionários
* Funcão de Medicão: IPPE, número de tarefas entregues pelo número total planejado
* Momento: Avaliacão das tarefas concluídas e planejamento de sprint
* Peridiocidade: Pelo menos uma vez por iteracão
* Procedimento de medicão: Leitura das tarefas
* Ferramentas: Planilha de medicões
* Agentes: Avaliador, responsável pela avaliacão das tarefas

#### 6. QPEM
* Nome: Índice de percentual de pontos entregues por membro															
* Definicão: Mede o percentual de pontos entregues poe membro
* Objetivos: Indicar a conformidade com o cronograma e a capacidade produtiva dos membros
* Questões: Qual é o percentual de pontos entregues por membro?
* Hipóteses: A conformidade com o cronograma e com o processo indica qualidade do software produzido
* Entidades Mensuráveis: Tarefas entregues 
* Unidade de Medida: Número de tarefas entregues
* Intervalo de Dados: Reais de 0 a 1
* Tipo de Escala: razão
* Valores da Escala: Números fracionários
* Funcão de Medicão: QPEM, número de tarefas entregues por membro pelo número total planejado para o membro
* Momento: Avaliacão das tarefas concluídas e planejamento de sprint
* Peridiocidade: Pelo menos uma vez por iteracão
* Procedimento de medicão: Leitura das tarefas
* Ferramentas: Planilha de medicões
* Agentes: Avaliador, responsável pela avaliacão das tarefas


As medicões dos indicadores propostos se encontram na [planilha de medicões](https://docs.google.com/a/poli.ufrj.br/spreadsheets/d/1zJ26MskGE87uit8OgGvDgxTuLT0m9gcfpACok17qtAU/edit?usp=sharing).

## 3. Recursos

### 1. Perfil do recurso humano

Os agentes envolvidos na medição ficarão responsáveis por gerar gráficos e dados estatísticos que mostrem o quadro da qualidade do produto. Com esses artefatos, será realizada uma análise visando descobrir se há algum problema. Caso exista um, espera-se que os artefatos identifiquem facilmente a sua causa e a melhor maneira de resolvê-lo.

### 2. Infra-estrutura

* Acompanhamento e consulta das atividades no Redmine.
* Relatórios de teste e acompanhamento de código.
* Questionário de satisfação do cliente com a entrega.

## 4. Riscos

### 1. Pressupostos

O plano pressupõe a conformidade dos participantes dos processos de desenvolvimento no que diz respeito ao seguimento compromissado do modelo empregado. Na incorrência da inconformidade com o processo pelos agentes envolvidos, existirão riscos para a execução bem sucedida da medição.

### 2. Gestão de riscos

Uma maneira de identificar um quebra no pressuposto pode ser a não conformidade dos artefatos propostos com os artefatos entregues. Caso a entrega de algum artefato, planejada para uma determinada data, não ocorra, deve-se avaliar o motivo pelo qual a mesma não foi realizada. Uma justificativa da mesma deve ser analisada e autorizada, ou não, pelo cliente (stakeholder). Caso a autorização seja aprovada pelo cliente, a entrega não deve ser cancelada, apenas postergada.

### 3. Avaliação

Todas as medidas deverão ser catalogadas temporalmente de modo a tornar visível a variação das métricas no decorrer do projeto. Com a base temporal será possível a avaliação da evolução prática do processo de software empregado, bem como a observação de quais métricas refletem efetivamente a qualidade do produto de software em desenvolvimento. Tais observações permitirão o aperfeiçoamento contínuo do processo e das métricas preditivas de qualidade.
