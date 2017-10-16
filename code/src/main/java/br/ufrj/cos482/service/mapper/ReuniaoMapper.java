package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.ReuniaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reuniao and its DTO ReuniaoDTO.
 */
@Mapper(componentModel = "spring", uses = {ProfessorMapper.class, AlunoMapper.class, })
public interface ReuniaoMapper extends EntityMapper <ReuniaoDTO, Reuniao> {

    @Mapping(source = "professor.id", target = "professorId")

    @Mapping(source = "aluno.id", target = "alunoId")
    ReuniaoDTO toDto(Reuniao reuniao); 

    @Mapping(source = "professorId", target = "professor")

    @Mapping(source = "alunoId", target = "aluno")
    Reuniao toEntity(ReuniaoDTO reuniaoDTO); 
    default Reuniao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reuniao reuniao = new Reuniao();
        reuniao.setId(id);
        return reuniao;
    }
}
