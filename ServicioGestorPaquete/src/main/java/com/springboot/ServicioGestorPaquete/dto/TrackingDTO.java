package com.springboot.ServicioGestorPaquete.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TrackingDTO {

    private Long codigoTracking;
    private LocalDateTime FechaDeRegistro;
    private String status;

}
