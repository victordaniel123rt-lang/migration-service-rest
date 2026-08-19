package com.vdgarcia.migration_service_rest.repository;

import com.vdgarcia.migration_service_rest.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Producto, Long> {
}
