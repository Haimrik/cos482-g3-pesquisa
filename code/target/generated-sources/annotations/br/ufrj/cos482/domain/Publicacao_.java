package br.ufrj.cos482.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Publicacao.class)
public abstract class Publicacao_ {

	public static volatile SingularAttribute<Publicacao, Aluno> aluno;
	public static volatile SingularAttribute<Publicacao, Boolean> pertenceAoPrograma;
	public static volatile SetAttribute<Publicacao, Usuario> coautors;
	public static volatile SingularAttribute<Publicacao, Long> id;
	public static volatile SingularAttribute<Publicacao, String> url;

}

