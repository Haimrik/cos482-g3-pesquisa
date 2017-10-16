package br.ufrj.cos482.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import br.ufrj.cos482.domain.enumeration.EstadoAprovacaoDefesa;

/**
 * A ParticipacaoBanca.
 */
@Entity
@Table(name = "participacao_banca")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ParticipacaoBanca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "confirmado")
    private Boolean confirmado;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_aprovacao_defesa")
    private EstadoAprovacaoDefesa estadoAprovacaoDefesa;

    @ManyToOne
    private Defesa defesa;

    @ManyToOne
    private Professor professor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isConfirmado() {
        return confirmado;
    }

    public ParticipacaoBanca confirmado(Boolean confirmado) {
        this.confirmado = confirmado;
        return this;
    }

    public void setConfirmado(Boolean confirmado) {
        this.confirmado = confirmado;
    }

    public EstadoAprovacaoDefesa getEstadoAprovacaoDefesa() {
        return estadoAprovacaoDefesa;
    }

    public ParticipacaoBanca estadoAprovacaoDefesa(EstadoAprovacaoDefesa estadoAprovacaoDefesa) {
        this.estadoAprovacaoDefesa = estadoAprovacaoDefesa;
        return this;
    }

    public void setEstadoAprovacaoDefesa(EstadoAprovacaoDefesa estadoAprovacaoDefesa) {
        this.estadoAprovacaoDefesa = estadoAprovacaoDefesa;
    }

    public Defesa getDefesa() {
        return defesa;
    }

    public ParticipacaoBanca defesa(Defesa defesa) {
        this.defesa = defesa;
        return this;
    }

    public void setDefesa(Defesa defesa) {
        this.defesa = defesa;
    }

    public Professor getProfessor() {
        return professor;
    }

    public ParticipacaoBanca professor(Professor professor) {
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
        ParticipacaoBanca participacaoBanca = (ParticipacaoBanca) o;
        if (participacaoBanca.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participacaoBanca.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParticipacaoBanca{" +
            "id=" + getId() +
            ", confirmado='" + isConfirmado() + "'" +
            ", estadoAprovacaoDefesa='" + getEstadoAprovacaoDefesa() + "'" +
            "}";
    }
}
