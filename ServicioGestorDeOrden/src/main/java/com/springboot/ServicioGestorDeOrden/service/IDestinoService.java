package com.springboot.ServicioGestorDeOrden.service;

import com.springboot.ServicioGestorDeOrden.dto.DestinoDTO;
import com.springboot.ServicioGestorDeOrden.model.Destino;

public interface IDestinoService {
    public Destino guardarDestino(DestinoDTO destinoDTO);
}
