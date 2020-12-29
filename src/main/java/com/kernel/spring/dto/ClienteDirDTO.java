package com.kernel.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
public @Data class ClienteDirDTO {

    private String nombre;
    private String apellido;
    private String calle;
    private int noExterior;
    private int codPostal;
    private String estado;
    private String referencia;

}
