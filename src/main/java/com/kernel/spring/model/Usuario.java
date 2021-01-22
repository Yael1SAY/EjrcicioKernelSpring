package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor //Crea constructor con todos los parametros
@NoArgsConstructor //Crea constructor vacio
public @Data class Usuario implements Serializable{ //@Data agrega getter, setter, toString

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", updatable = false, nullable = false)
    private long idUsuario;

    //@Email(message = "Introdusca direccion de correo correcta")
    @NotBlank(message = "No se Permite campo CORREO vacio")
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    //@Pattern(regexp = ".*[a-zA-Z]", message = "Solo se permiten Letras en campo Nombre")
    @NotBlank(message = "No se permiten campos NOMBRE vacios")
    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @NotBlank(message = "No se permiten campos APELLIDO vacios")
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "curp", nullable = false, length = 18, unique = true)
    private String curp;


    //@Pattern(regexp="(^$|[0-9]{3})", message = "Solo se permiten digitos")
    @Column(name = "edad", nullable = false, length = 3)
    private int edad;

    //@NotBlank(message = "No se permiten campos CONTRASEÑA vacios")
    //@Size(min = 4, max = 12, message = "Minimo 4 caracteres para la contraseña")
    @Column(name = "password", length = 100)
    private String password;

    @Column
    private boolean estatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name="id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "id_rol"})})
    private List<Rol> roles;

}