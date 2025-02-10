package com.springboot.ServicioGestorPaquete.repository;

import com.springboot.ServicioGestorPaquete.model.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking,Long> {
    @Query("SELECT t FROM Tracking t WHERE t.paquete.codigoPaquete = :codigoPaquete ORDER BY t.FechaDeRegistro DESC")
    List<Tracking> findByPaqueteOrderByFechaDeRegistroDesc(@Param("codigoPaquete") Long codigoPaquete);

}
