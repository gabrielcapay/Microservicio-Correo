package com.springboot.ServicioGestorPaquete.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigoPaquete;
    @Column(name = "numeroDeOrden")
    private Long numeroDeOrden;
    @Column(name = "paquete_contenido")
    private String contenido;
    @Column(name = "paquete_direccion")
    private String direccion;
    @Column(name = "paquete_fragil")
    private Boolean fragil;
    @Column(name = "paquete_fechaRegistro")
    private LocalDateTime fechaRegistro;

    @Column(name = "paquete_fechaDeEntrega")
    private LocalDateTime fechaDeEntrega;

    @OneToMany(mappedBy = "paquete", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tracking> seguimiento ;

}
