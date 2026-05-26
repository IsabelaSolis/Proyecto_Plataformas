package com.gym.system.controller;

import com.gym.system.model.Entrenador;
import com.gym.system.service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entrenadores")
@CrossOrigin(origins = "*")
public class EntrenadorController {

    private final EntrenadorService service;

    @Autowired
    public EntrenadorController(EntrenadorService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Entrenador>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Entrenador>> getByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Entrenador> create(@Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entrenador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> update(@PathVariable Long id, @Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(service.update(id, entrenador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
