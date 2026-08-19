package com.vdgarcia.migration_service_rest.service.inter;

import com.vdgarcia.migration_service_rest.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> obtenerTodos();
    ClienteDTO obtenerPorId(Long id);
    ClienteDTO crear(ClienteDTO dto);
    ClienteDTO actualizar(Long id, ClienteDTO dto);
    ClienteDTO eliminar(Long id);
}
