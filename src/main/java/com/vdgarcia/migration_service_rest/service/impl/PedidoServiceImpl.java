package com.vdgarcia.migration_service_rest.service.impl;

import com.vdgarcia.migration_service_rest.dto.PedidoDTO;
import com.vdgarcia.migration_service_rest.mapper.Mapper;
import com.vdgarcia.migration_service_rest.model.*;
import com.vdgarcia.migration_service_rest.repository.ClienteRepository;
import com.vdgarcia.migration_service_rest.repository.PedidoRepository;
import com.vdgarcia.migration_service_rest.repository.ProductRepository;
import com.vdgarcia.migration_service_rest.service.inter.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProductRepository productRepository;
    private BigDecimal total;

    @Override
    public List<PedidoDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toPedidoDTO).toList();
    }

    @Override
    public PedidoDTO obtenerPorId(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Pedido no encontrado")
        );
        return Mapper.toPedidoDTO(pedido);
    }

    @Override
    public PedidoDTO crear(PedidoDTO dto) {
        Optional<Cliente> opt1 = clienteRepository.findById(dto.getCliente());
        if(opt1.isEmpty()){
            throw new IllegalArgumentException("El cliente que se solicita en el pedido no existe");
        }
        List<DetallePedido> detalles = dto.getDetalles().stream().map(Mapper::toDetallePedido).toList();
        List<Long> ids = detalles.stream().map(p->p.getProducto().getId()).toList();
        List<Producto> productos = productRepository.findAll();
        for(Producto p : productos ){
            Optional<Long> existe = ids.stream().filter(id -> id.equals(p.getId())).findFirst();
            if(existe.isEmpty()){
                Long l = existe.get();
                throw new IllegalArgumentException("El producto que ingresaste con ID: " + l + " no existe y no se tomara en cuenta");
            }
        }
        List<Producto> productosPedido = productRepository.findAllById(ids);
        for(Producto k : productosPedido){
            Optional<DetallePedido> detalle = detalles.stream().filter(d->d.getProducto().getId().equals(k.getId())).findFirst();
            if(detalle.isPresent()){
                DetallePedido det = detalle.get();
                boolean hayStock = det.getCantidad()>k.getStock();
                if (hayStock){
                    det.setCantidad(k.getStock());
                }
                total = total.add(k.getPrecio().multiply(BigDecimal.valueOf(det.getCantidad())));
            }
        }
        Pedido pedido = Pedido.builder()
                .fecha(LocalDate.now())
                .total(total)
                .cliente(opt1.get())
                .detallePedido(detalles)
                .estado(Estado.RECIBIDO)
                .build();
        Pedido creado = repository.save(pedido);

        return Mapper.toPedidoDTO(creado);
    }

    @Override
    public PedidoDTO actualizar(Long id, PedidoDTO dto) {
        Pedido pedido = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Pedido no encontrado")
        );
        Optional<Cliente> opt1 = clienteRepository.findById(dto.getCliente());
        if (opt1.isEmpty()) {
            throw new IllegalArgumentException("El cliente que se solicita en el pedido no existe");
        }
        List<DetallePedido> detalles = dto.getDetalles().stream().map(Mapper::toDetallePedido).toList();
        List<Long> ids = detalles.stream().map(p -> p.getProducto().getId()).toList();
        List<Producto> productos = productRepository.findAll();
        for (Producto p : productos) {
            Optional<Long> existe = ids.stream().filter(l -> id.equals(p.getId())).findFirst();
            if (existe.isEmpty()) {
                Long l = existe.get();
                throw new IllegalArgumentException("El producto que ingresaste con ID: " + l + " no existe y no se tomara en cuenta");
            }
        }
        List<Producto> productosPedido = productRepository.findAllById(ids);
        for (Producto k : productosPedido) {
            Optional<DetallePedido> detalle = detalles.stream().filter(d -> d.getProducto().getId().equals(k.getId())).findFirst();
            if (detalle.isPresent()) {
                DetallePedido det = detalle.get();
                boolean hayStock = det.getCantidad() > k.getStock();
                if (hayStock) {
                    det.setCantidad(k.getStock());
                }
                total = total.add(k.getPrecio().multiply(BigDecimal.valueOf(det.getCantidad())));
            }

        }
        pedido.setEstado(dto.getEstado());
        pedido.setTotal(total);
        pedido.setFecha(LocalDate.now());
        Pedido actualizado = repository.save(pedido);
        return Mapper.toPedidoDTO(actualizado);
    }

    @Override
    public PedidoDTO eliminar(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Pedido no encontrado")
        );
        repository.delete(pedido);
        return Mapper.toPedidoDTO(pedido);
    }
}
