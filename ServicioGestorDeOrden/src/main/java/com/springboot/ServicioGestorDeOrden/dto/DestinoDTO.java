package com.springboot.ServicioGestorDeOrden.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DestinoDTO {

    private String destinatario;
    private String  cuitCuil;
    private int codigoPostal;
    private String dirreccion;
}
