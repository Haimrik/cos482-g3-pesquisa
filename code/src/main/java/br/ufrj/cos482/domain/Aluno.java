package br.ufrj.cos482.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Aluno.
 */
@Entity
@Table(name = "aluno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "dre", nullable = false)
    private String dre;

    @NotNull
    @Column(name = "data_de_entrada", nullable = false)
    private ZonedDateTime dataDeEntrada;

    @OneToOne
    @JoinColumn(unique = true)
    private Usuario usuario;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Publicacao> publicacaos = new HashSet<>();

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Defesa> defesas = new HashSet<>();

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Reuniao> reuniaos = new HashSet<>();

    @ManyToOne
    private Professor orientador;

    @ManyToMany(mappedBy = "coorientadors")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Professor> alunos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Aluno nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDre() {
        return dre;
    }

    public Aluno dre(String dre) {
        this.dre = dre;
        return this;
    }

    public void setDre(String dre) {
        this.dre = dre;
    }

    public ZonedDateTime getDataDeEntrada() {
        return dataDeEntrada;
    }

    public Aluno dataDeEntrada(ZonedDateTime dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
        return this;
    }

    public void setDataDeEntrada(ZonedDateTime dataDeEntrada) {
        this.dataDeEntrada = dataDeEntrada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Aluno usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Publicacao> getPublicacaos() {
        return publicacaos;
    }

    public Aluno publicacaos(Set<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
        return this;
    }

    public Aluno addPublicacao(Publicacao publicacao) {
        this.publicacaos.add(publicacao);
        publicacao.setAluno(this);
        return this;
    }

    public Aluno removePublicacao(Publicacao publicacao) {
        this.publicacaos.remove(publicacao);
        publicacao.setAluno(null);
        return this;
    }

    public void setPublicacaos(Set<Publicacao> publicacaos) {
        this.publicacaos = publicacaos;
    }

    public Set<Defesa> getDefesas() {
        return defesas;
    }

    public Aluno defesas(Set<Defesa> defesas) {
        this.defesas = defesas;
        return this;
    }

    public Aluno addDefesa(Defesa defesa) {
        this.defesas.add(defesa);
        defesa.setAluno(this);
        return this;
    }

    public Aluno removeDefesa(Defesa defesa) {
        this.defesas.remove(defesa);
        defesa.setAluno(null);
        return this;
    }

    public void setDefesas(Set<Defesa> defesas) {
        this.defesas = defesas;
    }

    public Set<Reuniao> getReuniaos() {
        return reuniaos;
    }

    public Aluno reuniaos(Set<Reuniao> reuniaos) {
        this.reuniaos = reuniaos;
        return this;
    }

    public Aluno addReuniao(Reuniao reuniao) {
        this.reuniaos.add(reuniao);
        reuniao.setAluno(this);
        return this;
    }

    public Aluno removeReuniao(Reuniao reuniao) {
        this.reuniaos.remove(reuniao);
        reuniao.setAluno(null);
        return this;
    }

    public void setReuniaos(Set<Reuniao> reuniaos) {
        this.reuniaos = reuniaos;
    }

    public Professor getOrientador() {
        return orientador;
    }

    public Aluno orientador(Professor professor) {
        this.orientador = professor;
        return this;
    }

    public void setOrientador(Professor professor) {
        this.orientador = professor;
    }

    public Set<Professor> getAlunos() {
        return alunos;
    }

    public Aluno alunos(Set<Professor> professors) {
        this.alunos = professors;
        return this;
    }

    public Aluno addAluno(Professor professor) {
        this.alunos.add(professor);
        professor.getCoorientadors().add(this);
        return this;
    }

    public Aluno removeAluno(Professor professor) {
        this.alunos.remove(professor);
        professor.getCoorientadors().remove(this);
        return this;
    }

    public void setAlunos(Set<Professor> professors) {
        this.alunos = professors;
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
        Aluno aluno = (Aluno) o;
        if (aluno.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aluno.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aluno{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", dre='" + getDre() + "'" +
            ", dataDeEntrada='" + getDataDeEntrada() + "'" +
            "}";
    }
}
