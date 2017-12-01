package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.PublicacaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Publicacao and its DTO PublicacaoDTO.
 */
@Mapper(componentModel = "spring", uses = {AlunoMapper.class, })
public interface PublicacaoMapper extends EntityMapper <PublicacaoDTO, Publicacao> {

    @Mapping(source = "aluno.id", target = "alunoId")
    @Mapping(source = "aluno.nome", target = "alunoNome")
    PublicacaoDTO toDto(Publicacao publicacao); 

    @Mapping(source = "alunoId", target = "aluno")
    @Mapping(target = "coautorAlunos", ignore = true)
    @Mapping(target = "coautorProfessors", ignore = true)
    Publicacao toEntity(PublicacaoDTO publicacaoDTO); 
    default Publicacao fromId(Long id) {
        if (id == null) {
            return null;
        }
        Publicacao publicacao = new Publicacao();
        publicacao.setId(id);
        return publicacao;
    }
}
