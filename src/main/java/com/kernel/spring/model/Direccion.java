package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "direcciones")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion", updatable = false, nullable = false)
    private long idDireccion;

    @Column(name = "calle")
    private String calle;

    @Column(name = "no_exterior")
    private int noExterior;

    @Column(name = "cod_postal")
    private int codPostal;

    @Column(name = "estado")
    private String estado;

    @Column(name = "referencia")
    private String referencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

}
