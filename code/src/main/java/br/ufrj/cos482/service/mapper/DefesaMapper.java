package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.DefesaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Defesa and its DTO DefesaDTO.
 */
@Mapper(componentModel = "spring", uses = {AlunoMapper.class, })
public interface DefesaMapper extends EntityMapper <DefesaDTO, Defesa> {

    @Mapping(source = "aluno.id", target = "alunoId")
    DefesaDTO toDto(Defesa defesa); 
    @Mapping(target = "participacaoBancas", ignore = true)

    @Mapping(source = "alunoId", target = "aluno")
    Defesa toEntity(DefesaDTO defesaDTO); 
    default Defesa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Defesa defesa = new Defesa();
        defesa.setId(id);
        return defesa;
    }
}
