package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Defesa;

import br.ufrj.cos482.domain.ParticipacaoBanca;

import br.ufrj.cos482.domain.Professor;

import br.ufrj.cos482.service.dto.ParticipacaoBancaDTO;

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

public class ParticipacaoBancaMapperImpl implements ParticipacaoBancaMapper {

    @Autowired

    private DefesaMapper defesaMapper;

    @Autowired

    private ProfessorMapper professorMapper;

    @Override

    public List<ParticipacaoBanca> toEntity(List<ParticipacaoBancaDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<ParticipacaoBanca> list = new ArrayList<ParticipacaoBanca>();

        for ( ParticipacaoBancaDTO participacaoBancaDTO : dtoList ) {

            list.add( toEntity( participacaoBancaDTO ) );
        }

        return list;
    }

    @Override

    public List<ParticipacaoBancaDTO> toDto(List<ParticipacaoBanca> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<ParticipacaoBancaDTO> list = new ArrayList<ParticipacaoBancaDTO>();

        for ( ParticipacaoBanca participacaoBanca : entityList ) {

            list.add( toDto( participacaoBanca ) );
        }

        return list;
    }

    @Override

    public ParticipacaoBancaDTO toDto(ParticipacaoBanca participacaoBanca) {

        if ( participacaoBanca == null ) {

            return null;
        }

        ParticipacaoBancaDTO participacaoBancaDTO_ = new ParticipacaoBancaDTO();

        participacaoBancaDTO_.setDefesaId( participacaoBancaDefesaId( participacaoBanca ) );

        participacaoBancaDTO_.setProfessorId( participacaoBancaProfessorId( participacaoBanca ) );

        participacaoBancaDTO_.setId( participacaoBanca.getId() );

        participacaoBancaDTO_.setConfirmado( participacaoBanca.isConfirmado() );

        participacaoBancaDTO_.setEstadoAprovacaoDefesa( participacaoBanca.getEstadoAprovacaoDefesa() );

        return participacaoBancaDTO_;
    }

    @Override

    public ParticipacaoBanca toEntity(ParticipacaoBancaDTO participacaoBancaDTO) {

        if ( participacaoBancaDTO == null ) {

            return null;
        }

        ParticipacaoBanca participacaoBanca_ = new ParticipacaoBanca();

        participacaoBanca_.setDefesa( defesaMapper.fromId( participacaoBancaDTO.getDefesaId() ) );

        participacaoBanca_.setProfessor( professorMapper.fromId( participacaoBancaDTO.getProfessorId() ) );

        participacaoBanca_.setId( participacaoBancaDTO.getId() );

        participacaoBanca_.setConfirmado( participacaoBancaDTO.isConfirmado() );

        participacaoBanca_.setEstadoAprovacaoDefesa( participacaoBancaDTO.getEstadoAprovacaoDefesa() );

        return participacaoBanca_;
    }

    private Long participacaoBancaDefesaId(ParticipacaoBanca participacaoBanca) {

        if ( participacaoBanca == null ) {

            return null;
        }

        Defesa defesa = participacaoBanca.getDefesa();

        if ( defesa == null ) {

            return null;
        }

        Long id = defesa.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }

    private Long participacaoBancaProfessorId(ParticipacaoBanca participacaoBanca) {

        if ( participacaoBanca == null ) {

            return null;
        }

        Professor professor = participacaoBanca.getProfessor();

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

