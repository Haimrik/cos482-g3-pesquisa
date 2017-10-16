package br.ufrj.cos482.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Professor.class)
public abstract class Professor_ {

	public static volatile SetAttribute<Professor, Aluno> coorientadors;
	public static volatile SetAttribute<Professor, ParticipacaoBanca> participacaoBancas;
	public static volatile SetAttribute<Professor, Aluno> alunos;
	public static volatile SingularAttribute<Professor, String> matricula;
	public static volatile SingularAttribute<Professor, String> programa;
	public static volatile SingularAttribute<Professor, String> linhaDePesquisa;
	public static volatile SetAttribute<Professor, Reuniao> reuniaos;
	public static volatile SingularAttribute<Professor, String> nome;
	public static volatile SingularAttribute<Professor, Usuario> usuario;
	public static volatile SingularAttribute<Professor, Long> id;
	public static volatile SingularAttribute<Professor, String> linkLattes;
	public static volatile SingularAttribute<Professor, String> areasDeInteresse;

}

