package com.gym.system.service;

import com.gym.system.model.Cliente;
import com.gym.system.repository.ClienteRepository;
import com.gym.system.repository.AsignacionEntrenadorRepository;
import com.gym.system.repository.PagoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository repo;
    private final AsignacionEntrenadorRepository asignacionRepo;
    private final PagoRepository pagoRepo;

    @Autowired
    public ClienteService(
            ClienteRepository repo,
            AsignacionEntrenadorRepository asignacionRepo,
            PagoRepository pagoRepo
    ) {
        this.repo = repo;
        this.asignacionRepo = asignacionRepo;
        this.pagoRepo = pagoRepo;
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id) {
        return repo.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByApellido(String apellido) {
        return repo.findByApellido(apellido);
    }

    @Transactional(readOnly = true)
    public Cliente findByCorreo(String correo) {
        return repo.findByCorreo(correo);
    }

    public Cliente save(Cliente cliente) {
        if (repo.findByCorreo(cliente.getCorreo()) != null)
            throw new RuntimeException("Ya existe un cliente con ese correo");
        return repo.save(cliente);
    }

    public Cliente update(Long id, Cliente datos) {
        Cliente e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        e.setNombre(datos.getNombre());
        e.setApellido(datos.getApellido());
        e.setTelefono(datos.getTelefono());
        e.setCorreo(datos.getCorreo());
        e.setDomicilio(datos.getDomicilio());
        e.setSexo(datos.getSexo());

        return repo.save(e);
    }

    public void delete(Long id) {

        if (!repo.existsById(id))
            throw new RuntimeException("Cliente no encontrado con id: " + id);

  
        asignacionRepo.deleteAll(asignacionRepo.findByIdCliente(id));
        pagoRepo.deleteAll(pagoRepo.findByIdCliente(id));

        repo.deleteById(id);
    }
}