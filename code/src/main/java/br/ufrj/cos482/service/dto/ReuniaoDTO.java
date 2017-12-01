package br.ufrj.cos482.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Reuniao entity.
 */
public class ReuniaoDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataEHora;

    private String local;

    private Long professorId;

    private String professorNome;

    private Long alunoId;

    private String alunoNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        ReuniaoDTO reuniaoDTO = (ReuniaoDTO) o;
        if(reuniaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reuniaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReuniaoDTO{" +
            "id=" + getId() +
            ", dataEHora='" + getDataEHora() + "'" +
            ", local='" + getLocal() + "'" +
            "}";
    }
}
