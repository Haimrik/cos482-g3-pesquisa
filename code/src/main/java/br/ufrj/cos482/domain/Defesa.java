package br.ufrj.cos482.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import br.ufrj.cos482.domain.enumeration.TipoDefesa;

/**
 * A Defesa.
 */
@Entity
@Table(name = "defesa")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Defesa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_e_hora")
    private ZonedDateTime dataEHora;

    @Column(name = "jhi_local")
    private String local;

    @Column(name = "arquivo_texto")
    private String arquivoTexto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_defesa")
    private TipoDefesa tipoDefesa;

    @OneToMany(mappedBy = "defesa")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ParticipacaoBanca> participacaoBancas = new HashSet<>();

    @ManyToOne
    private Aluno aluno;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDataEHora() {
        return dataEHora;
    }

    public Defesa dataEHora(ZonedDateTime dataEHora) {
        this.dataEHora = dataEHora;
        return this;
    }

    public void setDataEHora(ZonedDateTime dataEHora) {
        this.dataEHora = dataEHora;
    }

    public String getLocal() {
        return local;
    }

    public Defesa local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getArquivoTexto() {
        return arquivoTexto;
    }

    public Defesa arquivoTexto(String arquivoTexto) {
        this.arquivoTexto = arquivoTexto;
        return this;
    }

    public void setArquivoTexto(String arquivoTexto) {
        this.arquivoTexto = arquivoTexto;
    }

    public TipoDefesa getTipoDefesa() {
        return tipoDefesa;
    }

    public Defesa tipoDefesa(TipoDefesa tipoDefesa) {
        this.tipoDefesa = tipoDefesa;
        return this;
    }

    public void setTipoDefesa(TipoDefesa tipoDefesa) {
        this.tipoDefesa = tipoDefesa;
    }

    public Set<ParticipacaoBanca> getParticipacaoBancas() {
        return participacaoBancas;
    }

    public Defesa participacaoBancas(Set<ParticipacaoBanca> participacaoBancas) {
        this.participacaoBancas = participacaoBancas;
        return this;
    }

    public Defesa addParticipacaoBanca(ParticipacaoBanca participacaoBanca) {
        this.participacaoBancas.add(participacaoBanca);
        participacaoBanca.setDefesa(this);
        return this;
    }

    public Defesa removeParticipacaoBanca(ParticipacaoBanca participacaoBanca) {
        this.participacaoBancas.remove(participacaoBanca);
        participacaoBanca.setDefesa(null);
        return this;
    }

    public void setParticipacaoBancas(Set<ParticipacaoBanca> participacaoBancas) {
        this.participacaoBancas = participacaoBancas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Defesa aluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
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
        Defesa defesa = (Defesa) o;
        if (defesa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), defesa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Defesa{" +
            "id=" + getId() +
            ", dataEHora='" + getDataEHora() + "'" +
            ", local='" + getLocal() + "'" +
            ", arquivoTexto='" + getArquivoTexto() + "'" +
            ", tipoDefesa='" + getTipoDefesa() + "'" +
            "}";
    }
}
