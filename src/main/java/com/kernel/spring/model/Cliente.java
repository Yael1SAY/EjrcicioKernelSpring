package com.kernel.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente", updatable = false, nullable = false)
    private long idCliente;

    //Si no se agrega la anotación @Column toma el nombre de la variable
    private String nombre;

    @Column(name = "apellido ")
    private String apellido;

    @JsonIgnore
    @Column(name = "contrasenia")
    private String contrasenia;

    public Cliente() {
    }

    public Cliente(long idCliente, String nombre, String apellido, String contrasenia) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}
