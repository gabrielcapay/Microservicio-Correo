package com.springboot.ServicioGestorPaquete.controller;

import com.springboot.ServicioGestorPaquete.dto.TrackingDTO;
import com.springboot.ServicioGestorPaquete.service.ITrackingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tracking")
public class TrackingController {
    @Autowired
    private ITrackingService trackingService;

    @PostMapping
    public ResponseEntity<?> actualizarTracking(@RequestParam Long numeroPaquete, @RequestBody TrackingDTO trackingDTO){
        try {
            return new ResponseEntity<>(this.trackingService.actualizar(numeroPaquete,trackingDTO), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }




    }
}
