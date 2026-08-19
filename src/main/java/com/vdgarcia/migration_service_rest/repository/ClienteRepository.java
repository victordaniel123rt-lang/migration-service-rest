package com.vdgarcia.migration_service_rest.repository;

import com.vdgarcia.migration_service_rest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
