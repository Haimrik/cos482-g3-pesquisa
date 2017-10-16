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

    @ManyToMany(mappedBy = "publicacaos")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Usuario> coautors = new HashSet<>();

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

    public Set<Usuario> getCoautors() {
        return coautors;
    }

    public Publicacao coautors(Set<Usuario> usuarios) {
        this.coautors = usuarios;
        return this;
    }

    public Publicacao addCoautor(Usuario usuario) {
        this.coautors.add(usuario);
        usuario.getPublicacaos().add(this);
        return this;
    }

    public Publicacao removeCoautor(Usuario usuario) {
        this.coautors.remove(usuario);
        usuario.getPublicacaos().remove(this);
        return this;
    }

    public void setCoautors(Set<Usuario> usuarios) {
        this.coautors = usuarios;
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
