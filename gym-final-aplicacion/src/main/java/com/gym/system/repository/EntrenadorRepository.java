package com.gym.system.repository;

import com.gym.system.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {

    List<Entrenador> findByNombre(String nombre);
    List<Entrenador> findByApellido(String apellido);
    List<Entrenador> findByEspecialidad(String especialidad);
    List<Entrenador> findByEstado(String estado);
    List<Entrenador> findByNombreContainingIgnoreCase(String nombre);
}
