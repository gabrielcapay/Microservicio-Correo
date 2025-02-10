package com.springboot.ServicioGestorPaquete.repository;


import com.springboot.ServicioGestorPaquete.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaqueteRepository extends JpaRepository<Paquete,Long> {
    @Query("SELECT p FROM Paquete p WHERE p.numeroDeOrden = :numeroDeOrden")
    List<Paquete> buscarPorNumeroDeOrden(@Param("numeroDeOrden") Long numeroDeOrden);


}
