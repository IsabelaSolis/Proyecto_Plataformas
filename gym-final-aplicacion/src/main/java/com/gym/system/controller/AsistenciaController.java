package com.gym.system.controller;

import com.gym.system.model.Asistencia;
import com.gym.system.service.AsistenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    private final AsistenciaService service;

    @Autowired
    public AsistenciaController(AsistenciaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Asistencia>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Asistencia>> getByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.findByCliente(idCliente));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Asistencia>> getByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(service.findByFecha(fecha));
    }

    @GetMapping("/cliente/{idCliente}/rango")
    public ResponseEntity<List<Asistencia>> getByClienteYRango(
            @PathVariable Long idCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(service.findByClienteYRango(idCliente, inicio, fin));
    }

    @GetMapping("/cliente/{idCliente}/count")
    public ResponseEntity<Long> countByClienteYPeriodo(
            @PathVariable Long idCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(service.contarAsistencias(idCliente, inicio, fin));
    }

    @PostMapping
    public ResponseEntity<Asistencia> create(@Valid @RequestBody Asistencia asistencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(asistencia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> update(@PathVariable Long id, @Valid @RequestBody Asistencia asistencia) {
        return ResponseEntity.ok(service.update(id, asistencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
