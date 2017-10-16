package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.SeminarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Seminario and its DTO SeminarioDTO.
 */
@Mapper(componentModel = "spring", uses = {UsuarioMapper.class, })
public interface SeminarioMapper extends EntityMapper <SeminarioDTO, Seminario> {

    @Mapping(source = "organizador.id", target = "organizadorId")
    SeminarioDTO toDto(Seminario seminario); 

    @Mapping(source = "organizadorId", target = "organizador")
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
