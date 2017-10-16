package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.service.ReuniaoService;
import br.ufrj.cos482.domain.Reuniao;
import br.ufrj.cos482.repository.ReuniaoRepository;
import br.ufrj.cos482.service.dto.ReuniaoDTO;
import br.ufrj.cos482.service.mapper.ReuniaoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Reuniao.
 */
@Service
@Transactional
public class ReuniaoServiceImpl implements ReuniaoService{

    private final Logger log = LoggerFactory.getLogger(ReuniaoServiceImpl.class);

    private final ReuniaoRepository reuniaoRepository;

    private final ReuniaoMapper reuniaoMapper;

    public ReuniaoServiceImpl(ReuniaoRepository reuniaoRepository, ReuniaoMapper reuniaoMapper) {
        this.reuniaoRepository = reuniaoRepository;
        this.reuniaoMapper = reuniaoMapper;
    }

    /**
     * Save a reuniao.
     *
     * @param reuniaoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReuniaoDTO save(ReuniaoDTO reuniaoDTO) {
        log.debug("Request to save Reuniao : {}", reuniaoDTO);
        Reuniao reuniao = reuniaoMapper.toEntity(reuniaoDTO);
        reuniao = reuniaoRepository.save(reuniao);
        return reuniaoMapper.toDto(reuniao);
    }

    /**
     *  Get all the reuniaos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReuniaoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Reuniaos");
        return reuniaoRepository.findAll(pageable)
            .map(reuniaoMapper::toDto);
    }

    /**
     *  Get one reuniao by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ReuniaoDTO findOne(Long id) {
        log.debug("Request to get Reuniao : {}", id);
        Reuniao reuniao = reuniaoRepository.findOne(id);
        return reuniaoMapper.toDto(reuniao);
    }

    /**
     *  Delete the  reuniao by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reuniao : {}", id);
        reuniaoRepository.delete(id);
    }
}
