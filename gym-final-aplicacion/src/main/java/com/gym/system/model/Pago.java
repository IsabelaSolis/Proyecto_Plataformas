package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "PAGOS")
public class Pago {

    @Id
    @Column(name = "ID_PAGO")
    private Long idPago;

    @Column(name = "ID_CLIENTE", nullable = false)
    @NotNull(message = "El id_cliente es obligatorio")
    private Long idCliente;

    @Column(name = "METODO_PAGO", nullable = false, length = 30)
    @NotBlank(message = "El método de pago es obligatorio")
    @Size(max = 30)
    private String metodoPago;

    @Column(name = "MONTO", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
    private BigDecimal monto;

    @Column(name = "FECHA_PAGO", nullable = false)
    @NotNull(message = "La fecha de pago es obligatoria")
    private LocalDate fechaPago;

    public Pago() {}

    public Pago(Long idPago, Long idCliente, String metodoPago, BigDecimal monto, LocalDate fechaPago) {
        this.idPago = idPago;
        this.idCliente = idCliente;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
    }

    public Long getIdPago() { return idPago; }
    public void setIdPago(Long idPago) { this.idPago = idPago; }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public LocalDate getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDate fechaPago) { this.fechaPago = fechaPago; }
}
