package com.springboot.ServicioGestorDeOrden.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Setter @Getter
public class OrdenDTO {
    private Long numeroDeOrden;
    private boolean validacion;
    private int cantidadDePaquetes;
    private ClienteDTO cliente;
    private DestinoDTO destino;
    private LocalDateTime fechadecreacion;
    private LocalDateTime fechadeconfirmacion;
}
