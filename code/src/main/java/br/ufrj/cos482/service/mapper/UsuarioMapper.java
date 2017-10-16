package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.UsuarioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Usuario and its DTO UsuarioDTO.
 */
@Mapper(componentModel = "spring", uses = {PublicacaoMapper.class, })
public interface UsuarioMapper extends EntityMapper <UsuarioDTO, Usuario> {
    
    @Mapping(target = "seminarios", ignore = true)
    @Mapping(target = "aluno", ignore = true)
    @Mapping(target = "professor", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO); 
    default Usuario fromId(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
