package com.gym.system.controller;

import com.gym.system.model.ClientePlanRutina;
import com.gym.system.service.ClientePlanRutinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cliente-plan-rutina")
@CrossOrigin(origins = "*")
public class ClientePlanRutinaController {

    private final ClientePlanRutinaService service;

    @Autowired
    public ClientePlanRutinaController(ClientePlanRutinaService service) { this.service = service; }

    // GET /api/cliente-plan-rutina  → todos
    @GetMapping
    public ResponseEntity<List<ClientePlanRutina>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET /api/cliente-plan-rutina/cliente/376827  → planes de un cliente
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ClientePlanRutina>> getByCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(service.findByCliente(idCliente));
    }

    // GET /api/cliente-plan-rutina/plan/1  → clientes de un plan
    @GetMapping("/plan/{idPlan}")
    public ResponseEntity<List<ClientePlanRutina>> getByPlan(@PathVariable Long idPlan) {
        return ResponseEntity.ok(service.findByPlan(idPlan));
    }

    // POST /api/cliente-plan-rutina  body: { "idCliente": 376827, "idPlan": 1 }
    @PostMapping
    public ResponseEntity<ClientePlanRutina> assign(@RequestBody Map<String, Long> body) {
        Long idCliente = body.get("idCliente");
        Long idPlan    = body.get("idPlan");
        if (idCliente == null || idPlan == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.assign(idCliente, idPlan));
    }

    // DELETE /api/cliente-plan-rutina/cliente/376827/plan/1
    @DeleteMapping("/cliente/{idCliente}/plan/{idPlan}")
    public ResponseEntity<Void> remove(@PathVariable Long idCliente, @PathVariable Long idPlan) {
        service.remove(idCliente, idPlan);
        return ResponseEntity.noContent().build();
    }
}
