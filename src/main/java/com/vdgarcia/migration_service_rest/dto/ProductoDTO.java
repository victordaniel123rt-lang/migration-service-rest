package com.vdgarcia.migration_service_rest.dto;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private List<DetallePedidoDTO> detalles;
}
