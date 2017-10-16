package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Aluno;

import br.ufrj.cos482.domain.Professor;

import br.ufrj.cos482.domain.Usuario;

import br.ufrj.cos482.service.dto.AlunoDTO;

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

public class AlunoMapperImpl implements AlunoMapper {

    @Autowired

    private UsuarioMapper usuarioMapper;

    @Autowired

    private ProfessorMapper professorMapper;

    @Override

    public List<Aluno> toEntity(List<AlunoDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Aluno> list = new ArrayList<Aluno>();

        for ( AlunoDTO alunoDTO : dtoList ) {

            list.add( toEntity( alunoDTO ) );
        }

        return list;
    }

    @Override

    public List<AlunoDTO> toDto(List<Aluno> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<AlunoDTO> list = new ArrayList<AlunoDTO>();

        for ( Aluno aluno : entityList ) {

            list.add( toDto( aluno ) );
        }

        return list;
    }

    @Override

    public AlunoDTO toDto(Aluno aluno) {

        if ( aluno == null ) {

            return null;
        }

        AlunoDTO alunoDTO_ = new AlunoDTO();

        alunoDTO_.setUsuarioId( alunoUsuarioId( aluno ) );

        alunoDTO_.setOrientadorId( alunoOrientadorId( aluno ) );

        alunoDTO_.setId( aluno.getId() );

        alunoDTO_.setNome( aluno.getNome() );

        alunoDTO_.setDre( aluno.getDre() );

        alunoDTO_.setDataDeEntrada( aluno.getDataDeEntrada() );

        return alunoDTO_;
    }

    @Override

    public Aluno toEntity(AlunoDTO alunoDTO) {

        if ( alunoDTO == null ) {

            return null;
        }

        Aluno aluno_ = new Aluno();

        aluno_.setUsuario( usuarioMapper.fromId( alunoDTO.getUsuarioId() ) );

        aluno_.setOrientador( professorMapper.fromId( alunoDTO.getOrientadorId() ) );

        aluno_.setId( alunoDTO.getId() );

        aluno_.setNome( alunoDTO.getNome() );

        aluno_.setDre( alunoDTO.getDre() );

        aluno_.setDataDeEntrada( alunoDTO.getDataDeEntrada() );

        return aluno_;
    }

    private Long alunoUsuarioId(Aluno aluno) {

        if ( aluno == null ) {

            return null;
        }

        Usuario usuario = aluno.getUsuario();

        if ( usuario == null ) {

            return null;
        }

        Long id = usuario.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long alunoOrientadorId(Aluno aluno) {

        if ( aluno == null ) {

            return null;
        }

        Professor orientador = aluno.getOrientador();

        if ( orientador == null ) {

            return null;
        }

        Long id = orientador.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

