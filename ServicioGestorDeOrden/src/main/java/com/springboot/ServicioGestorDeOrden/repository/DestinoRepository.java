package com.springboot.ServicioGestorDeOrden.repository;

import com.springboot.ServicioGestorDeOrden.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinoRepository extends JpaRepository<Destino,Long>  {

    @Query("SELECT d FROM Destino d WHERE d.destino_CUITCUIL = :cuitCuil")
    Destino findByDestinoCuitCuil(@Param("cuitCuil") String destinoCuitCuil);

}
