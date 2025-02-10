package com.springboot.ServicioGestorDeOrden.dto;

import lombok.Data;

import java.util.List;


@Data
public class PaqueteDTO {


    private Long numeroDeOrden;

    private String contenido;

    private String direccion;

    private Boolean fragil;

}
