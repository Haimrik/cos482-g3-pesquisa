package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.service.SeminarioService;
import br.ufrj.cos482.domain.Seminario;
import br.ufrj.cos482.repository.SeminarioRepository;
import br.ufrj.cos482.service.dto.SeminarioDTO;
import br.ufrj.cos482.service.mapper.SeminarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Seminario.
 */
@Service
@Transactional
public class SeminarioServiceImpl implements SeminarioService{

    private final Logger log = LoggerFactory.getLogger(SeminarioServiceImpl.class);

    private final SeminarioRepository seminarioRepository;

    private final SeminarioMapper seminarioMapper;

    public SeminarioServiceImpl(SeminarioRepository seminarioRepository, SeminarioMapper seminarioMapper) {
        this.seminarioRepository = seminarioRepository;
        this.seminarioMapper = seminarioMapper;
    }

    /**
     * Save a seminario.
     *
     * @param seminarioDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SeminarioDTO save(SeminarioDTO seminarioDTO) {
        log.debug("Request to save Seminario : {}", seminarioDTO);
        Seminario seminario = seminarioMapper.toEntity(seminarioDTO);
        seminario = seminarioRepository.save(seminario);
        return seminarioMapper.toDto(seminario);
    }

    /**
     *  Get all the seminarios.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SeminarioDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Seminarios");
        return seminarioRepository.findAll(pageable)
            .map(seminarioMapper::toDto);
    }

    /**
     *  Get one seminario by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SeminarioDTO findOne(Long id) {
        log.debug("Request to get Seminario : {}", id);
        Seminario seminario = seminarioRepository.findOne(id);
        return seminarioMapper.toDto(seminario);
    }

    /**
     *  Delete the  seminario by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Seminario : {}", id);
        seminarioRepository.delete(id);
    }
}
