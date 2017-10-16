package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.Aluno;

import br.ufrj.cos482.domain.Defesa;

import br.ufrj.cos482.service.dto.DefesaDTO;

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

public class DefesaMapperImpl implements DefesaMapper {

    @Autowired

    private AlunoMapper alunoMapper;

    @Override

    public List<Defesa> toEntity(List<DefesaDTO> dtoList) {

        if ( dtoList == null ) {

            return null;
        }

        List<Defesa> list = new ArrayList<Defesa>();

        for ( DefesaDTO defesaDTO : dtoList ) {

            list.add( toEntity( defesaDTO ) );
        }

        return list;
    }

    @Override

    public List<DefesaDTO> toDto(List<Defesa> entityList) {

        if ( entityList == null ) {

            return null;
        }

        List<DefesaDTO> list = new ArrayList<DefesaDTO>();

        for ( Defesa defesa : entityList ) {

            list.add( toDto( defesa ) );
        }

        return list;
    }

    @Override

    public DefesaDTO toDto(Defesa defesa) {

        if ( defesa == null ) {

            return null;
        }

        DefesaDTO defesaDTO_ = new DefesaDTO();

        defesaDTO_.setAlunoId( defesaAlunoId( defesa ) );

        defesaDTO_.setId( defesa.getId() );

        defesaDTO_.setDataEHora( defesa.getDataEHora() );

        defesaDTO_.setLocal( defesa.getLocal() );

        defesaDTO_.setArquivoTexto( defesa.getArquivoTexto() );

        defesaDTO_.setTipoDefesa( defesa.getTipoDefesa() );

        return defesaDTO_;
    }

    @Override

    public Defesa toEntity(DefesaDTO defesaDTO) {

        if ( defesaDTO == null ) {

            return null;
        }

        Defesa defesa_ = new Defesa();

        defesa_.setAluno( alunoMapper.fromId( defesaDTO.getAlunoId() ) );

        defesa_.setId( defesaDTO.getId() );

        defesa_.setDataEHora( defesaDTO.getDataEHora() );

        defesa_.setLocal( defesaDTO.getLocal() );

        defesa_.setArquivoTexto( defesaDTO.getArquivoTexto() );

        defesa_.setTipoDefesa( defesaDTO.getTipoDefesa() );

        return defesa_;
    }

    private Long defesaAlunoId(Defesa defesa) {

        if ( defesa == null ) {

            return null;
        }

        Aluno aluno = defesa.getAluno();

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

