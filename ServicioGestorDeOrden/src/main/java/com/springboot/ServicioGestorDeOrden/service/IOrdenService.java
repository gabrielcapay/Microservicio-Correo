package com.springboot.ServicioGestorDeOrden.service;

import com.springboot.ServicioGestorDeOrden.dto.DatosConfirmacionDTO;
import com.springboot.ServicioGestorDeOrden.dto.OrdenDTO;
import com.springboot.ServicioGestorDeOrden.model.Orden;

import java.util.List;


public interface IOrdenService {
    public void guardarPreOrden(OrdenDTO preOrden);
    public String confirmarOrden(DatosConfirmacionDTO datosConfirmacionDTO);
    public void editarOrden();
    public List<OrdenDTO> obtenerOrdenes();
    public OrdenDTO obtenerOrden(Long numerodeorden);

}
