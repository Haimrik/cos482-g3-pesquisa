package br.ufrj.cos482.repository;

import br.ufrj.cos482.domain.Professor;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Professor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("select distinct professor from Professor professor left join fetch professor.copublicacaos left join fetch professor.coorientandos")
    List<Professor> findAllWithEagerRelationships();

    @Query("select professor from Professor professor left join fetch professor.copublicacaos left join fetch professor.coorientandos where professor.id =:id")
    Professor findOneWithEagerRelationships(@Param("id") Long id);

}
