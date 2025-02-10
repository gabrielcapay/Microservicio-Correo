package com.springboot.ServicioGestorPaquete.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Tracking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idTracking;
    @Column(name = "tracking_FechaDeRegistro")
    private LocalDateTime FechaDeRegistro;
    @ManyToOne
    @JoinColumn(name = "paquete_id", nullable = false)
    private Paquete paquete;
    @ManyToOne
    @JoinColumn(name = "estado_ID", nullable = false)
    private Estado tracking_Estado;

}
