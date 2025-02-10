package com.springboot.ServicioGestorPaquete.service;

import com.springboot.ServicioGestorPaquete.dto.TrackingDTO;
import com.springboot.ServicioGestorPaquete.model.Estado;
import com.springboot.ServicioGestorPaquete.model.Paquete;
import com.springboot.ServicioGestorPaquete.model.Tracking;
import com.springboot.ServicioGestorPaquete.repository.EstadoRepository;
import com.springboot.ServicioGestorPaquete.repository.PaqueteRepository;
import com.springboot.ServicioGestorPaquete.repository.TrackingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrackingServer implements ITrackingService{
    @Autowired
    private TrackingRepository trackingRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private PaqueteRepository paqueteRepository;
    @Override
    public String actualizar(Long numeroPaquete, TrackingDTO trackingDTO) {
        if(paqueteRepository.findById(numeroPaquete).orElse(null) == null){
            throw new EntityNotFoundException("No existe el paquete con codigo paquete: " + numeroPaquete );
        }
        Tracking trackingRespuesta = new Tracking();
        Estado estadotracking = estadoRepository.findAll()
                .stream()
                .filter(e -> trackingDTO.getStatus().equals(e.getDescripcion()))
                .findFirst().orElse(null);
        if(estadotracking == null){
            throw new EntityNotFoundException("No existe un estado con nombre  " + trackingDTO.getStatus() );
        }
        trackingRespuesta.setPaquete(paqueteRepository.findById(numeroPaquete).orElse(null));
        trackingRespuesta.setTracking_Estado(estadotracking);
        trackingRespuesta.setFechaDeRegistro(LocalDateTime.now());

        this.trackingRepository.save(trackingRespuesta);
        return "El paquete " + numeroPaquete + " fue modificado a" +trackingDTO.getStatus();
    }
    public List<Tracking> getTracking(){
        return this.trackingRepository.findAll();
    }
}
