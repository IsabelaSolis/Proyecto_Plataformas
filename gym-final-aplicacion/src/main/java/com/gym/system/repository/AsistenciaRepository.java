package com.gym.system.repository;

import com.gym.system.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    List<Asistencia> findByIdCliente(Long idCliente);
    List<Asistencia> findByFechaAsistencia(LocalDate fecha);
    List<Asistencia> findByIdClienteAndFechaAsistenciaBetween(Long idCliente, LocalDate inicio, LocalDate fin);

    @Query("SELECT COUNT(a) FROM Asistencia a WHERE a.idCliente = :idCliente AND a.fechaAsistencia BETWEEN :inicio AND :fin")
    Long countAsistenciasByClienteAndPeriodo(@Param("idCliente") Long idCliente,
                                              @Param("inicio") LocalDate inicio,
                                              @Param("fin") LocalDate fin);
}
