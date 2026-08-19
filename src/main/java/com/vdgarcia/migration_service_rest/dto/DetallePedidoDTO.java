package com.vdgarcia.migration_service_rest.dto;
import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DetallePedidoDTO {
    private Long producto;
    private Long detalle;

}
