package com.gym.system.repository;

import com.gym.system.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    List<Pago> findByIdCliente(Long idCliente);
    List<Pago> findByMetodoPago(String metodoPago);
    List<Pago> findByFechaPagoBetween(LocalDate inicio, LocalDate fin);

    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.idCliente = :idCliente")
    Double sumMontoByIdCliente(@Param("idCliente") Long idCliente);
}
