package br.ufrj.cos482.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Seminario.
 */
@Entity
@Table(name = "seminario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Seminario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "data_e_hora")
    private ZonedDateTime dataEHora;

    @Column(name = "jhi_local")
    private String local;

    @ManyToOne
    private Aluno organizadorAluno;

    @ManyToOne
    private Professor organizadorProfessor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Seminario titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ZonedDateTime getDataEHora() {
        return dataEHora;
    }

    public Seminario dataEHora(ZonedDateTime dataEHora) {
        this.dataEHora = dataEHora;
        return this;
    }

    public void setDataEHora(ZonedDateTime dataEHora) {
        this.dataEHora = dataEHora;
    }

    public String getLocal() {
        return local;
    }

    public Seminario local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Aluno getOrganizadorAluno() {
        return organizadorAluno;
    }

    public Seminario organizadorAluno(Aluno aluno) {
        this.organizadorAluno = aluno;
        return this;
    }

    public void setOrganizadorAluno(Aluno aluno) {
        this.organizadorAluno = aluno;
    }

    public Professor getOrganizadorProfessor() {
        return organizadorProfessor;
    }

    public Seminario organizadorProfessor(Professor professor) {
        this.organizadorProfessor = professor;
        return this;
    }

    public void setOrganizadorProfessor(Professor professor) {
        this.organizadorProfessor = professor;
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
        Seminario seminario = (Seminario) o;
        if (seminario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), seminario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Seminario{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", dataEHora='" + getDataEHora() + "'" +
            ", local='" + getLocal() + "'" +
            "}";
    }
}
