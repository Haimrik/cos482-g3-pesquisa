package br.ufrj.cos482.service;

import br.ufrj.cos482.service.dto.DefesaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Defesa.
 */
public interface DefesaService {

    /**
     * Save a defesa.
     *
     * @param defesaDTO the entity to save
     * @return the persisted entity
     */
    DefesaDTO save(DefesaDTO defesaDTO);

    /**
     *  Get all the defesas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DefesaDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" defesa.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DefesaDTO findOne(Long id);

    /**
     *  Delete the "id" defesa.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
