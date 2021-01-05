package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "clientes")
@AllArgsConstructor //Crea constructor con todos los parametros
@NoArgsConstructor //Crea constructor vacio
public @Data class Cliente { //@Data agrega getter, setter, toString

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", updatable = false, nullable = false)
    private long idCliente;

    @NotBlank(message = "No se permiten campos NOMBRE vacios")
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @NotBlank(message = "No se permiten campos APELLIDO vacios")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @NotBlank(message = "No se permiten campos CONTRASEÑA vacios")
    @Size(min = 4, max = 12, message = "Minimo 4 caracteres para la contraseña")
    @Column(name = "contrasenia", nullable = false, length = 12)
    private String contrasenia;

}
