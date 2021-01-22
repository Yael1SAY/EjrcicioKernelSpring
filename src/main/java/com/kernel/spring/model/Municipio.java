/*
package com.kernel.spring.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "municipios")
@Data
public class Municipio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio", updatable = false, nullable = false)
    private long idMunicipio;

    @Column(name = "nombre", updatable = false, nullable = false)
    private String nombre;

    @Column(name = "codigo_postal", updatable = false, nullable = false)
    private int codigoPostal;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "id_estado", updatable = false, nullable = false)
    //private Estado estado;
}
*/