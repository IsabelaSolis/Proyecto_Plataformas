package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "MEMBRESIA")
public class Membresia {

    @Id
    @Column(name = "ID_MEMBRESIA")
    private Long idMembresia;

    // Renombrado de idMiembro a idCliente — FK a CLIENTE(id_cliente)
    @Column(name = "ID_CLIENTE", nullable = false)
    @NotNull(message = "El id_cliente es obligatorio")
    private Long idCliente;

    @Column(name = "TIPO_MEMBRESIA", nullable = false, length = 50)
    @NotBlank(message = "El tipo de membresia es obligatorio")
    @Size(max = 50)
    private String tipoMembresia;

    @Column(name = "FECHA_INICIO", nullable = false)
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;

    @Column(name = "ESTADO", nullable = false, length = 20)
    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 20)
    private String estado;

    public Membresia() {}

    public Long getIdMembresia()            { return idMembresia; }
    public void setIdMembresia(Long v)      { this.idMembresia = v; }
    public Long getIdCliente()              { return idCliente; }
    public void setIdCliente(Long v)        { this.idCliente = v; }
    public String getTipoMembresia()        { return tipoMembresia; }
    public void setTipoMembresia(String v)  { this.tipoMembresia = v; }
    public LocalDate getFechaInicio()       { return fechaInicio; }
    public void setFechaInicio(LocalDate v) { this.fechaInicio = v; }
    public LocalDate getFechaFin()          { return fechaFin; }
    public void setFechaFin(LocalDate v)    { this.fechaFin = v; }
    public String getEstado()               { return estado; }
    public void setEstado(String v)         { this.estado = v; }
}
