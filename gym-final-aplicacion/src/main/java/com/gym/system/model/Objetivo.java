package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "OBJETIVO")
public class Objetivo {

    @Id
    @Column(name = "ID_OBJETIVO")
    private Long idObjetivo;

    @Column(name = "OBJETIVO", nullable = false, length = 100)
    @NotBlank(message = "El objetivo es obligatorio")
    @Size(max = 100)
    private String objetivo;

    public Objetivo() {}

    public Objetivo(Long idObjetivo, String objetivo) {
        this.idObjetivo = idObjetivo;
        this.objetivo = objetivo;
    }

    public Long getIdObjetivo() { return idObjetivo; }
    public void setIdObjetivo(Long v) { this.idObjetivo = v; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String v) { this.objetivo = v; }
}
