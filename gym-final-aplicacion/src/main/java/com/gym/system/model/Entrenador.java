package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "ENTRENADOR")
public class Entrenador {

    @Id
    @Column(name = "ID_ENTRENADOR")
    private Long idEntrenador;

    @Column(name = "NOMBRE", nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 50)
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(name = "FECHA_CONTRATACION", nullable = false)
    @NotNull(message = "La fecha de contratación es obligatoria")
    private LocalDate fechaContratacion;

    @Column(name = "ESPECIALIDAD", nullable = false, length = 50)
    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @Column(name = "ESTADO", nullable = false, length = 30)
    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public Entrenador() {}

    public Entrenador(Long idEntrenador, String nombre, String apellido,
                      LocalDate fechaContratacion, String especialidad, String estado) {
        this.idEntrenador = idEntrenador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaContratacion = fechaContratacion;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public Long getIdEntrenador() { return idEntrenador; }
    public void setIdEntrenador(Long idEntrenador) { this.idEntrenador = idEntrenador; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(LocalDate fechaContratacion) { this.fechaContratacion = fechaContratacion; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
