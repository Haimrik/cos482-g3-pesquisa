package br.ufrj.cos482.repository;

import br.ufrj.cos482.domain.Reuniao;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Reuniao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReuniaoRepository extends JpaRepository<Reuniao, Long> {

}
