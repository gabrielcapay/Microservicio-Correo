package com.springboot.ServicioGestorPaquete.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long estado_ID;

    private String descripcion;

    public Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado() {

    }
}
