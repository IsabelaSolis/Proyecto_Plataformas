package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ASISTENCIA")
public class Asistencia {

    @Id
    @Column(name = "ID_ASISTENCIA")
    private Long idAsistencia;

    @Column(name = "ID_CLIENTE", nullable = false)
    @NotNull(message = "El id_cliente es obligatorio")
    private Long idCliente;

    @Column(name = "FECHA_ASISTENCIA", nullable = false)
    @NotNull(message = "La fecha de asistencia es obligatoria")
    private LocalDate fechaAsistencia;

    @Column(name = "HORA_ENTRADA", nullable = false)
    @NotNull(message = "La hora de entrada es obligatoria")
    private LocalDateTime horaEntrada;

    @Column(name = "HORA_SALIDA", nullable = false)
    @NotNull(message = "La hora de salida es obligatoria")
    private LocalDateTime horaSalida;

    @Transient
    public long getDuracionMinutos() {
        if (horaEntrada != null && horaSalida != null) {
            return Duration.between(horaEntrada, horaSalida).toMinutes();
        }
        return 0;
    }

    public Asistencia() {}

    public Asistencia(Long idAsistencia, Long idCliente, LocalDate fechaAsistencia,
                      LocalDateTime horaEntrada, LocalDateTime horaSalida) {
        this.idAsistencia = idAsistencia;
        this.idCliente = idCliente;
        this.fechaAsistencia = fechaAsistencia;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public Long getIdAsistencia() { return idAsistencia; }
    public void setIdAsistencia(Long idAsistencia) { this.idAsistencia = idAsistencia; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public LocalDate getFechaAsistencia() { return fechaAsistencia; }
    public void setFechaAsistencia(LocalDate fechaAsistencia) { this.fechaAsistencia = fechaAsistencia; }

    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public void setHoraEntrada(LocalDateTime horaEntrada) { this.horaEntrada = horaEntrada; }

    public LocalDateTime getHoraSalida() { return horaSalida; }
    public void setHoraSalida(LocalDateTime horaSalida) { this.horaSalida = horaSalida; }
}
