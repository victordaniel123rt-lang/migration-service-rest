package com.vdgarcia.migration_service_rest.mapper;


import com.vdgarcia.migration_service_rest.dto.ClienteDTO;
import com.vdgarcia.migration_service_rest.model.Cliente;

public class Mapper {

    public static ClienteDTO toClienteDTO(Cliente entity){
        if (entity==null) return null;
        return ClienteDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .build();
    }


    public static Cliente toCliente(ClienteDTO dto){
        if (dto==null) return null;

        return Cliente.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .build();
    }



    public void updateCliente(ClienteDTO dto, Cliente entity){
        if (dto == null || entity == null) return;

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());

    }











}
