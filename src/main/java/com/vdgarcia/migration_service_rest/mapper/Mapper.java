package com.vdgarcia.migration_service_rest.mapper;


import com.vdgarcia.migration_service_rest.dto.ClienteDTO;
import com.vdgarcia.migration_service_rest.dto.DetallePedidoDTO;
import com.vdgarcia.migration_service_rest.dto.PedidoDTO;
import com.vdgarcia.migration_service_rest.dto.ProductoDTO;
import com.vdgarcia.migration_service_rest.model.Cliente;
import com.vdgarcia.migration_service_rest.model.DetallePedido;
import com.vdgarcia.migration_service_rest.model.Pedido;
import com.vdgarcia.migration_service_rest.model.Producto;

import java.util.List;

public class Mapper {

    public static ClienteDTO toClienteDTO(Cliente entity){
        if (entity==null) return null;
        List<PedidoDTO> pedidos = entity.getPedidos().stream().map(Mapper::toPedidoDTO).toList();
        return ClienteDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .pedidos(pedidos)
                .build();
    }


    public static Cliente toCliente(ClienteDTO dto){
        if (dto==null) return null;
        List<Pedido> pedidos = dto.getPedidos().stream().map(Mapper::toPedido).toList();
        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .pedidos(pedidos)
                .build();
    }



    public static void updateCliente(ClienteDTO dto, Cliente entity){
        if (dto == null || entity == null) return;

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());

    }


    public static ProductoDTO toProductoDTO(Producto entity){
        if (entity==null) return null;
        List<DetallePedidoDTO> detalles = entity.getDetalles().stream().map(Mapper::toDetallePedidoDTO).toList();
        return ProductoDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .descripcion(entity.getDescripcion())
                .stock(entity.getStock())
                .descripcion(entity.getDescripcion())
                .detalles(detalles)
                .build();

    }

    public static DetallePedido toDetallePedido(DetallePedidoDTO dto){
        if (dto==null) return null;
        return DetallePedido.builder()
                .pedido(Pedido.builder().id(dto.getPedido()).build())
                .producto(Producto.builder().id(dto.getProducto()).build())
                .Cantidad(dto.getCantidad())
                .build();
    }


    public static PedidoDTO toPedidoDTO(Pedido entity){
        if (entity==null) return null;
        List<DetallePedidoDTO> detalles = entity.getDetallePedido().stream().map(Mapper::toDetallePedidoDTO).toList();
        return PedidoDTO.builder()
                .id(entity.getId())
                .cliente(entity.getCliente().getId())
                .fecha(entity.getFecha())
                .total(entity.getTotal())
                .estado(entity.getEstado())
                .detalles(detalles)
                .build();
    }


    public static Producto toProducto(ProductoDTO dto){
        if (dto==null) return null;
        List<DetallePedido> detalles = dto.getDetalles().stream().map(Mapper::toDetallePedido).toList();
        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .stock(dto.getStock())
                .descripcion(dto.getDescripcion())
                .detalles(detalles)
                .build();

    }





    public static Pedido toPedido(PedidoDTO dto){
        if (dto==null) return null;
        List<DetallePedido> detalles = dto.getDetalles().stream().map(Mapper::toDetallePedido).toList();
        return Pedido.builder()
                .id(dto.getId())
                .cliente(Cliente.builder().id(dto.getCliente()).build())
                .fecha(dto.getFecha())
                .total(dto.getTotal())
                .estado(dto.getEstado())
                .detallePedido(detalles)
                .build();
    }


    public static void updateProducto(ProductoDTO dto , Producto entity){
        if (dto==null || entity==null) return;

        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        entity.setStock(dto.getStock());
        entity.setPrecio(dto.getPrecio());

    }



    public static void updatePedido(PedidoDTO dto,Pedido entity){
        if (dto == null || entity == null) return;
        entity.setEstado(dto.getEstado());
        entity.setTotal(dto.getTotal());
        entity.setFecha(dto.getFecha());
    }


    public static DetallePedidoDTO toDetallePedidoDTO(DetallePedido entity){
        if (entity==null) return null;
        return DetallePedidoDTO.builder()
                .id(entity.getId())
                .pedido(entity.getPedido().getId())
                .producto(entity.getProducto().getId())
                .cantidad(entity.getCantidad())
                .build();
    }





























}
