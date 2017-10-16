package br.ufrj.cos482.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SetAttribute<Usuario, Seminario> seminarios;
	public static volatile SingularAttribute<Usuario, Aluno> aluno;
	public static volatile SingularAttribute<Usuario, Professor> professor;
	public static volatile SetAttribute<Usuario, Publicacao> publicacaos;
	public static volatile SingularAttribute<Usuario, Long> id;

}

