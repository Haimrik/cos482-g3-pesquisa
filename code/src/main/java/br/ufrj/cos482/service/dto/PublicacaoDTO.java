package br.ufrj.cos482.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Publicacao entity.
 */
public class PublicacaoDTO implements Serializable {

    private Long id;

    @NotNull
    private String url;

    private Boolean pertenceAoPrograma;

    private Long alunoId;

    private String alunoNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isPertenceAoPrograma() {
        return pertenceAoPrograma;
    }

    public void setPertenceAoPrograma(Boolean pertenceAoPrograma) {
        this.pertenceAoPrograma = pertenceAoPrograma;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PublicacaoDTO publicacaoDTO = (PublicacaoDTO) o;
        if(publicacaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), publicacaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PublicacaoDTO{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", pertenceAoPrograma='" + isPertenceAoPrograma() + "'" +
            "}";
    }
}
