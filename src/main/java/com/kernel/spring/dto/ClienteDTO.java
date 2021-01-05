package com.kernel.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO implements Serializable{

    private String correo;
    private String nombre;
    private String apellido;
    private int edad;

}
