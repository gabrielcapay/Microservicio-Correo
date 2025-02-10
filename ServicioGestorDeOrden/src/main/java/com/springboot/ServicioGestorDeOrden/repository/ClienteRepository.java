package com.springboot.ServicioGestorDeOrden.repository;

import com.springboot.ServicioGestorDeOrden.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("SELECT c FROM Cliente c WHERE c.cliente_CUIL_CUIT = :cuilCuit")
    Cliente findByCuilCuit(@Param("cuilCuit") String cuilCuit);
}
