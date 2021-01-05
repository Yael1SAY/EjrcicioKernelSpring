package com.kernel.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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

    @NotEmpty(message = "No se permite campo vacio")
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

    @OneToOne(cascade = CascadeType.ALL)//Se indica la realcion 1 a 1
    @JoinColumn(name = "id_cliente", updatable = false, nullable = false) //La union se realiza mediate el campo id_cliente
    @Valid
    private Cliente cliente;

}
