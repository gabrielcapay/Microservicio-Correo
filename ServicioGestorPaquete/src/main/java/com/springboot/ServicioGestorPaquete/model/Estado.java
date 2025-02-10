package com.springboot.ServicioGestorPaquete.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estado_ID")
    private Long estado_ID;
    @Column(name = "estado_Descripcion")
    private String descripcion;

    public Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado() {

    }
}
