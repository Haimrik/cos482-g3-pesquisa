package br.ufrj.cos482.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import br.ufrj.cos482.domain.enumeration.EstadoAprovacaoDefesa;

/**
 * A DTO for the ParticipacaoBanca entity.
 */
public class ParticipacaoBancaDTO implements Serializable {

    private Long id;

    private Boolean confirmado;

    private EstadoAprovacaoDefesa estadoAprovacaoDefesa;

    private Long defesaId;

    private String defesaArquivoTexto;

    private Long professorId;

    private String professorNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public EstadoAprovacaoDefesa getEstadoAprovacaoDefesa() {
        return estadoAprovacaoDefesa;
    }

    public void setEstadoAprovacaoDefesa(EstadoAprovacaoDefesa estadoAprovacaoDefesa) {
        this.estadoAprovacaoDefesa = estadoAprovacaoDefesa;
    }

    public Long getDefesaId() {
        return defesaId;
    }

    public void setDefesaId(Long defesaId) {
        this.defesaId = defesaId;
    }

    public String getDefesaArquivoTexto() {
        return defesaArquivoTexto;
    }

    public void setDefesaArquivoTexto(String defesaArquivoTexto) {
        this.defesaArquivoTexto = defesaArquivoTexto;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParticipacaoBancaDTO participacaoBancaDTO = (ParticipacaoBancaDTO) o;
        if(participacaoBancaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participacaoBancaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParticipacaoBancaDTO{" +
            "id=" + getId() +
            ", confirmado='" + isConfirmado() + "'" +
            ", estadoAprovacaoDefesa='" + getEstadoAprovacaoDefesa() + "'" +
            "}";
    }
}
