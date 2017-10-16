package br.ufrj.cos482.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Seminario.class)
public abstract class Seminario_ {

	public static volatile SingularAttribute<Seminario, ZonedDateTime> dataEHora;
	public static volatile SingularAttribute<Seminario, String> titulo;
	public static volatile SingularAttribute<Seminario, Long> id;
	public static volatile SingularAttribute<Seminario, String> local;
	public static volatile SingularAttribute<Seminario, Usuario> organizador;

}

