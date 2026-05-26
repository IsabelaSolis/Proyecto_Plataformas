package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "NIVEL")
public class Nivel {

    @Id
    @Column(name = "ID_NIVEL")
    private Long idNivel;

    @Column(name = "NIVEL", nullable = false, length = 50)
    @NotBlank(message = "El nivel es obligatorio")
    @Size(max = 50)
    private String nivel;

    public Nivel() {}

    public Nivel(Long idNivel, String nivel) {
        this.idNivel = idNivel;
        this.nivel = nivel;
    }

    public Long getIdNivel() { return idNivel; }
    public void setIdNivel(Long v) { this.idNivel = v; }

    public String getNivel() { return nivel; }
    public void setNivel(String v) { this.nivel = v; }
}
