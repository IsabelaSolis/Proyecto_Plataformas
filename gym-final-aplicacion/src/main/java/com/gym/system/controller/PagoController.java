package com.gym.system.controller;

import com.gym.system.model.Pago;
import com.gym.system.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    private final PagoService service;

    @Autowired
    public PagoController(PagoService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<List<Pago>> getAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pago>> getByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.findByCliente(idCliente));
    }

    @GetMapping("/metodo/{metodoPago}")
    public ResponseEntity<List<Pago>> getByMetodo(@PathVariable String metodoPago) {
        return ResponseEntity.ok(service.findByMetodoPago(metodoPago));
    }

    @GetMapping("/rango")
    public ResponseEntity<List<Pago>> getByRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(service.findByRangoFechas(inicio, fin));
    }

    @GetMapping("/total/cliente/{idCliente}")
    public ResponseEntity<Double> getTotalPorCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.totalPagadoPorCliente(idCliente));
    }

    @PostMapping
    public ResponseEntity<Pago> create(@Valid @RequestBody Pago pago) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable Long id, @Valid @RequestBody Pago pago) {
        return ResponseEntity.ok(service.update(id, pago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
