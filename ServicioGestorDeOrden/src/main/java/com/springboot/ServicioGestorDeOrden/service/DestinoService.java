package com.springboot.ServicioGestorDeOrden.service;

import com.springboot.ServicioGestorDeOrden.dto.DestinoDTO;
import com.springboot.ServicioGestorDeOrden.model.Destino;
import com.springboot.ServicioGestorDeOrden.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinoService implements IDestinoService {

    @Autowired
    private DestinoRepository destinoRepository;


    @Override
    public Destino guardarDestino(DestinoDTO destinoDTO) {

        Destino destinoOrden = new Destino();

            destinoOrden.setDestino_CUITCUIL(destinoDTO.getCuitCuil());
            destinoOrden.setDestino_Destinatario(destinoDTO.getDestinatario());
            destinoOrden.setDestino_CodigoPostal(destinoDTO.getCodigoPostal());
            destinoOrden.setDestino_Dirreccion(destinoDTO.getDirreccion());
            this.destinoRepository.saveAndFlush(destinoOrden);




        return destinoOrden;
    }
}
