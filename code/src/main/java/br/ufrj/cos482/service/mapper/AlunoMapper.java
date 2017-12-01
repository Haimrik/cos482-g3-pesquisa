package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.AlunoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Aluno and its DTO AlunoDTO.
 */
@Mapper(componentModel = "spring", uses = {PublicacaoMapper.class, ProfessorMapper.class, })
public interface AlunoMapper extends EntityMapper <AlunoDTO, Aluno> {

    @Mapping(source = "orientador.id", target = "orientadorId")
    @Mapping(source = "orientador.nome", target = "orientadorNome")
    AlunoDTO toDto(Aluno aluno); 
    @Mapping(target = "publicacaos", ignore = true)
    @Mapping(target = "seminarios", ignore = true)
    @Mapping(target = "defesas", ignore = true)
    @Mapping(target = "reuniaos", ignore = true)

    @Mapping(source = "orientadorId", target = "orientador")
    @Mapping(target = "coorientadors", ignore = true)
    Aluno toEntity(AlunoDTO alunoDTO); 
    default Aluno fromId(Long id) {
        if (id == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        aluno.setId(id);
        return aluno;
    }
}
