package br.ufrj.cos482.domain;

import br.ufrj.cos482.domain.enumeration.TipoDefesa;
import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Defesa.class)
public abstract class Defesa_ {

	public static volatile SingularAttribute<Defesa, Aluno> aluno;
	public static volatile SetAttribute<Defesa, ParticipacaoBanca> participacaoBancas;
	public static volatile SingularAttribute<Defesa, ZonedDateTime> dataEHora;
	public static volatile SingularAttribute<Defesa, String> arquivoTexto;
	public static volatile SingularAttribute<Defesa, TipoDefesa> tipoDefesa;
	public static volatile SingularAttribute<Defesa, Long> id;
	public static volatile SingularAttribute<Defesa, String> local;

}

