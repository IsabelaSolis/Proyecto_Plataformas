package com.gym.system.repository;

import com.gym.system.model.PlanRutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanRutinaRepository extends JpaRepository<PlanRutina, Long> {

    List<PlanRutina> findByIdRutina(Long idRutina);
    List<PlanRutina> findByIdNivel(Long idNivel);
    List<PlanRutina> findByIdObjetivo(Long idObjetivo);
}
