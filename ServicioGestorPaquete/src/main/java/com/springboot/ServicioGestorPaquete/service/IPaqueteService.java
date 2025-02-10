package com.springboot.ServicioGestorPaquete.service;

import com.springboot.ServicioGestorPaquete.dto.PaqueteDTO;
import com.springboot.ServicioGestorPaquete.model.Paquete;

import java.util.List;

public interface IPaqueteService {
    List<PaqueteDTO> buscarPaquetesPorOrden(Long numeroDeOrden);

    PaqueteDTO buscarPaquete(Long codigoPaquete);

    List<PaqueteDTO> buscarPaquetesPorOrdenUltimoEstado(Long numeroDeOrden);

    PaqueteDTO buscarPaqueteUltimo(Long codigoPaquete);

    void registrarPaquete(PaqueteDTO paqueteParam);
}
