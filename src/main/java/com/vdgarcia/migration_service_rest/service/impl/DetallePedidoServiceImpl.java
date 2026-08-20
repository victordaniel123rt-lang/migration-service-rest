package com.vdgarcia.migration_service_rest.service.impl;

import com.vdgarcia.migration_service_rest.dto.DetallePedidoDTO;
import com.vdgarcia.migration_service_rest.mapper.Mapper;
import com.vdgarcia.migration_service_rest.model.DetallePedido;
import com.vdgarcia.migration_service_rest.model.Pedido;
import com.vdgarcia.migration_service_rest.model.Producto;
import com.vdgarcia.migration_service_rest.repository.DetallePedidoRepository;
import com.vdgarcia.migration_service_rest.repository.PedidoRepository;
import com.vdgarcia.migration_service_rest.repository.ProductRepository;
import com.vdgarcia.migration_service_rest.service.inter.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ProductRepository productRepository;

    @Override
    public DetallePedidoDTO crear(DetallePedidoDTO dto) {
        Pedido pedido = pedidoRepository.findById(dto.getPedido()).orElseThrow(
                ()-> new IllegalArgumentException("El pedido no existe")
        );
        Producto producto = productRepository.findById(dto.getProducto()).orElseThrow(
                ()-> new IllegalArgumentException("Producto no encontrado")
        );
        DetallePedido detalle = Mapper.toDetallePedido(dto);
        DetallePedido creado = repository.save(detalle);
        return Mapper.toDetallePedidoDTO(creado);
    }
}
