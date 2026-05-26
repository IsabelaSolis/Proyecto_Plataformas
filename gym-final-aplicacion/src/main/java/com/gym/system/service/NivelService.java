package com.gym.system.service;

import com.gym.system.model.Nivel;
import com.gym.system.repository.NivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NivelService {

    private final NivelRepository repo;

    @Autowired
    public NivelService(NivelRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<Nivel> findAll() { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<Nivel> findById(Long id) { return repo.findById(id); }

    public Nivel save(Nivel n) { return repo.save(n); }

    public Nivel update(Long id, Nivel datos) {
        Nivel n = repo.findById(id).orElseThrow(() -> new RuntimeException("Nivel no encontrado con id: " + id));
        n.setNivel(datos.getNivel());
        return repo.save(n);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Nivel no encontrado con id: " + id);
        repo.deleteById(id);
    }
}
