package br.ufrj.cos482.repository;

import br.ufrj.cos482.domain.ParticipacaoBanca;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ParticipacaoBanca entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ParticipacaoBancaRepository extends JpaRepository<ParticipacaoBanca, Long> {

}
