package com.gym.system.repository;

import com.gym.system.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNombre(String nombre);
    List<Cliente> findByApellido(String apellido);
    Cliente findByCorreo(String correo);
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);
}
