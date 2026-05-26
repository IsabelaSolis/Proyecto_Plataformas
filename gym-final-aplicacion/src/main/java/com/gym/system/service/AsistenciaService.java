package com.gym.system.service;

import com.gym.system.model.Asistencia;
import com.gym.system.repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AsistenciaService {

    private final AsistenciaRepository repo;

    @Autowired
    public AsistenciaService(AsistenciaRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<Asistencia> findAll() { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<Asistencia> findById(Long id) { return repo.findById(id); }
    @Transactional(readOnly = true) public List<Asistencia> findByCliente(Long idCliente) { return repo.findByIdCliente(idCliente); }
    @Transactional(readOnly = true) public List<Asistencia> findByFecha(LocalDate fecha) { return repo.findByFechaAsistencia(fecha); }
    @Transactional(readOnly = true) public List<Asistencia> findByClienteYRango(Long idCliente, LocalDate inicio, LocalDate fin) { return repo.findByIdClienteAndFechaAsistenciaBetween(idCliente, inicio, fin); }
    @Transactional(readOnly = true) public Long contarAsistencias(Long idCliente, LocalDate inicio, LocalDate fin) { return repo.countAsistenciasByClienteAndPeriodo(idCliente, inicio, fin); }

    public Asistencia save(Asistencia a) {
        if (a.getHoraSalida().isBefore(a.getHoraEntrada()))
            throw new IllegalArgumentException("La hora de salida no puede ser anterior a la hora de entrada");
        return repo.save(a);
    }

    public Asistencia update(Long id, Asistencia datos) {
        Asistencia e = repo.findById(id).orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + id));
        e.setIdCliente(datos.getIdCliente());
        e.setFechaAsistencia(datos.getFechaAsistencia());
        e.setHoraEntrada(datos.getHoraEntrada());
        e.setHoraSalida(datos.getHoraSalida());
        return repo.save(e);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Asistencia no encontrada con id: " + id);
        repo.deleteById(id);
    }
}
