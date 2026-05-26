package com.gym.system.service;

import com.gym.system.model.ClientePlanRutina;
import com.gym.system.model.ClientePlanRutinaId;
import com.gym.system.repository.ClientePlanRutinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientePlanRutinaService {

    private final ClientePlanRutinaRepository repo;

    @Autowired
    public ClientePlanRutinaService(ClientePlanRutinaRepository repo) { this.repo = repo; }

    @Transactional(readOnly = true) public List<ClientePlanRutina> findAll()                          { return repo.findAll(); }
    @Transactional(readOnly = true) public List<ClientePlanRutina> findByCliente(Long idCliente)      { return repo.findByIdIdCliente(idCliente); }
    @Transactional(readOnly = true) public List<ClientePlanRutina> findByPlan(Long idPlan)            { return repo.findByIdIdPlan(idPlan); }

    public ClientePlanRutina assign(Long idCliente, Long idPlan) {
        ClientePlanRutinaId pk = new ClientePlanRutinaId(idCliente, idPlan);
        if (repo.existsById(pk)) throw new RuntimeException("El cliente ya tiene ese plan asignado.");
        return repo.save(new ClientePlanRutina(idCliente, idPlan));
    }

    public void remove(Long idCliente, Long idPlan) {
        ClientePlanRutinaId pk = new ClientePlanRutinaId(idCliente, idPlan);
        if (!repo.existsById(pk)) throw new RuntimeException("Asignacion no encontrada.");
        repo.deleteById(pk);
    }
}
