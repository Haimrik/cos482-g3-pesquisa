package br.ufrj.cos482.service;

import br.ufrj.cos482.service.dto.SeminarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Seminario.
 */
public interface SeminarioService {

    /**
     * Save a seminario.
     *
     * @param seminarioDTO the entity to save
     * @return the persisted entity
     */
    SeminarioDTO save(SeminarioDTO seminarioDTO);

    /**
     *  Get all the seminarios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<SeminarioDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" seminario.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SeminarioDTO findOne(Long id);

    /**
     *  Delete the "id" seminario.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
