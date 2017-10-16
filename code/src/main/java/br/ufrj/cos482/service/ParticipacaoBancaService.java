package br.ufrj.cos482.service;

import br.ufrj.cos482.service.dto.ParticipacaoBancaDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ParticipacaoBanca.
 */
public interface ParticipacaoBancaService {

    /**
     * Save a participacaoBanca.
     *
     * @param participacaoBancaDTO the entity to save
     * @return the persisted entity
     */
    ParticipacaoBancaDTO save(ParticipacaoBancaDTO participacaoBancaDTO);

    /**
     *  Get all the participacaoBancas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ParticipacaoBancaDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" participacaoBanca.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ParticipacaoBancaDTO findOne(Long id);

    /**
     *  Delete the "id" participacaoBanca.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
