package com.springboot.ServicioGestorPaquete.repository;

import com.springboot.ServicioGestorPaquete.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long> {
    @Query("SELECT e FROM Estado e WHERE e.descripcion = :descripcion")
    Estado buscarPorDescripcion(@Param("descripcion") String descripcion);
}

