package br.ufrj.cos482.repository;

import br.ufrj.cos482.domain.Aluno;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Aluno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("select distinct aluno from Aluno aluno left join fetch aluno.copublicacaos")
    List<Aluno> findAllWithEagerRelationships();

    @Query("select aluno from Aluno aluno left join fetch aluno.copublicacaos where aluno.id =:id")
    Aluno findOneWithEagerRelationships(@Param("id") Long id);

}
