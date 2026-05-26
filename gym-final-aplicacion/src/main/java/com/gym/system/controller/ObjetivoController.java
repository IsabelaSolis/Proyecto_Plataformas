package com.gym.system.controller;

import com.gym.system.model.Objetivo;
import com.gym.system.service.ObjetivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/objetivos")
@CrossOrigin(origins = "*")
public class ObjetivoController {

    private final ObjetivoService service;

    @Autowired
    public ObjetivoController(ObjetivoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Objetivo>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Objetivo> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Objetivo> create(@Valid @RequestBody Objetivo o) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(o));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Objetivo> update(@PathVariable Long id, @Valid @RequestBody Objetivo o) {
        return ResponseEntity.ok(service.update(id, o));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
