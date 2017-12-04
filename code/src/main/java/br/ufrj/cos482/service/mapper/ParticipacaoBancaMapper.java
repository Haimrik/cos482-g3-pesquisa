package br.ufrj.cos482.service.mapper;

import br.ufrj.cos482.domain.*;
import br.ufrj.cos482.service.dto.ParticipacaoBancaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ParticipacaoBanca and its DTO ParticipacaoBancaDTO.
 */
@Mapper(componentModel = "spring", uses = {DefesaMapper.class, ProfessorMapper.class, })
public interface ParticipacaoBancaMapper extends EntityMapper <ParticipacaoBancaDTO, ParticipacaoBanca> {

    @Mapping(source = "defesa.id", target = "defesaId")
    @Mapping(source = "defesa.arquivoTexto", target = "defesaArquivoTexto")

    @Mapping(source = "professor.id", target = "professorId")
    @Mapping(source = "professor.nome", target = "professorNome")
    ParticipacaoBancaDTO toDto(ParticipacaoBanca participacaoBanca); 

    @Mapping(source = "defesaId", target = "defesa")

    @Mapping(source = "professorId", target = "professor")
    ParticipacaoBanca toEntity(ParticipacaoBancaDTO participacaoBancaDTO); 
    default ParticipacaoBanca fromId(Long id) {
        if (id == null) {
            return null;
        }
        ParticipacaoBanca participacaoBanca = new ParticipacaoBanca();
        participacaoBanca.setId(id);
        return participacaoBanca;
    }
}
