package br.ufrj.cos482.repository;

import br.ufrj.cos482.domain.Seminario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Seminario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SeminarioRepository extends JpaRepository<Seminario, Long> {

}
