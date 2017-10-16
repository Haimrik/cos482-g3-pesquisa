package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Aluno;

import br.ufrj.cos482.domain.Professor;

import br.ufrj.cos482.domain.Usuario;

import br.ufrj.cos482.service.dto.AlunoDTO;

import br.ufrj.cos482.service.dto.ProfessorDTO;

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

public class ProfessorMapperImpl implements ProfessorMapper {

    @Autowired

    private UsuarioMapper usuarioMapper;

    @Autowired

    private AlunoMapper alunoMapper;

    @Override

    public List<Professor> toEntity(List<ProfessorDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Professor> list = new ArrayList<Professor>();

        for ( ProfessorDTO professorDTO : dtoList ) {

            list.add( toEntity( professorDTO ) );
        }

        return list;
    }

    @Override

    public List<ProfessorDTO> toDto(List<Professor> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<ProfessorDTO> list = new ArrayList<ProfessorDTO>();

        for ( Professor professor : entityList ) {

            list.add( toDto( professor ) );
        }

        return list;
    }

    @Override

    public ProfessorDTO toDto(Professor professor) {

        if ( professor == null ) {

            return null;
        }

        ProfessorDTO professorDTO_ = new ProfessorDTO();

        professorDTO_.setUsuarioId( professorUsuarioId( professor ) );

        professorDTO_.setId( professor.getId() );

        professorDTO_.setNome( professor.getNome() );

        professorDTO_.setMatricula( professor.getMatricula() );

        professorDTO_.setLinkLattes( professor.getLinkLattes() );

        professorDTO_.setPrograma( professor.getPrograma() );

        professorDTO_.setLinhaDePesquisa( professor.getLinhaDePesquisa() );

        professorDTO_.setAreasDeInteresse( professor.getAreasDeInteresse() );

        Set<AlunoDTO> set = alunoSetToAlunoDTOSet( professor.getCoorientadors() );

        if ( set != null ) {

            professorDTO_.setCoorientadors( set );
        }

        return professorDTO_;
    }

    @Override

    public Professor toEntity(ProfessorDTO professorDTO) {

        if ( professorDTO == null ) {

            return null;
        }

        Professor professor_ = new Professor();

        professor_.setUsuario( usuarioMapper.fromId( professorDTO.getUsuarioId() ) );

        professor_.setId( professorDTO.getId() );

        professor_.setNome( professorDTO.getNome() );

        professor_.setMatricula( professorDTO.getMatricula() );

        professor_.setLinkLattes( professorDTO.getLinkLattes() );

        professor_.setPrograma( professorDTO.getPrograma() );

        professor_.setLinhaDePesquisa( professorDTO.getLinhaDePesquisa() );

        professor_.setAreasDeInteresse( professorDTO.getAreasDeInteresse() );

        Set<Aluno> set = alunoDTOSetToAlunoSet( professorDTO.getCoorientadors() );

        if ( set != null ) {

            professor_.setCoorientadors( set );
        }

        return professor_;
    }

    private Long professorUsuarioId(Professor professor) {

        if ( professor == null ) {

            return null;
        }

        Usuario usuario = professor.getUsuario();

        if ( usuario == null ) {

            return null;
        }

        Long id = usuario.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    protected Set<AlunoDTO> alunoSetToAlunoDTOSet(Set<Aluno> set) {

        if ( set == null ) {

            return null;
        }

        Set<AlunoDTO> set_ = new HashSet<AlunoDTO>();

        for ( Aluno aluno : set ) {

            set_.add( alunoMapper.toDto( aluno ) );
        }

        return set_;
    }

    protected Set<Aluno> alunoDTOSetToAlunoSet(Set<AlunoDTO> set) {

        if ( set == null ) {

            return null;
        }

        Set<Aluno> set_ = new HashSet<Aluno>();

        for ( AlunoDTO alunoDTO : set ) {

            set_.add( alunoMapper.toEntity( alunoDTO ) );
        }

        return set_;
    }
}

