package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.service.DefesaService;
import br.ufrj.cos482.domain.Defesa;
import br.ufrj.cos482.repository.DefesaRepository;
import br.ufrj.cos482.service.dto.DefesaDTO;
import br.ufrj.cos482.service.mapper.DefesaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Defesa.
 */
@Service
@Transactional
public class DefesaServiceImpl implements DefesaService{

    private final Logger log = LoggerFactory.getLogger(DefesaServiceImpl.class);

    private final DefesaRepository defesaRepository;

    private final DefesaMapper defesaMapper;

    public DefesaServiceImpl(DefesaRepository defesaRepository, DefesaMapper defesaMapper) {
        this.defesaRepository = defesaRepository;
        this.defesaMapper = defesaMapper;
    }

    /**
     * Save a defesa.
     *
     * @param defesaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DefesaDTO save(DefesaDTO defesaDTO) {
        log.debug("Request to save Defesa : {}", defesaDTO);
        Defesa defesa = defesaMapper.toEntity(defesaDTO);
        defesa = defesaRepository.save(defesa);
        return defesaMapper.toDto(defesa);
    }

    /**
     *  Get all the defesas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DefesaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Defesas");
        return defesaRepository.findAll(pageable)
            .map(defesaMapper::toDto);
    }

    /**
     *  Get one defesa by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DefesaDTO findOne(Long id) {
        log.debug("Request to get Defesa : {}", id);
        Defesa defesa = defesaRepository.findOne(id);
        return defesaMapper.toDto(defesa);
    }

    /**
     *  Delete the  defesa by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Defesa : {}", id);
        defesaRepository.delete(id);
    }
}
