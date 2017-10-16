package br.ufrj.cos482.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reuniao.class)
public abstract class Reuniao_ {

	public static volatile SingularAttribute<Reuniao, Professor> professor;
	public static volatile SingularAttribute<Reuniao, Aluno> aluno;
	public static volatile SingularAttribute<Reuniao, ZonedDateTime> dataEHora;
	public static volatile SingularAttribute<Reuniao, Long> id;
	public static volatile SingularAttribute<Reuniao, String> local;

}

