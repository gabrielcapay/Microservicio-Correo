package com.springboot.ServicioGestorDeOrden.controller;

import com.springboot.ServicioGestorDeOrden.dto.ClienteDTO;
import com.springboot.ServicioGestorDeOrden.service.IClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;


    @PostMapping("api/v1/cliente")
    public ClienteDTO registrarCliente(@RequestBody ClienteDTO clienteDTOParam){

        return iClienteService.registrarCliente(clienteDTOParam);
    }

    @GetMapping("api/v1/cliente/{cuitCuilParam}")
    public ResponseEntity<?> buscarCliente(@PathVariable String cuitCuilParam){

        try {
            return new ResponseEntity<>(iClienteService.buscarCliente(cuitCuilParam), HttpStatus.OK);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
