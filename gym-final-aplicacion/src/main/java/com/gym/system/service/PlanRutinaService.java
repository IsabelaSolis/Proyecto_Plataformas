package com.gym.system.service;

import com.gym.system.model.PlanRutina;
import com.gym.system.repository.PlanRutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlanRutinaService {

    private final PlanRutinaRepository repo;

    @Autowired
    public PlanRutinaService(PlanRutinaRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<PlanRutina> findAll() { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<PlanRutina> findById(Long id) { return repo.findById(id); }
    @Transactional(readOnly = true) public List<PlanRutina> findByRutina(Long id) { return repo.findByIdRutina(id); }
    @Transactional(readOnly = true) public List<PlanRutina> findByNivel(Long id) { return repo.findByIdNivel(id); }
    @Transactional(readOnly = true) public List<PlanRutina> findByObjetivo(Long id) { return repo.findByIdObjetivo(id); }

    public PlanRutina save(PlanRutina p) { return repo.save(p); }

    public PlanRutina update(Long id, PlanRutina datos) {
        PlanRutina p = repo.findById(id).orElseThrow(() -> new RuntimeException("Plan de rutina no encontrado con id: " + id));
        p.setIdRutina(datos.getIdRutina());
        p.setIdNivel(datos.getIdNivel());
        p.setIdObjetivo(datos.getIdObjetivo());
        p.setDuracionDias(datos.getDuracionDias());
        return repo.save(p);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Plan de rutina no encontrado con id: " + id);
        repo.deleteById(id);
    }
}
