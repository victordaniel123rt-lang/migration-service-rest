package com.vdgarcia.migration_service_rest.dto;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DetallePedidoDTO {
    private Long id;
    private Long producto;
    private Long pedido;
    private Integer cantidad;

}
