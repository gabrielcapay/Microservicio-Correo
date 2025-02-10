package com.springboot.ServicioGestorDeOrden.service;

import com.springboot.ServicioGestorDeOrden.dto.ClienteDTO;
import com.springboot.ServicioGestorDeOrden.model.Cliente;

public interface IClienteService {

    public Cliente guardarCliente(ClienteDTO cliente);
    public ClienteDTO registrarCliente(ClienteDTO clienteDTO);
    public ClienteDTO buscarCliente(String CuitCuilParam);
}
