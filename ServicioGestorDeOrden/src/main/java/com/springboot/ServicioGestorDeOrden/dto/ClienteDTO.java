package com.springboot.ServicioGestorDeOrden.dto;

import com.springboot.ServicioGestorDeOrden.model.Orden;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class ClienteDTO {
    private String cuitCuil ;
    private String razonSocial;
    private String telefono;
    private String correo;
    private List<OrdenClienteDTO> ordenes;
}
