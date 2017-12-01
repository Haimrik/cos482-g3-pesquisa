package br.ufrj.cos482.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Professor entity.
 */
public class ProfessorDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String matricula;

    @NotNull
    private String linkLattes;

    private String programa;

    private String linhaDePesquisa;

    private String areasDeInteresse;

    private Set<PublicacaoDTO> copublicacaos = new HashSet<>();

    private Set<AlunoDTO> coorientandos = new HashSet<>();

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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getLinkLattes() {
        return linkLattes;
    }

    public void setLinkLattes(String linkLattes) {
        this.linkLattes = linkLattes;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getLinhaDePesquisa() {
        return linhaDePesquisa;
    }

    public void setLinhaDePesquisa(String linhaDePesquisa) {
        this.linhaDePesquisa = linhaDePesquisa;
    }

    public String getAreasDeInteresse() {
        return areasDeInteresse;
    }

    public void setAreasDeInteresse(String areasDeInteresse) {
        this.areasDeInteresse = areasDeInteresse;
    }

    public Set<PublicacaoDTO> getCopublicacaos() {
        return copublicacaos;
    }

    public void setCopublicacaos(Set<PublicacaoDTO> publicacaos) {
        this.copublicacaos = publicacaos;
    }

    public Set<AlunoDTO> getCoorientandos() {
        return coorientandos;
    }

    public void setCoorientandos(Set<AlunoDTO> alunos) {
        this.coorientandos = alunos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProfessorDTO professorDTO = (ProfessorDTO) o;
        if(professorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), professorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProfessorDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", matricula='" + getMatricula() + "'" +
            ", linkLattes='" + getLinkLattes() + "'" +
            ", programa='" + getPrograma() + "'" +
            ", linhaDePesquisa='" + getLinhaDePesquisa() + "'" +
            ", areasDeInteresse='" + getAreasDeInteresse() + "'" +
            "}";
    }
}
