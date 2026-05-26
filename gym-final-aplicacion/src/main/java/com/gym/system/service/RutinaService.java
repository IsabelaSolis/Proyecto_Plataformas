package com.gym.system.service;

import com.gym.system.model.Rutina;
import com.gym.system.repository.RutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RutinaService {

    private final RutinaRepository repo;

    @Autowired
    public RutinaService(RutinaRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<Rutina> findAll() { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<Rutina> findById(Long id) { return repo.findById(id); }

    public Rutina save(Rutina r) { return repo.save(r); }

    public Rutina update(Long id, Rutina datos) {
        Rutina r = repo.findById(id).orElseThrow(() -> new RuntimeException("Rutina no encontrada con id: " + id));
        r.setNombreRutina(datos.getNombreRutina());
        return repo.save(r);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Rutina no encontrada con id: " + id);
        repo.deleteById(id);
    }
}
