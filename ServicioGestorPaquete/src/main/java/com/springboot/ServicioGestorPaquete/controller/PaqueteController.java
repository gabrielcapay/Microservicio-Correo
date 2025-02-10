package com.springboot.ServicioGestorPaquete.controller;

import com.springboot.ServicioGestorPaquete.dto.PaqueteDTO;
import com.springboot.ServicioGestorPaquete.model.Paquete;
import com.springboot.ServicioGestorPaquete.service.IPaqueteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/paquete")
public class PaqueteController {

    @Autowired
    private IPaqueteService iPaqueteService;

    @GetMapping
    public ResponseEntity<?> buscarPaquetesPorOrden(@RequestParam Long numeroDeOrden){

        try {
            return new ResponseEntity<>(this.iPaqueteService.buscarPaquetesPorOrden(numeroDeOrden), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/ultimoStatus")
    public ResponseEntity<?> buscarPaquetesPorOrdenUltimoEstado(@RequestParam Long numeroDeOrden){
        //Busca todos los paquetes con su ultimo estado del numeroDeOrden
        try {
            return new ResponseEntity<>(this.iPaqueteService.buscarPaquetesPorOrdenUltimoEstado(numeroDeOrden), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{codigoPaquete}")
    public ResponseEntity<?> buscarPaquete(@PathVariable Long codigoPaquete){
        try {
            return new ResponseEntity<>(this.iPaqueteService.buscarPaquete(codigoPaquete), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{codigoPaquete}/ultimoStatus")
    public ResponseEntity<?> buscarPaqueteUltimoStatus(@PathVariable Long codigoPaquete){

        try {
            return new ResponseEntity<>(this.iPaqueteService.buscarPaqueteUltimo(codigoPaquete), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public void registrarPaquete(@RequestBody PaqueteDTO paquete){
        this.iPaqueteService.registrarPaquete(paquete);
    }



}
