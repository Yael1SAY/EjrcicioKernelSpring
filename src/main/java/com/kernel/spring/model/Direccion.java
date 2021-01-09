package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity//indica que es una entidad
@Table(name = "direcciones") //indicar el nombre de la tabla
@AllArgsConstructor
@NoArgsConstructor
public @Data class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//se indica que sera autoincrmenteable
    @Column(name = "id_direccion", updatable = false, nullable = false)//se indica que es una columna
    private long idDireccion;

    @NotBlank(message = "No se permite campo CALLE vacio")
    @Column(name = "calle", nullable = false, length = 50)
    private String calle;

    //@NotNull(message = "No se permite campo vacio")
    //@Size(min = 1, max = 8, message = "Minimo 1 caracter")
    //@Pattern(regexp = ".*[0-9] +", message = "Solo se permiten digitos")
    @Column(name = "no_exterior", nullable = false, length = 8)
    private Integer noExterior;

    //@NotBlank(message = "No se permite campo vacio")
    //@Size(min = 1, max = 8, message = "Minimo 1 caracter")
    //@Pattern(regexp = ".*[0-9] +", message = "Solo se permiten digitos")
    @Column(name = "cod_postal", nullable = false, length = 8)
    private Integer codPostal;

    @NotBlank(message = "No se permite campo ESTADO vacio")
    @Column(name = "estado", nullable = false, length = 25)
    private String estado;

    @NotBlank(message = "No se permite campo MUNICIPIO vacio")
    @Column(name = "municipio", nullable = false, length = 20)
    private String municipio;

    @NotBlank(message = "No se permite campo REFERENCIA vacio")
    @Column(name = "referencia", nullable = false, length = 250)
    private String referencia;

    @OneToOne(cascade = CascadeType.ALL)//Se indica la realcion 1 a 1
    @JoinColumn(name = "id_cliente", updatable = false, nullable = false) //La union se realiza mediate el campo id_cliente
    @Valid
    private Cliente cliente;

}
