package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.AlunoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Aluno and its DTO AlunoDTO.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, ProfessorMapper.class, })
public interface AlunoMapper extends EntityMapper <AlunoDTO, Aluno> {

    @Mapping(source = "usuario.id", target = "usuarioId")

    @Mapping(source = "orientador.id", target = "orientadorId")
    AlunoDTO toDto(Aluno aluno); 

    @Mapping(source = "usuarioId", target = "usuario")
    @Mapping(target = "publicacaos", ignore = true)
    @Mapping(target = "defesas", ignore = true)
    @Mapping(target = "reuniaos", ignore = true)

    @Mapping(source = "orientadorId", target = "orientador")
    @Mapping(target = "alunos", ignore = true)
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
