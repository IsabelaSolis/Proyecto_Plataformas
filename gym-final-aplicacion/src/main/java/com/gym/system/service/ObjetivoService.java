package com.gym.system.service;

import com.gym.system.model.Objetivo;
import com.gym.system.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObjetivoService {

    private final ObjetivoRepository repo;

    @Autowired
    public ObjetivoService(ObjetivoRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<Objetivo> findAll() { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<Objetivo> findById(Long id) { return repo.findById(id); }

    public Objetivo save(Objetivo o) { return repo.save(o); }

    public Objetivo update(Long id, Objetivo datos) {
        Objetivo o = repo.findById(id).orElseThrow(() -> new RuntimeException("Objetivo no encontrado con id: " + id));
        o.setObjetivo(datos.getObjetivo());
        return repo.save(o);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Objetivo no encontrado con id: " + id);
        repo.deleteById(id);
    }
}
