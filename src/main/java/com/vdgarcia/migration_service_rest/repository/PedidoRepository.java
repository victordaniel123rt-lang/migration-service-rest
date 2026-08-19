package com.vdgarcia.migration_service_rest.repository;

import com.vdgarcia.migration_service_rest.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
