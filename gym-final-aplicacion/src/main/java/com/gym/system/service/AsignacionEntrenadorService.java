package com.gym.system.service;

import com.gym.system.model.AsignacionEntrenador;
import com.gym.system.model.AsignacionEntrenadorId;
import com.gym.system.repository.AsignacionEntrenadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AsignacionEntrenadorService {

    private final AsignacionEntrenadorRepository repo;

    @Autowired
    public AsignacionEntrenadorService(AsignacionEntrenadorRepository repo) {
        this.repo = repo;
    }

    // obtener todas las asignaciones
    @Transactional(readOnly = true)
    public List<AsignacionEntrenador> findAll() {
        return repo.findAll();
    }

    //por cliente
    @Transactional(readOnly = true)
    public List<AsignacionEntrenador> findByCliente(Long idCliente) {
        return repo.findByIdCliente(idCliente);
    }

    //por entrenador
    @Transactional(readOnly = true)
    public List<AsignacionEntrenador> findByEntrenador(Long idEntrenador) {
        return repo.findByIdEntrenador(idEntrenador);
    }

    //guardar asignación
    public AsignacionEntrenador save(AsignacionEntrenador a) {
        return repo.save(a);
    }

    // eliminar UNA asignación específica
    public void delete(Long idCliente, Long idEntrenador) {

        AsignacionEntrenadorId id =
                new AsignacionEntrenadorId(idCliente, idEntrenador);

        if (!repo.existsById(id)) {
            throw new RuntimeException("Asignación no encontrada");
        }

        repo.deleteById(id);
    }

    // borra todas las asignaciones de un cliente
    public void deleteByCliente(Long idCliente) {
        repo.deleteAll(repo.findByIdCliente(idCliente));
    }

 
}