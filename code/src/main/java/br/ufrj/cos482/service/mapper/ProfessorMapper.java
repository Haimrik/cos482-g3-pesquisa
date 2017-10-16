package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.ProfessorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Professor and its DTO ProfessorDTO.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, AlunoMapper.class, })
public interface ProfessorMapper extends EntityMapper <ProfessorDTO, Professor> {

    @Mapping(source = "usuario.id", target = "usuarioId")
    ProfessorDTO toDto(Professor professor); 

    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(target = "alunos", ignore = true)
    @Mapping(target = "participacaoBancas", ignore = true)
    @Mapping(target = "reuniaos", ignore = true)
    Professor toEntity(ProfessorDTO professorDTO); 
    default Professor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Professor professor = new Professor();
        professor.setId(id);
        return professor;
    }
}
