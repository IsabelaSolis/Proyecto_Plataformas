package com.gym.system.repository;

import com.gym.system.model.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
    List<Membresia> findByIdCliente(Long idCliente);
    List<Membresia> findByEstado(String estado);
    List<Membresia> findByTipoMembresia(String tipoMembresia);
}
