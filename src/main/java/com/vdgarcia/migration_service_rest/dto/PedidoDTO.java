package com.vdgarcia.migration_service_rest.dto;
import com.vdgarcia.migration_service_rest.model.Estado;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PedidoDTO {

    private Long id;
    private Estado estado;
    private BigDecimal total;
    private LocalDate fecha;
    private Long cliente;
    private List<DetallePedidoDTO> detalles;
}
