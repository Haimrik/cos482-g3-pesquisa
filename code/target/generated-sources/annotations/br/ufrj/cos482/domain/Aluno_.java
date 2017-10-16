package br.ufrj.cos482.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aluno.class)
public abstract class Aluno_ {

	public static volatile SetAttribute<Aluno, Defesa> defesas;
	public static volatile SingularAttribute<Aluno, String> dre;
	public static volatile SetAttribute<Aluno, Professor> alunos;
	public static volatile SetAttribute<Aluno, Publicacao> publicacaos;
	public static volatile SetAttribute<Aluno, Reuniao> reuniaos;
	public static volatile SingularAttribute<Aluno, String> nome;
	public static volatile SingularAttribute<Aluno, Usuario> usuario;
	public static volatile SingularAttribute<Aluno, Long> id;
	public static volatile SingularAttribute<Aluno, Professor> orientador;
	public static volatile SingularAttribute<Aluno, ZonedDateTime> dataDeEntrada;

}

