package br.ufrj.cos482.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Publicacao.
 */
@Entity
@Table(name = "publicacao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Publicacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "pertence_ao_programa")
    private Boolean pertenceAoPrograma;

    @ManyToOne
    private Aluno aluno;

    @ManyToMany(mappedBy = "copublicacaos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Aluno> coautorAlunos = new HashSet<>();

    @ManyToMany(mappedBy = "copublicacaos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Professor> coautorProfessors = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public Publicacao url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean isPertenceAoPrograma() {
        return pertenceAoPrograma;
    }

    public Publicacao pertenceAoPrograma(Boolean pertenceAoPrograma) {
        this.pertenceAoPrograma = pertenceAoPrograma;
        return this;
    }

    public void setPertenceAoPrograma(Boolean pertenceAoPrograma) {
        this.pertenceAoPrograma = pertenceAoPrograma;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Publicacao aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Set<Aluno> getCoautorAlunos() {
        return coautorAlunos;
    }

    public Publicacao coautorAlunos(Set<Aluno> alunos) {
        this.coautorAlunos = alunos;
        return this;
    }

    public Publicacao addCoautorAluno(Aluno aluno) {
        this.coautorAlunos.add(aluno);
        aluno.getCopublicacaos().add(this);
        return this;
    }

    public Publicacao removeCoautorAluno(Aluno aluno) {
        this.coautorAlunos.remove(aluno);
        aluno.getCopublicacaos().remove(this);
        return this;
    }

    public void setCoautorAlunos(Set<Aluno> alunos) {
        this.coautorAlunos = alunos;
    }

    public Set<Professor> getCoautorProfessors() {
        return coautorProfessors;
    }

    public Publicacao coautorProfessors(Set<Professor> professors) {
        this.coautorProfessors = professors;
        return this;
    }

    public Publicacao addCoautorProfessor(Professor professor) {
        this.coautorProfessors.add(professor);
        professor.getCopublicacaos().add(this);
        return this;
    }

    public Publicacao removeCoautorProfessor(Professor professor) {
        this.coautorProfessors.remove(professor);
        professor.getCopublicacaos().remove(this);
        return this;
    }

    public void setCoautorProfessors(Set<Professor> professors) {
        this.coautorProfessors = professors;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Publicacao publicacao = (Publicacao) o;
        if (publicacao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), publicacao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Publicacao{" +
            "id=" + getId() +
            ", url='" + getUrl() + "'" +
            ", pertenceAoPrograma='" + isPertenceAoPrograma() + "'" +
            "}";
    }
}
