package com.vdgarcia.migration_service_rest.service.impl;

import com.vdgarcia.migration_service_rest.dto.ClienteDTO;
import com.vdgarcia.migration_service_rest.mapper.Mapper;
import com.vdgarcia.migration_service_rest.model.Cliente;
import com.vdgarcia.migration_service_rest.repository.ClienteRepository;
import com.vdgarcia.migration_service_rest.service.inter.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;

    @Override
    public List<ClienteDTO> obtenerTodos() {
        return repository.findAll().stream().map(Mapper::toClienteDTO).toList();
    }

    @Override
    public ClienteDTO obtenerPorId(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        return Mapper.toClienteDTO(cliente);
    }

    @Override
    public ClienteDTO crear(ClienteDTO dto) {
        Cliente cliente = Mapper.toCliente(dto);
        Cliente creado = repository.save(cliente);
        return Mapper.toClienteDTO(creado);
    }

    @Override
    public ClienteDTO actualizar(Long id, ClienteDTO dto) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        Mapper.updateCliente(dto,cliente);
        Cliente actualizado = repository.save(cliente);
        return Mapper.toClienteDTO(actualizado);
    }

    @Override
    public ClienteDTO eliminar(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("Cliente no encontrado")
        );
        repository.delete(cliente);
        return Mapper.toClienteDTO(cliente);
    }

}
