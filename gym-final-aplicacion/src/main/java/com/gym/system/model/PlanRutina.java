package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "PLAN_RUTINA")
public class PlanRutina {

    @Id
    @Column(name = "ID_PLAN")
    private Long idPlan;

    @Column(name = "ID_RUTINA", nullable = false)
    @NotNull(message = "El id_rutina es obligatorio")
    private Long idRutina;

    @Column(name = "ID_NIVEL", nullable = false)
    @NotNull(message = "El id_nivel es obligatorio")
    private Long idNivel;

    @Column(name = "ID_OBJETIVO", nullable = false)
    @NotNull(message = "El id_objetivo es obligatorio")
    private Long idObjetivo;

    @Column(name = "DURACION_DIAS", nullable = false)
    @NotNull(message = "La duración en días es obligatoria")
    @Min(value = 1, message = "La duración debe ser al menos 1 día")
    private Integer duracionDias;

    public PlanRutina() {}

    public PlanRutina(Long idPlan, Long idRutina, Long idNivel, Long idObjetivo, Integer duracionDias) {
        this.idPlan = idPlan;
        this.idRutina = idRutina;
        this.idNivel = idNivel;
        this.idObjetivo = idObjetivo;
        this.duracionDias = duracionDias;
    }

    public Long getIdPlan() { return idPlan; }
    public void setIdPlan(Long v) { this.idPlan = v; }

    public Long getIdRutina() { return idRutina; }
    public void setIdRutina(Long v) { this.idRutina = v; }

    public Long getIdNivel() { return idNivel; }
    public void setIdNivel(Long v) { this.idNivel = v; }

    public Long getIdObjetivo() { return idObjetivo; }
    public void setIdObjetivo(Long v) { this.idObjetivo = v; }

    public Integer getDuracionDias() { return duracionDias; }
    public void setDuracionDias(Integer v) { this.duracionDias = v; }
}
