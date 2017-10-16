package br.ufrj.cos482.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Usuario.
 */
@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "organizador")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Seminario> seminarios = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "usuario_publicacao",
               joinColumns = @JoinColumn(name="usuarios_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="publicacaos_id", referencedColumnName="id"))
    private Set<Publicacao> publicacaos = new HashSet<>();

    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private Aluno aluno;

    @OneToOne(mappedBy = "usuario")
    @JsonIgnore
    private Professor professor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Seminario> getSeminarios() {
        return seminarios;
    }

    public Usuario seminarios(Set<Seminario> seminarios) {
        this.seminarios = seminarios;
        return this;
    }

    public Usuario addSeminario(Seminario seminario) {
        this.seminarios.add(seminario);
        seminario.setOrganizador(this);
        return this;
    }

    public Usuario removeSeminario(Seminario seminario) {
        this.seminarios.remove(seminario);
        seminario.setOrganizador(null);
        return this;
    }

    public void setSeminarios(Set<Seminario> seminarios) {
        this.seminarios = seminarios;
    }

    public Set<Publicacao> getPublicacaos() {
        return publicacaos;
    }

    public Usuario publicacaos(Set<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
        return this;
    }

    public Usuario addPublicacao(Publicacao publicacao) {
        this.publicacaos.add(publicacao);
        publicacao.getCoautors().add(this);
        return this;
    }

    public Usuario removePublicacao(Publicacao publicacao) {
        this.publicacaos.remove(publicacao);
        publicacao.getCoautors().remove(this);
        return this;
    }

    public void setPublicacaos(Set<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Usuario aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Usuario professor(Professor professor) {
        this.professor = professor;
        return this;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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
        Usuario usuario = (Usuario) o;
        if (usuario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + getId() +
            "}";
    }
}
