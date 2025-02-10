package com.springboot.ServicioGestorPaquete.service;

import com.springboot.ServicioGestorPaquete.dto.PaqueteDTO;
import com.springboot.ServicioGestorPaquete.dto.TrackingDTO;
import com.springboot.ServicioGestorPaquete.model.Paquete;
import com.springboot.ServicioGestorPaquete.model.Tracking;
import com.springboot.ServicioGestorPaquete.repository.EstadoRepository;
import com.springboot.ServicioGestorPaquete.repository.PaqueteRepository;
import com.springboot.ServicioGestorPaquete.repository.TrackingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PaqueteService implements IPaqueteService{
    @Autowired
    private PaqueteRepository paqueteRepository;

    @Autowired
    private TrackingRepository trackingRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<PaqueteDTO> buscarPaquetesPorOrden(Long numeroDeOrden) {
        List<PaqueteDTO> paquetesByOrden = new ArrayList<>();

        for(Paquete paquete : this.paqueteRepository.buscarPorNumeroDeOrden(numeroDeOrden)){
            PaqueteDTO paqueteDTO = new PaqueteDTO();
            paqueteDTO.setContenido(paquete.getContenido());
            paqueteDTO.setNumeroDeOrden(paquete.getNumeroDeOrden());
            paqueteDTO.setDireccion(paquete.getDireccion());
            paqueteDTO.setFragil(paquete.getFragil());

            List<TrackingDTO> trackingDTOs = new ArrayList<>();
            for(Tracking tracking :paquete.getSeguimiento()){
                TrackingDTO trackingDTO = new TrackingDTO();
                trackingDTO.setCodigoTracking(tracking.getIdTracking());
                trackingDTO.setFechaDeRegistro(tracking.getFechaDeRegistro());
                trackingDTO.setStatus(tracking.getTracking_Estado().getDescripcion());

                trackingDTOs.add(trackingDTO);
            }
            paqueteDTO.setSeguimiento(trackingDTOs);

            paquetesByOrden.add(paqueteDTO);

        }



        return paquetesByOrden;
    }
    @Override
    public List<PaqueteDTO> buscarPaquetesPorOrdenUltimoEstado(Long numeroDeOrden) {
        List<Paquete> paquetesDeLaOrden = this.paqueteRepository.buscarPorNumeroDeOrden(numeroDeOrden);

        if (paquetesDeLaOrden.isEmpty()) {
            throw new EntityNotFoundException("El numero de orden: " + numeroDeOrden + " no existe y/o no tiene paquetes cargados.");
        }

        List<PaqueteDTO> paqueteDTOSList = new ArrayList<>();


        for (Paquete paquete : paquetesDeLaOrden){
            PaqueteDTO paqueteDTO= new PaqueteDTO();

            paqueteDTO.setContenido(paquete.getContenido());
            paqueteDTO.setDireccion(paquete.getDireccion());
            paqueteDTO.setFragil(paquete.getFragil());
            paqueteDTO.setNumeroDeOrden(paquete.getNumeroDeOrden());

            Tracking ultimoStatus = paquete.getSeguimiento().stream()
                    .sorted(Comparator.comparing(Tracking::getFechaDeRegistro).reversed())
                    .findFirst()
                    .orElse(null);

            TrackingDTO trackingDTO = new TrackingDTO();
            trackingDTO.setStatus(ultimoStatus.getTracking_Estado().getDescripcion());
            trackingDTO.setFechaDeRegistro(ultimoStatus.getFechaDeRegistro());
            trackingDTO.setCodigoTracking(ultimoStatus.getIdTracking());

            List<TrackingDTO> trackingDTOS = new ArrayList<>();
            trackingDTOS.add(trackingDTO);

            paqueteDTO.setSeguimiento(trackingDTOS);

            paqueteDTOSList.add(paqueteDTO);
        }

        return paqueteDTOSList;
    }

    @Override
    public PaqueteDTO buscarPaqueteUltimo(Long codigoPaquete) {
        Paquete paquete = this.paqueteRepository.findById(codigoPaquete).orElse(null);

        if (paquete == null) {
            throw new EntityNotFoundException("No existe el paquete con codigo paquete: " + codigoPaquete );
        }


        PaqueteDTO paqueteDTORESPONSE = new PaqueteDTO();

        paqueteDTORESPONSE.setContenido(paquete.getContenido());
        paqueteDTORESPONSE.setDireccion(paquete.getDireccion());
        paqueteDTORESPONSE.setFragil(paquete.getFragil());
        paqueteDTORESPONSE.setNumeroDeOrden(paquete.getNumeroDeOrden());



        Tracking ultimoStatus = paquete.getSeguimiento().stream()
                .sorted(Comparator.comparing(Tracking::getFechaDeRegistro).reversed())
                .findFirst()
                .orElse(null);

        TrackingDTO trackingDTO = new TrackingDTO();
        trackingDTO.setStatus(ultimoStatus.getTracking_Estado().getDescripcion());
        trackingDTO.setFechaDeRegistro(ultimoStatus.getFechaDeRegistro());
        trackingDTO.setCodigoTracking(ultimoStatus.getIdTracking());


        List<TrackingDTO> trackingDTOS = new ArrayList<>();
        trackingDTOS.add(trackingDTO);
        paqueteDTORESPONSE.setSeguimiento(trackingDTOS);
        return paqueteDTORESPONSE;
    }

    @Override
    public PaqueteDTO buscarPaquete(Long codigoPaqueteParam) {
        Paquete paquete= this.paqueteRepository.findById(codigoPaqueteParam).orElse(null);

        if (paquete == null) {
            throw new EntityNotFoundException("No existe el paquete con codigo paquete: " + codigoPaqueteParam );
        }

        PaqueteDTO paqueteDTO = new PaqueteDTO();
        paqueteDTO.setContenido(paquete.getContenido());
        paqueteDTO.setDireccion(paquete.getDireccion());
        paqueteDTO.setNumeroDeOrden(paquete.getNumeroDeOrden());
        paqueteDTO.setFragil(paquete.getFragil());

        List<TrackingDTO> trackingDTOList = new ArrayList<>();

        for(Tracking tracking : paquete.getSeguimiento()){
            TrackingDTO trackingDTO = new TrackingDTO();

            trackingDTO.setCodigoTracking(tracking.getIdTracking());
            trackingDTO.setStatus(tracking.getTracking_Estado().getDescripcion());
            trackingDTO.setFechaDeRegistro(tracking.getFechaDeRegistro());

            trackingDTOList.add(trackingDTO);
        }
        paqueteDTO.setSeguimiento(trackingDTOList);

        return paqueteDTO;


    }

    @Override

    public void registrarPaquete(PaqueteDTO paqueteParam) {
        Paquete paqueteRespuesta =new Paquete();
        paqueteRespuesta.setNumeroDeOrden(paqueteParam.getNumeroDeOrden());
        paqueteRespuesta.setContenido(paqueteParam.getContenido());
        paqueteRespuesta.setDireccion(paqueteParam.getDireccion());
        paqueteRespuesta.setFragil(paqueteParam.getFragil());
        paqueteRespuesta.setFechaRegistro(LocalDateTime.now());
        paqueteRespuesta.setFechaDeEntrega(LocalDateTime.now().plusDays(3));

        Tracking tracking = new Tracking();
        tracking.setFechaDeRegistro(LocalDateTime.now());
        tracking.setPaquete(paqueteRespuesta);
        tracking.setTracking_Estado(estadoRepository.findById(1L).orElse(null));
        List<Tracking> seguimiento = new ArrayList<>();
        seguimiento.add(tracking);
        paqueteRespuesta.setSeguimiento(seguimiento);

        paqueteRepository.save(paqueteRespuesta);

    }


}
