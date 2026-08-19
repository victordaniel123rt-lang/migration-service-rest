package com.vdgarcia.migration_service_rest.service.inter;

import com.vdgarcia.migration_service_rest.dto.PedidoDTO;

import java.util.List;

public interface PedidoService {

    List<PedidoDTO> obtenerTodos();
    PedidoDTO obtenerPorId(Long id);
    PedidoDTO crear(PedidoDTO dto);
    PedidoDTO actualizar(Long id, PedidoDTO dto);
    PedidoDTO eliminar(Long id);

}
