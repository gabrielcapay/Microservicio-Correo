package com.springboot.ServicioGestorDeOrden.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdenClienteDTO {
    private Long numeroDeOrden;
    private boolean validacion;
    private int cantidadDePaquetes;
    private LocalDateTime fechadecreacion;
    private LocalDateTime fechadeconfirmacion;
}
