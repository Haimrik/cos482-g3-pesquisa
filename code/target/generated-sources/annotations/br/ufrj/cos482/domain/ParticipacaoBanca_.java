package br.ufrj.cos482.domain;

import br.ufrj.cos482.domain.enumeration.EstadoAprovacaoDefesa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParticipacaoBanca.class)
public abstract class ParticipacaoBanca_ {

	public static volatile SingularAttribute<ParticipacaoBanca, EstadoAprovacaoDefesa> estadoAprovacaoDefesa;
	public static volatile SingularAttribute<ParticipacaoBanca, Professor> professor;
	public static volatile SingularAttribute<ParticipacaoBanca, Boolean> confirmado;
	public static volatile SingularAttribute<ParticipacaoBanca, Long> id;
	public static volatile SingularAttribute<ParticipacaoBanca, Defesa> defesa;

}

