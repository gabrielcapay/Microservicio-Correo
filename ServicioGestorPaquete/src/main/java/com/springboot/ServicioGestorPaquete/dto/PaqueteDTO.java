package com.springboot.ServicioGestorPaquete.dto;

import lombok.Data;

import java.util.List;


@Data
public class PaqueteDTO {


    private Long numeroDeOrden;

    private String contenido;

    private String direccion;

    private Boolean fragil;

    private List<TrackingDTO> seguimiento;

}
