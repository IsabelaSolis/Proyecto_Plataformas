package com.gym.system.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "APELLIDO", nullable = false, length = 100)
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(name = "TELEFONO", nullable = false, length = 15)
    @NotBlank(message = "El teléfono es obligatorio")
    private String telefono;

    @Column(name = "EMAIL", nullable = false, length = 100)
    @Email(message = "Correo inválido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Column(name = "DOMICILIO", nullable = false, length = 200)
    @NotBlank(message = "La dirección es obligatoria")
    private String domicilio;

    @Column(name = "SEXO", nullable = false, length = 20)
    @NotBlank(message = "El género es obligatorio")
    private String sexo;

    public Cliente() {}

    public Cliente(Long idCliente, String nombre, String apellido,
                   String telefono, String correo, String domicilio, String sexo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.domicilio = domicilio;
        this.sexo = sexo;
    }

    public Long getIdCliente() { return idCliente; }
    public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
}
