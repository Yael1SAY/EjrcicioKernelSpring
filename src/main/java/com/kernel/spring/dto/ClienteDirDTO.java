package com.kernel.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class ClienteDirDTO {

    private long id;
    private String correo;
    private String nombre;
    private String apellido;
    private int edad;
    private String calle;
    private int noExterior;
    private int codPostal;
    private String estado;
    private String municipio;
    private String referencia;

}
