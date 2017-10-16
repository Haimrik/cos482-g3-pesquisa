package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Publicacao;

import br.ufrj.cos482.domain.Usuario;

import br.ufrj.cos482.service.dto.PublicacaoDTO;

import br.ufrj.cos482.service.dto.UsuarioDTO;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.List;

import java.util.Set;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-10-16T10:17:15-0200",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"

)

@Component

public class UsuarioMapperImpl implements UsuarioMapper {

    @Autowired

    private PublicacaoMapper publicacaoMapper;

    @Override

    public UsuarioDTO toDto(Usuario entity) {

        if ( entity == null ) {

            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( entity.getId() );

        Set<PublicacaoDTO> set = publicacaoSetToPublicacaoDTOSet( entity.getPublicacaos() );

        if ( set != null ) {

            usuarioDTO.setPublicacaos( set );
        }

        return usuarioDTO;
    }

    @Override

    public List<Usuario> toEntity(List<UsuarioDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>();

        for ( UsuarioDTO usuarioDTO : dtoList ) {

            list.add( toEntity( usuarioDTO ) );
        }

        return list;
    }

    @Override

    public List<UsuarioDTO> toDto(List<Usuario> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>();

        for ( Usuario usuario : entityList ) {

            list.add( toDto( usuario ) );
        }

        return list;
    }

    @Override

    public Usuario toEntity(UsuarioDTO usuarioDTO) {

        if ( usuarioDTO == null ) {

            return null;
        }

        Usuario usuario_ = new Usuario();

        usuario_.setId( usuarioDTO.getId() );

        Set<Publicacao> set = publicacaoDTOSetToPublicacaoSet( usuarioDTO.getPublicacaos() );

        if ( set != null ) {

            usuario_.setPublicacaos( set );
        }

        return usuario_;
    }

    protected Set<PublicacaoDTO> publicacaoSetToPublicacaoDTOSet(Set<Publicacao> set) {

        if ( set == null ) {

            return null;
        }

        Set<PublicacaoDTO> set_ = new HashSet<PublicacaoDTO>();

        for ( Publicacao publicacao : set ) {

            set_.add( publicacaoMapper.toDto( publicacao ) );
        }

        return set_;
    }

    protected Set<Publicacao> publicacaoDTOSetToPublicacaoSet(Set<PublicacaoDTO> set) {

        if ( set == null ) {

            return null;
        }

        Set<Publicacao> set_ = new HashSet<Publicacao>();

        for ( PublicacaoDTO publicacaoDTO : set ) {

            set_.add( publicacaoMapper.toEntity( publicacaoDTO ) );
        }

        return set_;
    }
}

