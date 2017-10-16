package br.ufrj.cos482.service;

import br.ufrj.cos482.service.dto.PublicacaoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Publicacao.
 */
public interface PublicacaoService {

    /**
     * Save a publicacao.
     *
     * @param publicacaoDTO the entity to save
     * @return the persisted entity
     */
    PublicacaoDTO save(PublicacaoDTO publicacaoDTO);

    /**
     *  Get all the publicacaos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<PublicacaoDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" publicacao.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PublicacaoDTO findOne(Long id);

    /**
     *  Delete the "id" publicacao.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
