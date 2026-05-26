package com.gym.system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIENTE_PLAN_RUTINA")
public class ClientePlanRutina {

    @EmbeddedId
    private ClientePlanRutinaId id;

    public ClientePlanRutina() {}

    public ClientePlanRutina(Long idCliente, Long idPlan) {
        this.id = new ClientePlanRutinaId(idCliente, idPlan);
    }

    public ClientePlanRutinaId getId()        { return id; }
    public void setId(ClientePlanRutinaId v)  { this.id = v; }

    // Helpers para acceso directo desde el controller/frontend
    @Transient
    public Long getIdCliente() { return id != null ? id.getIdCliente() : null; }
    @Transient
    public Long getIdPlan()    { return id != null ? id.getIdPlan() : null; }
}
