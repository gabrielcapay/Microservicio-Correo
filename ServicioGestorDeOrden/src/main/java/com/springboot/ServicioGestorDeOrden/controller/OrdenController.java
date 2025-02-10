package com.springboot.ServicioGestorDeOrden.controller;

import com.springboot.ServicioGestorDeOrden.dto.DatosConfirmacionDTO;
import com.springboot.ServicioGestorDeOrden.dto.OrdenDTO;
import com.springboot.ServicioGestorDeOrden.service.IOrdenService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdenController {

    @Autowired
    private IOrdenService iOrdenService;

    @PostMapping("api/v1/orden")
    public ResponseEntity<?> registrarOrden(@RequestBody(required = false)  OrdenDTO ordenDTO){

        try {
            this.iOrdenService.guardarPreOrden(ordenDTO);
            return new ResponseEntity<>("Confirme la orden. Se ha enviado el codigo a su correo", HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("api/v1/orden/confirmacion")
    public ResponseEntity<?> confirmarOrden(@RequestBody(required = false) DatosConfirmacionDTO datosConfirmacionDTO){

        try {
            String mensaje = this.iOrdenService.confirmarOrden(datosConfirmacionDTO);
            //registrar los paquetes
            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("api/v1/orden")
    public ResponseEntity<List<OrdenDTO>> listarOrden(){
        return new ResponseEntity<List<OrdenDTO>>(this.iOrdenService.obtenerOrdenes(), HttpStatus.OK);
    }

    @GetMapping("api/v1/orden/{numeroDeOrden}")
    public ResponseEntity<OrdenDTO> buscarOrden(@PathVariable Long numeroDeOrden){
        return new ResponseEntity<OrdenDTO>(this.iOrdenService.obtenerOrden(numeroDeOrden), HttpStatus.OK);
    }

}
