package com.springboot.ServicioGestorPaquete.service;

import com.springboot.ServicioGestorPaquete.dto.TrackingDTO;

public interface ITrackingService {
    public String actualizar(Long numeroPaquete, TrackingDTO trackingDTO);
}
