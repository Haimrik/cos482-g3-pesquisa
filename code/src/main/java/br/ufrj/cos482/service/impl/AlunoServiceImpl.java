package br.ufrj.cos482.service.impl;

import br.ufrj.cos482.domain.User;
import br.ufrj.cos482.service.AlunoService;
import br.ufrj.cos482.domain.Aluno;
import br.ufrj.cos482.repository.AlunoRepository;
import br.ufrj.cos482.service.UserService;
import br.ufrj.cos482.service.dto.AlunoDTO;
import br.ufrj.cos482.service.mapper.AlunoMapper;
import br.ufrj.cos482.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;


/**
 * Service Implementation for managing Aluno.
 */
@Service
@Transactional
public class AlunoServiceImpl implements AlunoService{

    private final Logger log = LoggerFactory.getLogger(AlunoServiceImpl.class);

    private final AlunoRepository alunoRepository;

    private final AlunoMapper alunoMapper;
    private final UserService userService;

    public AlunoServiceImpl(AlunoRepository alunoRepository, AlunoMapper alunoMapper, UserService userService) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
        this.userService = userService;
    }

    /**
     * Save a aluno.
     *
     * @param alunoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AlunoDTO save(AlunoDTO alunoDTO) {
        log.debug("Request to save Aluno : {}", alunoDTO);
        Aluno aluno = alunoMapper.toEntity(alunoDTO);
        aluno = alunoRepository.save(aluno);
        HashSet<String> authorities  = new HashSet<>();
        authorities.add("ROLE_USER");
        ManagedUserVM managedUserVM = new ManagedUserVM(null, aluno.getDre(), null, aluno.getNome(),
            null, null, false, null, "pt-br", null, null,
            null, null, authorities);
        User newUser = userService.createUser(managedUserVM);
        return alunoMapper.toDto(aluno);
    }

    /**
     *  Get all the alunos.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AlunoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Alunos");
        return alunoRepository.findAll(pageable)
            .map(alunoMapper::toDto);
    }

    /**
     *  Get one aluno by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public AlunoDTO findOne(Long id) {
        log.debug("Request to get Aluno : {}", id);
        Aluno aluno = alunoRepository.findOneWithEagerRelationships(id);
        return alunoMapper.toDto(aluno);
    }

    /**
     *  Delete the  aluno by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Aluno : {}", id);
        alunoRepository.delete(id);
    }
}
