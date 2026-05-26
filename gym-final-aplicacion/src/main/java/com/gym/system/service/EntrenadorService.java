package com.gym.system.service;

import com.gym.system.model.Entrenador;
import com.gym.system.repository.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntrenadorService {

    private final EntrenadorRepository repo;

    @Autowired
    public EntrenadorService(EntrenadorRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<Entrenador> findAll() { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<Entrenador> findById(Long id) { return repo.findById(id); }
    @Transactional(readOnly = true) public List<Entrenador> findByNombre(String nombre) { return repo.findByNombre(nombre); }
    @Transactional(readOnly = true) public List<Entrenador> findByApellido(String apellido) { return repo.findByApellido(apellido); }
    @Transactional(readOnly = true) public List<Entrenador> findByEspecialidad(String e) { return repo.findByEspecialidad(e); }
    @Transactional(readOnly = true) public List<Entrenador> findByEstado(String estado) { return repo.findByEstado(estado); }

    public Entrenador save(Entrenador e) { return repo.save(e); }

    public Entrenador update(Long id, Entrenador datos) {
        Entrenador e = repo.findById(id).orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id: " + id));
        e.setNombre(datos.getNombre());
        e.setApellido(datos.getApellido());
        e.setFechaContratacion(datos.getFechaContratacion());
        e.setEspecialidad(datos.getEspecialidad());
        e.setEstado(datos.getEstado());
        return repo.save(e);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Entrenador no encontrado con id: " + id);
        repo.deleteById(id);
    }
}
