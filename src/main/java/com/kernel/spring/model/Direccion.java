package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "direcciones")
@AllArgsConstructor
@NoArgsConstructor
public @Data class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion", updatable = false, nullable = false)
    private long idDireccion;

    @NotBlank(message = "No se permite campo CALLE vacio")
    @Column(name = "calle", nullable = false, length = 50)
    private String calle;

    //@NotEmpty(message = "No se permite campo vacio")
    //@Size(min = 1, max = 8, message = "Minimo 1 caracter")
    @Column(name = "no_exterior", nullable = false, length = 8)
    private int noExterior;

    //@NotBlank(message = "No se permite campo vacio")
    //@Size(min = 1, max = 8, message = "Minimo 1 caracter")
    @Column(name = "cod_postal", nullable = false, length = 8)
    private int codPostal;

    @NotBlank(message = "No se permite campo ESTADO vacio")
    @Column(name = "estado", nullable = false, length = 15)
    private String estado;

    @NotBlank(message = "No se permite campo REFERENCIA vacio")
    @Column(name = "referencia", nullable = false, length = 250)
    private String referencia;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", updatable = false, nullable = false)
    @Valid
    private Cliente cliente;

}
