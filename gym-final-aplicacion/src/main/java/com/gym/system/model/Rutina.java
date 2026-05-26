package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "RUTINA")
public class Rutina {

    @Id
    @Column(name = "ID_RUTINA")
    private Long idRutina;

    @Column(name = "NOMBRE_RUTINA", nullable = false, length = 50)
    @NotBlank(message = "El nombre de rutina es obligatorio")
    @Size(max = 50)
    private String nombreRutina;

    public Rutina() {}

    public Rutina(Long idRutina, String nombreRutina) {
        this.idRutina = idRutina;
        this.nombreRutina = nombreRutina;
    }

    public Long getIdRutina() { return idRutina; }
    public void setIdRutina(Long v) { this.idRutina = v; }

    public String getNombreRutina() { return nombreRutina; }
    public void setNombreRutina(String v) { this.nombreRutina = v; }
}
