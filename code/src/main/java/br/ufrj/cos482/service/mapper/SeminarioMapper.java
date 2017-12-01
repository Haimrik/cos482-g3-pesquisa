package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.SeminarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Seminario and its DTO SeminarioDTO.
 */
@Mapper(componentModel = "spring", uses = {AlunoMapper.class, ProfessorMapper.class, })
public interface SeminarioMapper extends EntityMapper <SeminarioDTO, Seminario> {

    @Mapping(source = "organizadorAluno.id", target = "organizadorAlunoId")
    @Mapping(source = "organizadorAluno.nome", target = "organizadorAlunoNome")

    @Mapping(source = "organizadorProfessor.id", target = "organizadorProfessorId")
    @Mapping(source = "organizadorProfessor.nome", target = "organizadorProfessorNome")
    SeminarioDTO toDto(Seminario seminario); 

    @Mapping(source = "organizadorAlunoId", target = "organizadorAluno")

    @Mapping(source = "organizadorProfessorId", target = "organizadorProfessor")
    Seminario toEntity(SeminarioDTO seminarioDTO); 
    default Seminario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Seminario seminario = new Seminario();
        seminario.setId(id);
        return seminario;
    }
}
