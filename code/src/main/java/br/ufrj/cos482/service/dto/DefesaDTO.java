package br.ufrj.cos482.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import br.ufrj.cos482.domain.enumeration.TipoDefesa;

/**
 * A DTO for the Defesa entity.
 */
public class DefesaDTO implements Serializable {

    private Long id;

    private ZonedDateTime dataEHora;

    private String local;

    private String arquivoTexto;

    private TipoDefesa tipoDefesa;

    private Long alunoId;

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

    public String getArquivoTexto() {
        return arquivoTexto;
    }

    public void setArquivoTexto(String arquivoTexto) {
        this.arquivoTexto = arquivoTexto;
    }

    public TipoDefesa getTipoDefesa() {
        return tipoDefesa;
    }

    public void setTipoDefesa(TipoDefesa tipoDefesa) {
        this.tipoDefesa = tipoDefesa;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefesaDTO defesaDTO = (DefesaDTO) o;
        if(defesaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), defesaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DefesaDTO{" +
            "id=" + getId() +
            ", dataEHora='" + getDataEHora() + "'" +
            ", local='" + getLocal() + "'" +
            ", arquivoTexto='" + getArquivoTexto() + "'" +
            ", tipoDefesa='" + getTipoDefesa() + "'" +
            "}";
    }
}
