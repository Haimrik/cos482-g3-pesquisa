package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.domain.User;
import br.ufrj.cos482.service.ProfessorService;
import br.ufrj.cos482.domain.Professor;
import br.ufrj.cos482.repository.ProfessorRepository;
import br.ufrj.cos482.service.UserService;
import br.ufrj.cos482.service.dto.ProfessorDTO;
import br.ufrj.cos482.service.mapper.ProfessorMapper;
import br.ufrj.cos482.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;


/**
 * Service Implementation for managing Professor.
 */
@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService{

    private final Logger log = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    private final ProfessorRepository professorRepository;

    private final ProfessorMapper professorMapper;
    private final UserService userService;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProfessorMapper professorMapper,
                                UserService userService) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.userService = userService;
    }

    /**
     * Save a professor.
     *
     * @param professorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProfessorDTO save(ProfessorDTO professorDTO) {
        log.debug("Request to save Professor : {}", professorDTO);
        Professor professor = professorMapper.toEntity(professorDTO);
        professor = professorRepository.save(professor);
        HashSet<String> authorities  = new HashSet<>();
        authorities.add("ROLE_USER");
        ManagedUserVM managedUserVM = new ManagedUserVM(null, professorDTO.getMatricula(), null,
            professorDTO.getNome(), null, null, false, null, "pt-br",
            null, null, null, null, authorities);
        User newUser = userService.createUser(managedUserVM);
        return professorMapper.toDto(professor);
    }

    /**
     *  Get all the professors.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProfessorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Professors");
        return professorRepository.findAll(pageable)
            .map(professorMapper::toDto);
    }

    /**
     *  Get one professor by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProfessorDTO findOne(Long id) {
        log.debug("Request to get Professor : {}", id);
        Professor professor = professorRepository.findOneWithEagerRelationships(id);
        return professorMapper.toDto(professor);
    }

    /**
     *  Delete the  professor by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Professor : {}", id);
        professorRepository.delete(id);
    }
}
