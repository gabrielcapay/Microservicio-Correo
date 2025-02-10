package com.springboot.ServicioGestorDeOrden.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Entity
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_Cliente;
    private String cliente_CUIL_CUIT ;
    private String cliente_RazonSocial;
    private String cliente_telefono;
    private String cliente_Correo;
    @OneToMany(mappedBy = "orden_cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orden> ordenes;

}
