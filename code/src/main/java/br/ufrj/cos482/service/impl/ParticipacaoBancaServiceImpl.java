package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.service.ParticipacaoBancaService;
import br.ufrj.cos482.domain.ParticipacaoBanca;
import br.ufrj.cos482.repository.ParticipacaoBancaRepository;
import br.ufrj.cos482.service.dto.ParticipacaoBancaDTO;
import br.ufrj.cos482.service.mapper.ParticipacaoBancaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ParticipacaoBanca.
 */
@Service
@Transactional
public class ParticipacaoBancaServiceImpl implements ParticipacaoBancaService{

    private final Logger log = LoggerFactory.getLogger(ParticipacaoBancaServiceImpl.class);

    private final ParticipacaoBancaRepository participacaoBancaRepository;

    private final ParticipacaoBancaMapper participacaoBancaMapper;

    public ParticipacaoBancaServiceImpl(ParticipacaoBancaRepository participacaoBancaRepository, ParticipacaoBancaMapper participacaoBancaMapper) {
        this.participacaoBancaRepository = participacaoBancaRepository;
        this.participacaoBancaMapper = participacaoBancaMapper;
    }

    /**
     * Save a participacaoBanca.
     *
     * @param participacaoBancaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ParticipacaoBancaDTO save(ParticipacaoBancaDTO participacaoBancaDTO) {
        log.debug("Request to save ParticipacaoBanca : {}", participacaoBancaDTO);
        ParticipacaoBanca participacaoBanca = participacaoBancaMapper.toEntity(participacaoBancaDTO);
        participacaoBanca = participacaoBancaRepository.save(participacaoBanca);
        return participacaoBancaMapper.toDto(participacaoBanca);
    }

    /**
     *  Get all the participacaoBancas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ParticipacaoBancaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParticipacaoBancas");
        return participacaoBancaRepository.findAll(pageable)
            .map(participacaoBancaMapper::toDto);
    }

    /**
     *  Get one participacaoBanca by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ParticipacaoBancaDTO findOne(Long id) {
        log.debug("Request to get ParticipacaoBanca : {}", id);
        ParticipacaoBanca participacaoBanca = participacaoBancaRepository.findOne(id);
        return participacaoBancaMapper.toDto(participacaoBanca);
    }

    /**
     *  Delete the  participacaoBanca by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ParticipacaoBanca : {}", id);
        participacaoBancaRepository.delete(id);
    }
}
