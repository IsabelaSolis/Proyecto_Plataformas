package com.gym.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ClientePlanRutinaId implements Serializable {

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "ID_PLAN")
    private Long idPlan;

    public ClientePlanRutinaId() {}

    public ClientePlanRutinaId(Long idCliente, Long idPlan) {
        this.idCliente = idCliente;
        this.idPlan = idPlan;
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long v) { this.idCliente = v; }
    public Long getIdPlan() { return idPlan; }
    public void setIdPlan(Long v) { this.idPlan = v; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientePlanRutinaId)) return false;
        ClientePlanRutinaId that = (ClientePlanRutinaId) o;
        return Objects.equals(idCliente, that.idCliente) && Objects.equals(idPlan, that.idPlan);
    }

    @Override
    public int hashCode() { return Objects.hash(idCliente, idPlan); }
}
