package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Aluno;

import br.ufrj.cos482.domain.Professor;

import br.ufrj.cos482.domain.Reuniao;

import br.ufrj.cos482.service.dto.ReuniaoDTO;

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

public class ReuniaoMapperImpl implements ReuniaoMapper {

    @Autowired

    private ProfessorMapper professorMapper;

    @Autowired

    private AlunoMapper alunoMapper;

    @Override

    public List<Reuniao> toEntity(List<ReuniaoDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Reuniao> list = new ArrayList<Reuniao>();

        for ( ReuniaoDTO reuniaoDTO : dtoList ) {

            list.add( toEntity( reuniaoDTO ) );
        }

        return list;
    }

    @Override

    public List<ReuniaoDTO> toDto(List<Reuniao> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<ReuniaoDTO> list = new ArrayList<ReuniaoDTO>();

        for ( Reuniao reuniao : entityList ) {

            list.add( toDto( reuniao ) );
        }

        return list;
    }

    @Override

    public ReuniaoDTO toDto(Reuniao reuniao) {

        if ( reuniao == null ) {

            return null;
        }

        ReuniaoDTO reuniaoDTO_ = new ReuniaoDTO();

        reuniaoDTO_.setAlunoId( reuniaoAlunoId( reuniao ) );

        reuniaoDTO_.setProfessorId( reuniaoProfessorId( reuniao ) );

        reuniaoDTO_.setId( reuniao.getId() );

        reuniaoDTO_.setDataEHora( reuniao.getDataEHora() );

        reuniaoDTO_.setLocal( reuniao.getLocal() );

        return reuniaoDTO_;
    }

    @Override

    public Reuniao toEntity(ReuniaoDTO reuniaoDTO) {

        if ( reuniaoDTO == null ) {

            return null;
        }

        Reuniao reuniao_ = new Reuniao();

        reuniao_.setProfessor( professorMapper.fromId( reuniaoDTO.getProfessorId() ) );

        reuniao_.setAluno( alunoMapper.fromId( reuniaoDTO.getAlunoId() ) );

        reuniao_.setId( reuniaoDTO.getId() );

        reuniao_.setDataEHora( reuniaoDTO.getDataEHora() );

        reuniao_.setLocal( reuniaoDTO.getLocal() );

        return reuniao_;
    }

    private Long reuniaoAlunoId(Reuniao reuniao) {

        if ( reuniao == null ) {

            return null;
        }

        Aluno aluno = reuniao.getAluno();

        if ( aluno == null ) {

            return null;
        }

        Long id = aluno.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long reuniaoProfessorId(Reuniao reuniao) {

        if ( reuniao == null ) {

            return null;
        }

        Professor professor = reuniao.getProfessor();

        if ( professor == null ) {

            return null;
        }

        Long id = professor.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

