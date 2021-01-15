package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, length = 20)
    private String nombreUsuario;

    @Column(length = 60)
    private String password;

    @Column
    private boolean estatus;

    private String nombre;
    private String apellido;

    @Column(unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})})
    private List<Rol> roles;
}
