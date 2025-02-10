package com.springboot.ServicioGestorDeOrden.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;

import java.time.LocalDateTime;


@Entity
@Data
public class Orden {
        @Id
        private Long orden_numerodeorden;
        private Long orden_codigoDeConfirmacion;
        private boolean orden_statusConfirmacion;
        private int orden_cantidadDePaquete;
        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
        @JoinColumn(name = "orden_cliente_id", referencedColumnName = "id_cliente", nullable = false)
        private Cliente orden_cliente;
        @OneToOne
        private Destino orden_destinatario;
        @Column(updatable = false)
        private LocalDateTime orden_fechadecreacion;
        private LocalDateTime orden_fechamodificaicon;

        public boolean getOrdenStatusConfirmacion() {
                return orden_statusConfirmacion;
        }
}
