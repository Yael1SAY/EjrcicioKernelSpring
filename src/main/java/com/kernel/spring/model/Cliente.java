package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity
@Table(name = "clientes")
@AllArgsConstructor //Crea constructor con todos los parametros
@NoArgsConstructor //Crea constructor vacio
public @Data class Cliente implements Serializable{ //@Data agrega getter, setter, toString

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", updatable = false, nullable = false)
    private long idCliente;

    @Email(message = "Introdusca direccion de correo correcta")
    @NotBlank(message = "No se Permite campo CORREO vacio")
    @Column(name = "correo", nullable = false, length = 50, unique = true)
    private String correo;

    @Pattern(regexp = ".*[a-zA-Z]", message = "Solo se permiten Letras en campo Nombre")
    @NotBlank(message = "No se permiten campos NOMBRE vacios")
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @NotBlank(message = "No se permiten campos APELLIDO vacios")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;


    //@Pattern(regexp="(^$|[0-9]{3})", message = "Solo se permiten digitos")
    @Column(name = "edad", nullable = false, length = 3)
    private int edad;

    //@NotBlank(message = "No se permiten campos CONTRASEÑA vacios")
    //@Size(min = 4, max = 12, message = "Minimo 4 caracteres para la contraseña")
    @Column(name = "contrasenia", length = 12)
    private String contrasenia;

}
