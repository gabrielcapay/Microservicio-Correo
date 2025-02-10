package com.springboot.ServicioGestorDeOrden.service;

import com.springboot.ServicioGestorDeOrden.dto.ClienteDTO;
import com.springboot.ServicioGestorDeOrden.dto.DatosConfirmacionDTO;
import com.springboot.ServicioGestorDeOrden.dto.OrdenClienteDTO;
import com.springboot.ServicioGestorDeOrden.model.Cliente;
import com.springboot.ServicioGestorDeOrden.model.Orden;
import com.springboot.ServicioGestorDeOrden.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService implements IClienteService{
    @Autowired
    private ClienteRepository clienteRepository;


    @Override
    public Cliente guardarCliente(ClienteDTO clienteParam) {

        Cliente clienteOrden = clienteRepository.findByCuilCuit(clienteParam.getCuitCuil());
        Cliente clienteRespuesta = new Cliente();
        if(clienteOrden == null){
            clienteRespuesta.setCliente_CUIL_CUIT(clienteParam.getCuitCuil());
            clienteRespuesta.setCliente_RazonSocial(clienteParam.getRazonSocial());
            clienteRespuesta.setCliente_telefono(clienteParam.getTelefono());
            clienteRespuesta.setCliente_Correo(clienteParam.getCorreo());
            clienteRepository.save(clienteRespuesta);
        }else {
            clienteRespuesta = clienteOrden;
        }

        return clienteRespuesta;
    }

    @Override
    public ClienteDTO registrarCliente(ClienteDTO clienteDTOParam){
        Cliente clienteRespuesta = new Cliente();
        clienteRespuesta.setCliente_CUIL_CUIT(clienteDTOParam.getCuitCuil());
        clienteRespuesta.setCliente_RazonSocial(clienteDTOParam.getRazonSocial());
        clienteRespuesta.setCliente_telefono(clienteDTOParam.getTelefono());
        clienteRespuesta.setCliente_Correo(clienteDTOParam.getCorreo());
        clienteRepository.save(clienteRespuesta);

        return clienteDTOParam;
    }

    @Override
    public ClienteDTO buscarCliente(String CuitCuilParam){

        Cliente cliente = clienteRepository.findByCuilCuit(CuitCuilParam);

        if (cliente == null) {
            throw new EntityNotFoundException("El cliente con el Cuit/Cuil " + CuitCuilParam + " no existe.");
        }

        ClienteDTO clienteDTORespuesta = new ClienteDTO();

        clienteDTORespuesta.setCuitCuil(cliente.getCliente_CUIL_CUIT());
        clienteDTORespuesta.setTelefono(cliente.getCliente_telefono());
        clienteDTORespuesta.setCorreo(cliente.getCliente_Correo());
        clienteDTORespuesta.setRazonSocial(cliente.getCliente_RazonSocial());

        List<OrdenClienteDTO> ordenes = new ArrayList<>();

        for(Orden orden : cliente.getOrdenes()){
            OrdenClienteDTO ordenGuardar = new OrdenClienteDTO();

            ordenGuardar.setNumeroDeOrden(orden.getOrden_numerodeorden());
            ordenGuardar.setFechadecreacion(orden.getOrden_fechadecreacion());
            ordenGuardar.setFechadeconfirmacion(orden.getOrden_fechamodificaicon());
            ordenGuardar.setValidacion(orden.isOrden_statusConfirmacion());
            ordenGuardar.setCantidadDePaquetes(orden.getOrden_cantidadDePaquete());

            ordenes.add(ordenGuardar);
        }
        clienteDTORespuesta.setOrdenes(ordenes);
        return clienteDTORespuesta;
    }
}
