package br.ufrj.cos482.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Aluno entity.
 */
public class AlunoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String dre;

    @NotNull
    private ZonedDateTime dataDeEntrada;

    private Set<PublicacaoDTO> copublicacaos = new HashSet<>();

    private Long orientadorId;

    private String orientadorNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDre() {
        return dre;
    }

    public void setDre(String dre) {
        this.dre = dre;
    }

    public ZonedDateTime getDataDeEntrada() {
        return dataDeEntrada;
    }

    public void setDataDeEntrada(ZonedDateTime dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    public Set<PublicacaoDTO> getCopublicacaos() {
        return copublicacaos;
    }

    public void setCopublicacaos(Set<PublicacaoDTO> publicacaos) {
        this.copublicacaos = publicacaos;
    }

    public Long getOrientadorId() {
        return orientadorId;
    }

    public void setOrientadorId(Long professorId) {
        this.orientadorId = professorId;
    }

    public String getOrientadorNome() {
        return orientadorNome;
    }

    public void setOrientadorNome(String professorNome) {
        this.orientadorNome = professorNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlunoDTO alunoDTO = (AlunoDTO) o;
        if(alunoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alunoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AlunoDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dre='" + getDre() + "'" +
            ", dataDeEntrada='" + getDataDeEntrada() + "'" +
            "}";
    }
}
