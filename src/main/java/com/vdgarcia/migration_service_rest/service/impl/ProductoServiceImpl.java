package com.vdgarcia.migration_service_rest.service.impl;

import com.vdgarcia.migration_service_rest.dto.ProductoDTO;
import com.vdgarcia.migration_service_rest.mapper.Mapper;
import com.vdgarcia.migration_service_rest.model.Producto;
import com.vdgarcia.migration_service_rest.repository.ProductRepository;
import com.vdgarcia.migration_service_rest.service.inter.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductRepository repository;

    @Override
    public List<ProductoDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toProductoDTO).toList();
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Producto no encontrado")
        );
        return Mapper.toProductoDTO(producto);
    }

    @Override
    public ProductoDTO crear(ProductoDTO dto) {
        Producto producto = Mapper.toProducto(dto);
        Producto creado = repository.save(producto);
        return Mapper.toProductoDTO(creado);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Producto no encontrado")
        );
        Mapper.updateProducto(dto,producto);
        Producto actualizado = repository.save(producto);
        return Mapper.toProductoDTO(actualizado);
    }

    @Override
    public ProductoDTO eliminar(Long id) {
        Producto producto = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Producto no encontrado")
        );
        repository.delete(producto);
        return Mapper.toProductoDTO(producto);
    }
}
