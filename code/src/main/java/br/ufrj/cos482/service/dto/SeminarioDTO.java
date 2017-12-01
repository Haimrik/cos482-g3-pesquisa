package br.ufrj.cos482.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Seminario entity.
 */
public class SeminarioDTO implements Serializable {

    private Long id;

    private String titulo;

    private ZonedDateTime dataEHora;

    private String local;

    private Long organizadorAlunoId;

    private String organizadorAlunoNome;

    private Long organizadorProfessorId;

    private String organizadorProfessorNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ZonedDateTime getDataEHora() {
        return dataEHora;
    }

    public void setDataEHora(ZonedDateTime dataEHora) {
        this.dataEHora = dataEHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Long getOrganizadorAlunoId() {
        return organizadorAlunoId;
    }

    public void setOrganizadorAlunoId(Long alunoId) {
        this.organizadorAlunoId = alunoId;
    }

    public String getOrganizadorAlunoNome() {
        return organizadorAlunoNome;
    }

    public void setOrganizadorAlunoNome(String alunoNome) {
        this.organizadorAlunoNome = alunoNome;
    }

    public Long getOrganizadorProfessorId() {
        return organizadorProfessorId;
    }

    public void setOrganizadorProfessorId(Long professorId) {
        this.organizadorProfessorId = professorId;
    }

    public String getOrganizadorProfessorNome() {
        return organizadorProfessorNome;
    }

    public void setOrganizadorProfessorNome(String professorNome) {
        this.organizadorProfessorNome = professorNome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SeminarioDTO seminarioDTO = (SeminarioDTO) o;
        if(seminarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), seminarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SeminarioDTO{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", dataEHora='" + getDataEHora() + "'" +
            ", local='" + getLocal() + "'" +
            "}";
    }
}
