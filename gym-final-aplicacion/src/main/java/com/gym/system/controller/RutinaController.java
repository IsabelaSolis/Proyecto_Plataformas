package com.gym.system.controller;

import com.gym.system.model.Rutina;
import com.gym.system.service.RutinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rutinas")
@CrossOrigin(origins = "*")
public class RutinaController {

    private final RutinaService service;

    @Autowired
    public RutinaController(RutinaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Rutina>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Rutina> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Rutina> create(@Valid @RequestBody Rutina r) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(r));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rutina> update(@PathVariable Long id, @Valid @RequestBody Rutina r) {
        return ResponseEntity.ok(service.update(id, r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
