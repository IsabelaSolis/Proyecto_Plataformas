package com.gym.system.service;

import com.gym.system.model.Membresia;
import com.gym.system.repository.MembresiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MembresiaService {

    private final MembresiaRepository repo;

    @Autowired
    public MembresiaService(MembresiaRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<Membresia> findAll()                      { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<Membresia> findById(Long id)          { return repo.findById(id); }
    @Transactional(readOnly = true) public List<Membresia> findByCliente(Long idCliente)  { return repo.findByIdCliente(idCliente); }
    @Transactional(readOnly = true) public List<Membresia> findByEstado(String estado)    { return repo.findByEstado(estado); }

    public Membresia save(Membresia m) { return repo.save(m); }

    public Membresia update(Long id, Membresia datos) {
        Membresia m = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Membresia no encontrada con id: " + id));
        m.setIdCliente(datos.getIdCliente());
        m.setTipoMembresia(datos.getTipoMembresia());
        m.setFechaInicio(datos.getFechaInicio());
        m.setFechaFin(datos.getFechaFin());
        m.setEstado(datos.getEstado());
        return repo.save(m);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Membresia no encontrada con id: " + id);
        repo.deleteById(id);
    }
}
