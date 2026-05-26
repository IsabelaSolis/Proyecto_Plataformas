package com.gym.system.controller;

import com.gym.system.model.Nivel;
import com.gym.system.service.NivelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/niveles")
@CrossOrigin(origins = "*")
public class NivelController {

    private final NivelService service;

    @Autowired
    public NivelController(NivelService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Nivel>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Nivel> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Nivel> create(@Valid @RequestBody Nivel n) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(n));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nivel> update(@PathVariable Long id, @Valid @RequestBody Nivel n) {
        return ResponseEntity.ok(service.update(id, n));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
