package br.ufrj.cos482.service;

import br.ufrj.cos482.service.dto.ReuniaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Reuniao.
 */
public interface ReuniaoService {

    /**
     * Save a reuniao.
     *
     * @param reuniaoDTO the entity to save
     * @return the persisted entity
     */
    ReuniaoDTO save(ReuniaoDTO reuniaoDTO);

    /**
     *  Get all the reuniaos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ReuniaoDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" reuniao.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ReuniaoDTO findOne(Long id);

    /**
     *  Delete the "id" reuniao.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
