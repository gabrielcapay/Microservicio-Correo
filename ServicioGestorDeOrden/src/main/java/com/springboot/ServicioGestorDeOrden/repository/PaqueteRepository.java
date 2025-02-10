package com.springboot.ServicioGestorDeOrden.repository;

import com.springboot.ServicioGestorDeOrden.dto.PaqueteDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ServicioGestorPaquete")
public interface PaqueteRepository {

    @PostMapping("/api/v1/paquete")
    public void registrarPaquete(@RequestBody PaqueteDTO paquete);
}
