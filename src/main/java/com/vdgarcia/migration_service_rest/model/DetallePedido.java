package com.vdgarcia.migration_service_rest.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    private Integer Cantidad;
}
