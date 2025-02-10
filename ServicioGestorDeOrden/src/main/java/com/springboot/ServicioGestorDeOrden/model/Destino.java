package com.springboot.ServicioGestorDeOrden.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_Destino;
    private String  destino_CUITCUIL;
    private String destino_Destinatario;
    private int destino_CodigoPostal;
    private String destino_Dirreccion;

}
