package com.gym.system.service;

import com.gym.system.model.Pago;
import com.gym.system.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagoService {

    private final PagoRepository repo;

    @Autowired
    public PagoService(PagoRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<Pago> findAll() { return repo.findAll(); }
    @Transactional(readOnly = true) public Optional<Pago> findById(Long id) { return repo.findById(id); }
    @Transactional(readOnly = true) public List<Pago> findByCliente(Long idCliente) { return repo.findByIdCliente(idCliente); }
    @Transactional(readOnly = true) public List<Pago> findByMetodoPago(String metodoPago) { return repo.findByMetodoPago(metodoPago); }
    @Transactional(readOnly = true) public List<Pago> findByRangoFechas(LocalDate inicio, LocalDate fin) { return repo.findByFechaPagoBetween(inicio, fin); }

    public Pago save(Pago p) { return repo.save(p); }

    public Pago update(Long id, Pago datos) {
        Pago p = repo.findById(id).orElseThrow(() -> new RuntimeException("Pago no encontrado con id: " + id));
        p.setIdCliente(datos.getIdCliente());
        p.setMetodoPago(datos.getMetodoPago());
        p.setMonto(datos.getMonto());
        p.setFechaPago(datos.getFechaPago());
        return repo.save(p);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) throw new RuntimeException("Pago no encontrado con id: " + id);
        repo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Double totalPagadoPorCliente(Long idCliente) {
        Double total = repo.sumMontoByIdCliente(idCliente);
        return total != null ? total : 0.0;
    }
}
