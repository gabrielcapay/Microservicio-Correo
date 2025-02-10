package com.springboot.ServicioGestorDeOrden.dto;

import lombok.Data;

import java.util.List;

@Data
public class DatosConfirmacionDTO {
    private Long numeroDeOrden;
    private Long codigoDeConfirmacion;
    private List<PaqueteDTO> paquetes;
}
