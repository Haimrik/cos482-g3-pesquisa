package br.ufrj.cos482.repository;

import br.ufrj.cos482.domain.Defesa;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Defesa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DefesaRepository extends JpaRepository<Defesa, Long> {

}
