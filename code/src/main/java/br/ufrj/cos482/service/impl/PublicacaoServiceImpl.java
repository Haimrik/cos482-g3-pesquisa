package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.service.PublicacaoService;
import br.ufrj.cos482.domain.Publicacao;
import br.ufrj.cos482.repository.PublicacaoRepository;
import br.ufrj.cos482.service.dto.PublicacaoDTO;
import br.ufrj.cos482.service.mapper.PublicacaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Publicacao.
 */
@Service
@Transactional
public class PublicacaoServiceImpl implements PublicacaoService{

    private final Logger log = LoggerFactory.getLogger(PublicacaoServiceImpl.class);

    private final PublicacaoRepository publicacaoRepository;

    private final PublicacaoMapper publicacaoMapper;

    public PublicacaoServiceImpl(PublicacaoRepository publicacaoRepository, PublicacaoMapper publicacaoMapper) {
        this.publicacaoRepository = publicacaoRepository;
        this.publicacaoMapper = publicacaoMapper;
    }

    /**
     * Save a publicacao.
     *
     * @param publicacaoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PublicacaoDTO save(PublicacaoDTO publicacaoDTO) {
        log.debug("Request to save Publicacao : {}", publicacaoDTO);
        Publicacao publicacao = publicacaoMapper.toEntity(publicacaoDTO);
        publicacao = publicacaoRepository.save(publicacao);
        return publicacaoMapper.toDto(publicacao);
    }

    /**
     *  Get all the publicacaos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PublicacaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Publicacaos");
        return publicacaoRepository.findAll(pageable)
            .map(publicacaoMapper::toDto);
    }

    /**
     *  Get one publicacao by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PublicacaoDTO findOne(Long id) {
        log.debug("Request to get Publicacao : {}", id);
        Publicacao publicacao = publicacaoRepository.findOne(id);
        return publicacaoMapper.toDto(publicacao);
    }

    /**
     *  Delete the  publicacao by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Publicacao : {}", id);
        publicacaoRepository.delete(id);
    }
}
