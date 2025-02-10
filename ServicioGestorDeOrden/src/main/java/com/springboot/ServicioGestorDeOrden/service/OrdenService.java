package com.springboot.ServicioGestorDeOrden.service;

import com.springboot.ServicioGestorDeOrden.dto.*;

import com.springboot.ServicioGestorDeOrden.model.Orden;

import com.springboot.ServicioGestorDeOrden.repository.OrdenRepository;

import com.springboot.ServicioGestorDeOrden.repository.PaqueteRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrdenService implements IOrdenService{

    @Autowired
    private OrdenRepository  ordenRepository;
    @Autowired
    private IClienteService iClienteService;
    @Autowired
    private IDestinoService iDestinoService;
    @Autowired
    private PaqueteRepository paqueteRepository;
    @Override
    public void guardarPreOrden(OrdenDTO preOrden) {

        Orden preoOrdenGuardar = new Orden();

        preoOrdenGuardar.setOrden_cantidadDePaquete(preOrden.getCantidadDePaquetes());
        preoOrdenGuardar.setOrden_fechadecreacion(LocalDateTime.now());
        preoOrdenGuardar.setOrden_fechamodificaicon(LocalDateTime.now());
        preoOrdenGuardar.setOrden_codigoDeConfirmacion(generarCodigoConfirmacion());

        preoOrdenGuardar.setOrden_statusConfirmacion(false);

        preoOrdenGuardar.setOrden_cliente(iClienteService.guardarCliente(preOrden.getCliente()));


        preoOrdenGuardar.setOrden_destinatario(iDestinoService.guardarDestino(preOrden.getDestino()));

        Long numeroDeOrden = Math.abs(generarID() - generarID());
        preoOrdenGuardar.setOrden_numerodeorden(numeroDeOrden);


        this.ordenRepository.save(preoOrdenGuardar);

    }

    @Override
    @Transactional
    @CircuitBreaker(name = "ServicioGestorPaquete" , fallbackMethod = "fallbackGetServicioGestorPaquete")
    @Retry(name = "ServicioGestorPaquete")
    public String confirmarOrden(DatosConfirmacionDTO datosConfirmacionDTO) {
        Orden ordenConfirmada = this.ordenRepository.findById(datosConfirmacionDTO.getNumeroDeOrden()).orElse(null);


        if(ordenConfirmada.getOrden_numerodeorden().equals(datosConfirmacionDTO.getNumeroDeOrden())
                && ordenConfirmada.getOrden_codigoDeConfirmacion().equals(datosConfirmacionDTO.getCodigoDeConfirmacion())
                && ordenConfirmada.isOrden_statusConfirmacion() == false){

            ordenConfirmada.setOrden_fechamodificaicon(LocalDateTime.now());
            ordenConfirmada.setOrden_statusConfirmacion(true);

            this.ordenRepository.save(ordenConfirmada);

            for(PaqueteDTO paquete : datosConfirmacionDTO.getPaquetes()){
                paquete.setNumeroDeOrden(datosConfirmacionDTO.getNumeroDeOrden());
                paqueteRepository.registrarPaquete(paquete);
            }

            return "Orden " + ordenConfirmada.getOrden_numerodeorden() + " Confirmada";
        } else if (ordenConfirmada.isOrden_statusConfirmacion() == true) {
            return "Orden " + ordenConfirmada.getOrden_numerodeorden() + " ya fue confirmada";
        } else {
            return "Orden y/o codigo de verificacion incorrecto";
        }

    }

    @Override
    public List<OrdenDTO> obtenerOrdenes() {
        List<Orden> ordenes = this.ordenRepository.findAll();
        List<OrdenDTO> ordenesRespuesta = new ArrayList<>();
        for (Orden orden: ordenes) {
            OrdenDTO ordenDTO = new OrdenDTO();
            ordenDTO.setNumeroDeOrden(orden.getOrden_numerodeorden());
            ordenDTO.setFechadecreacion(orden.getOrden_fechadecreacion());
            ordenDTO.setFechadeconfirmacion(orden.getOrden_fechamodificaicon());
            ordenDTO.setValidacion(orden.isOrden_statusConfirmacion());



            ClienteDTO clienteDTO = new ClienteDTO() ;
            clienteDTO.setCorreo(orden.getOrden_cliente().getCliente_Correo());
            clienteDTO.setTelefono(orden.getOrden_cliente().getCliente_telefono());
            clienteDTO.setRazonSocial(orden.getOrden_cliente().getCliente_RazonSocial());
            clienteDTO.setCuitCuil(orden.getOrden_cliente().getCliente_CUIL_CUIT());
            ordenDTO.setCliente(clienteDTO);

            DestinoDTO destinoDTO = new DestinoDTO();
            destinoDTO.setCodigoPostal(orden.getOrden_destinatario().getDestino_CodigoPostal());
            destinoDTO.setCuitCuil(orden.getOrden_destinatario().getDestino_CUITCUIL());
            destinoDTO.setDirreccion(orden.getOrden_destinatario().getDestino_Dirreccion());
            destinoDTO.setDestinatario(orden.getOrden_destinatario().getDestino_Destinatario());

            ordenDTO.setDestino(destinoDTO);
            ordenesRespuesta.add(ordenDTO);
        }
        
        return ordenesRespuesta;
    }

    @Override
    public OrdenDTO obtenerOrden(Long numerodeorden) {
        Orden orden = this.ordenRepository.findById(numerodeorden).orElse(null);

        OrdenDTO ordenDTO = new OrdenDTO();

        ordenDTO.setNumeroDeOrden(orden.getOrden_numerodeorden());
        ordenDTO.setFechadecreacion(orden.getOrden_fechadecreacion());
        ordenDTO.setFechadeconfirmacion(orden.getOrden_fechamodificaicon());
        ordenDTO.setValidacion(orden.isOrden_statusConfirmacion());

        ClienteDTO clienteDTO = new ClienteDTO() ;
        clienteDTO.setCorreo(orden.getOrden_cliente().getCliente_Correo());
        clienteDTO.setTelefono(orden.getOrden_cliente().getCliente_telefono());
        clienteDTO.setRazonSocial(orden.getOrden_cliente().getCliente_RazonSocial());
        clienteDTO.setCuitCuil(orden.getOrden_cliente().getCliente_CUIL_CUIT());
        ordenDTO.setCliente(clienteDTO);

        DestinoDTO destinoDTO = new DestinoDTO();
        destinoDTO.setCodigoPostal(orden.getOrden_destinatario().getDestino_CodigoPostal());
        destinoDTO.setCuitCuil(orden.getOrden_destinatario().getDestino_CUITCUIL());
        destinoDTO.setDirreccion(orden.getOrden_destinatario().getDestino_Dirreccion());
        destinoDTO.setDestinatario(orden.getOrden_destinatario().getDestino_Destinatario());

        ordenDTO.setDestino(destinoDTO);

        return ordenDTO;
    }

    @Override
    public void editarOrden() {

    }


    private Long generarID(){

        SecureRandom random = new SecureRandom();
        Long numeroAleatorio = 1_000_000_000_000_000L + (Math.abs(random.nextLong()) % 9_000_000_000_000_000L);

        return numeroAleatorio;
    }

    private Long generarCodigoConfirmacion(){

        SecureRandom random = new SecureRandom();
        Long numeroAleatorio = 1_000L + (Math.abs(random.nextLong()) % 9_000L);

        return numeroAleatorio;
    }

    public String fallbackGetServicioGestorPaquete(Throwable throwable){
        return "Operacion Fallida. Vuelva mas tarde";
    }


}
