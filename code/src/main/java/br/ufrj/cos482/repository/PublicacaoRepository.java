package br.ufrj.cos482.repository;

import br.ufrj.cos482.domain.Publicacao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Publicacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PublicacaoRepository extends JpaRepository<Publicacao, Long> {

}
