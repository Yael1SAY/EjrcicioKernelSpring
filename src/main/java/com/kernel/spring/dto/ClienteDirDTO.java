package com.kernel.spring.dto;

public class ClienteDirDTO {

    private long idCliente;
    private String nombre;
    private String apellido;
    private long idDireccion;
    private String calle;
    private int noExterior;
    private int codPostal;
    private String estado;
    private String referencia;

    public ClienteDirDTO() {
    }

    public ClienteDirDTO(long idCliente, String nombre, String apellido, long idDireccion,String calle, int noExterior, int codPostal, String estado, String referencia) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.noExterior = noExterior;
        this.codPostal = codPostal;
        this.estado = estado;
        this.referencia = referencia;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(long idDireccion) {
        this.idDireccion = idDireccion;
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

    @Override
    public String toString() {
        return "ClienteDirDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", calle='" + calle + '\'' +
                ", noExterior=" + noExterior +
                ", codPostal=" + codPostal +
                ", estado='" + estado + '\'' +
                ", referencia='" + referencia + '\'' +
                '}';
    }
}
