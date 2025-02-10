package com.springboot.ServicioGestorPaquete.service;

import com.springboot.ServicioGestorPaquete.model.Estado;
import com.springboot.ServicioGestorPaquete.repository.EstadoRepository;
import com.springboot.ServicioGestorPaquete.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class EstadoService implements CommandLineRunner {
    @Autowired
    private EstadoRepository estadoRepository;


    @Override
    public void run(String... args) throws Exception {
        // Si no hay estados en la base de datos, inicializarlos.
        if (estadoRepository.count() == 0) {
            Estado[] estados = {
                    new Estado("En espera de recolección"),
                    new Estado("Recolectado"),
                    new Estado("En centro de distribución"),
                    new Estado("Clasificado para despacho"),
                    new Estado("En camino a sucursal de destino"),
                    new Estado("En sucursal de destino"),
                    new Estado("En ruta de entrega"),
                    new Estado("Intento de entrega fallido"),
                    new Estado("Reprogramado para entrega"),
                    new Estado("En devolución a origen"),
                    new Estado("Entregado"),
                    new Estado("Extraviado/Dañado")
            };
            estadoRepository.saveAll(Arrays.asList(estados));
        }
    }

}
