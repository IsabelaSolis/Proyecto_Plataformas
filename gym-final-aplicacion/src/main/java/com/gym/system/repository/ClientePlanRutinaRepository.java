package com.gym.system.repository;

import com.gym.system.model.ClientePlanRutina;
import com.gym.system.model.ClientePlanRutinaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientePlanRutinaRepository extends JpaRepository<ClientePlanRutina, ClientePlanRutinaId> {
    List<ClientePlanRutina> findByIdIdCliente(Long idCliente);
    List<ClientePlanRutina> findByIdIdPlan(Long idPlan);
}
