package com.vdgarcia.migration_service_rest.controller;

import com.vdgarcia.migration_service_rest.dto.DetallePedidoDTO;
import com.vdgarcia.migration_service_rest.service.inter.DetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detalles")
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService service;

    @PostMapping
    public ResponseEntity<DetallePedidoDTO> crear(@RequestBody DetallePedidoDTO dto){
        return ResponseEntity.ok(service.crear(dto));
    }

}
