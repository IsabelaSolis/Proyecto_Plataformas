package com.gym.system.model;

import java.io.Serializable;
import java.util.Objects;

public class AsignacionEntrenadorId implements Serializable {

    private Long idCliente;
    private Long idEntrenador;

    public AsignacionEntrenadorId() {}

    public AsignacionEntrenadorId(Long idCliente, Long idEntrenador) {
        this.idCliente = idCliente;
        this.idEntrenador = idEntrenador;
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(Long idEntrenador) { this.idEntrenador = idEntrenador; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsignacionEntrenadorId)) return false;
        AsignacionEntrenadorId that = (AsignacionEntrenadorId) o;
        return Objects.equals(idCliente, that.idCliente) &&
               Objects.equals(idEntrenador, that.idEntrenador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, idEntrenador);
    }
}
