package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Seminario;

import br.ufrj.cos482.domain.Usuario;

import br.ufrj.cos482.service.dto.SeminarioDTO;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2017-10-16T10:17:15-0200",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"

)

@Component

public class SeminarioMapperImpl implements SeminarioMapper {

    @Autowired

    private UsuarioMapper usuarioMapper;

    @Override

    public List<Seminario> toEntity(List<SeminarioDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Seminario> list = new ArrayList<Seminario>();

        for ( SeminarioDTO seminarioDTO : dtoList ) {

            list.add( toEntity( seminarioDTO ) );
        }

        return list;
    }

    @Override

    public List<SeminarioDTO> toDto(List<Seminario> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<SeminarioDTO> list = new ArrayList<SeminarioDTO>();

        for ( Seminario seminario : entityList ) {

            list.add( toDto( seminario ) );
        }

        return list;
    }

    @Override

    public SeminarioDTO toDto(Seminario seminario) {

        if ( seminario == null ) {

            return null;
        }

        SeminarioDTO seminarioDTO_ = new SeminarioDTO();

        seminarioDTO_.setOrganizadorId( seminarioOrganizadorId( seminario ) );

        seminarioDTO_.setId( seminario.getId() );

        seminarioDTO_.setTitulo( seminario.getTitulo() );

        seminarioDTO_.setDataEHora( seminario.getDataEHora() );

        seminarioDTO_.setLocal( seminario.getLocal() );

        return seminarioDTO_;
    }

    @Override

    public Seminario toEntity(SeminarioDTO seminarioDTO) {

        if ( seminarioDTO == null ) {

            return null;
        }

        Seminario seminario_ = new Seminario();

        seminario_.setOrganizador( usuarioMapper.fromId( seminarioDTO.getOrganizadorId() ) );

        seminario_.setId( seminarioDTO.getId() );

        seminario_.setTitulo( seminarioDTO.getTitulo() );

        seminario_.setDataEHora( seminarioDTO.getDataEHora() );

        seminario_.setLocal( seminarioDTO.getLocal() );

        return seminario_;
    }

    private Long seminarioOrganizadorId(Seminario seminario) {

        if ( seminario == null ) {

            return null;
        }

        Usuario organizador = seminario.getOrganizador();

        if ( organizador == null ) {

            return null;
        }

        Long id = organizador.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

