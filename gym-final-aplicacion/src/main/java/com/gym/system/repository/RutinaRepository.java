package com.gym.system.repository;

import com.gym.system.model.Rutina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RutinaRepository extends JpaRepository<Rutina, Long> {

    List<Rutina> findByNombreRutinaContainingIgnoreCase(String nombre);
}
