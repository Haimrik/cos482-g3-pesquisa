package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Aluno;

import br.ufrj.cos482.domain.Publicacao;

import br.ufrj.cos482.service.dto.PublicacaoDTO;

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

public class PublicacaoMapperImpl implements PublicacaoMapper {

    @Autowired

    private AlunoMapper alunoMapper;

    @Override

    public List<Publicacao> toEntity(List<PublicacaoDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Publicacao> list = new ArrayList<Publicacao>();

        for ( PublicacaoDTO publicacaoDTO : dtoList ) {

            list.add( toEntity( publicacaoDTO ) );
        }

        return list;
    }

    @Override

    public List<PublicacaoDTO> toDto(List<Publicacao> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<PublicacaoDTO> list = new ArrayList<PublicacaoDTO>();

        for ( Publicacao publicacao : entityList ) {

            list.add( toDto( publicacao ) );
        }

        return list;
    }

    @Override

    public PublicacaoDTO toDto(Publicacao publicacao) {

        if ( publicacao == null ) {

            return null;
        }

        PublicacaoDTO publicacaoDTO_ = new PublicacaoDTO();

        publicacaoDTO_.setAlunoId( publicacaoAlunoId( publicacao ) );

        publicacaoDTO_.setId( publicacao.getId() );

        publicacaoDTO_.setUrl( publicacao.getUrl() );

        publicacaoDTO_.setPertenceAoPrograma( publicacao.isPertenceAoPrograma() );

        return publicacaoDTO_;
    }

    @Override

    public Publicacao toEntity(PublicacaoDTO publicacaoDTO) {

        if ( publicacaoDTO == null ) {

            return null;
        }

        Publicacao publicacao_ = new Publicacao();

        publicacao_.setAluno( alunoMapper.fromId( publicacaoDTO.getAlunoId() ) );

        publicacao_.setId( publicacaoDTO.getId() );

        publicacao_.setUrl( publicacaoDTO.getUrl() );

        publicacao_.setPertenceAoPrograma( publicacaoDTO.isPertenceAoPrograma() );

        return publicacao_;
    }

    private Long publicacaoAlunoId(Publicacao publicacao) {

        if ( publicacao == null ) {

            return null;
        }

        Aluno aluno = publicacao.getAluno();

        if ( aluno == null ) {

            return null;
        }

        Long id = aluno.getId();

        if ( id == null ) {

            return null;
        }

        return id;
    }
}

