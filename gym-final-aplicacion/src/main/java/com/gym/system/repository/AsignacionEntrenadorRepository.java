package com.gym.system.repository;

import com.gym.system.model.AsignacionEntrenador;
import com.gym.system.model.AsignacionEntrenadorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AsignacionEntrenadorRepository
        extends JpaRepository<AsignacionEntrenador, AsignacionEntrenadorId> {

    List<AsignacionEntrenador> findByIdCliente(Long idCliente);
    List<AsignacionEntrenador> findByIdEntrenador(Long idEntrenador);
}
