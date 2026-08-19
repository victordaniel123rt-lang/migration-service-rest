package com.vdgarcia.migration_service_rest.service.inter;


import com.vdgarcia.migration_service_rest.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> obtenerTodos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO crear(ProductoDTO dto);
    ProductoDTO actualizar(Long id, ProductoDTO dto);
    ProductoDTO eliminar(Long id);

}
