package com.gym.system.controller;

import com.gym.system.model.Membresia;
import com.gym.system.service.MembresiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/membresias")
@CrossOrigin(origins = "*")
public class MembresiaController {

    private final MembresiaService service;

    @Autowired
    public MembresiaController(MembresiaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Membresia>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Membresia> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Filtrar membresías de un cliente específico
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Membresia>> getByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.findByCliente(idCliente));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Membresia>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.findByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<Membresia> create(@Valid @RequestBody Membresia m) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(m));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Membresia> update(@PathVariable Long id, @Valid @RequestBody Membresia m) {
        return ResponseEntity.ok(service.update(id, m));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
