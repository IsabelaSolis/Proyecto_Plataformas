package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "ASIGNACION_ENTRENADOR")
@IdClass(AsignacionEntrenadorId.class)
public class AsignacionEntrenador {

    @Id
    @Column(name = "ID_CLIENTE")
    @NotNull(message = "El idCliente es obligatorio")
    private Long idCliente;

    @Id
    @Column(name = "ID_ENTRENADOR")
    @NotNull(message = "El idEntrenador es obligatorio")
    private Long idEntrenador;

    public AsignacionEntrenador() {}

    public AsignacionEntrenador(Long idCliente, Long idEntrenador) {
        this.idCliente = idCliente;
        this.idEntrenador = idEntrenador;
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public Long getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(Long idEntrenador) { this.idEntrenador = idEntrenador; }
}
