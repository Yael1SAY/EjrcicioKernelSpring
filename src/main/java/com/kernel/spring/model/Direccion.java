package com.kernel.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "Direcciones")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDireccion", updatable = false, nullable = false)
    private long idDireccion;

    @Column(name = "calle")
    private String calle;

    @Column(name = "noExteriro")
    private int noExterior;

    @Column(name = "codPostal")
    private int codPostal;

    @Column(name = "estado")
    private String estado;

    @Column(name = "referencia")
    private String referencia;

    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    public Direccion() {
    }

    public Direccion(long idDireccion, String calle, int noExterior, int codPostal, String estado, String referencia, Cliente cliente) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.noExterior = noExterior;
        this.codPostal = codPostal;
        this.estado = estado;
        this.referencia = referencia;
        this.cliente = cliente;
    }

    public long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNoExterior() {
        return noExterior;
    }

    public void setNoExterior(int noExterior) {
        this.noExterior = noExterior;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
