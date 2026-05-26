package com.gym.system.controller;

import com.gym.system.model.PlanRutina;
import com.gym.system.service.PlanRutinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/planes-rutina")
@CrossOrigin(origins = "*")
public class PlanRutinaController {

    private final PlanRutinaService service;

    @Autowired
    public PlanRutinaController(PlanRutinaService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<PlanRutina>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<PlanRutina> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/rutina/{idRutina}")
    public ResponseEntity<List<PlanRutina>> getByRutina(@PathVariable Long idRutina) {
        return ResponseEntity.ok(service.findByRutina(idRutina));
    }

    @GetMapping("/nivel/{idNivel}")
    public ResponseEntity<List<PlanRutina>> getByNivel(@PathVariable Long idNivel) {
        return ResponseEntity.ok(service.findByNivel(idNivel));
    }

    @GetMapping("/objetivo/{idObjetivo}")
    public ResponseEntity<List<PlanRutina>> getByObjetivo(@PathVariable Long idObjetivo) {
        return ResponseEntity.ok(service.findByObjetivo(idObjetivo));
    }

    @PostMapping
    public ResponseEntity<PlanRutina> create(@Valid @RequestBody PlanRutina p) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanRutina> update(@PathVariable Long id, @Valid @RequestBody PlanRutina p) {
        return ResponseEntity.ok(service.update(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
