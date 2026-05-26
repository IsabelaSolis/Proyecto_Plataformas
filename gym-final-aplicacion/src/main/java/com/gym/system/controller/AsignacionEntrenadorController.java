package com.gym.system.controller;

import com.gym.system.model.AsignacionEntrenador;
import com.gym.system.service.AsignacionEntrenadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/asignaciones")
@CrossOrigin(origins = "*")
public class AsignacionEntrenadorController {

    private final AsignacionEntrenadorService service;

    @Autowired
    public AsignacionEntrenadorController(AsignacionEntrenadorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AsignacionEntrenador>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<AsignacionEntrenador>> getByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.findByCliente(idCliente));
    }

    @GetMapping("/entrenador/{idEntrenador}")
    public ResponseEntity<List<AsignacionEntrenador>> getByEntrenador(@PathVariable Long idEntrenador) {
        return ResponseEntity.ok(service.findByEntrenador(idEntrenador));
    }

    @PostMapping
    public ResponseEntity<AsignacionEntrenador> create(@Valid @RequestBody AsignacionEntrenador asignacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(asignacion));
    }

    @DeleteMapping("/{idCliente}/{idEntrenador}")
    public ResponseEntity<Void> delete(
            @PathVariable Long idCliente,
            @PathVariable Long idEntrenador) {
        try {
            service.delete(idCliente, idEntrenador);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
