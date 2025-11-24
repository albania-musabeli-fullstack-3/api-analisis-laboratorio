package com.musabeli.api_laboratorio.mapper;

import com.musabeli.api_laboratorio.dtos.CreateLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.ResponseLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.UpdateLaboratorioDto;
import com.musabeli.api_laboratorio.entities.Laboratorio;

public class LaboratorioMapper {

    public static Laboratorio fromCreateLaboratorioDto(CreateLaboratorioDto dto) {
        return Laboratorio.builder()
                .nombre(dto.getNombre())
                .direccion(dto.getDireccion())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .especialidad(dto.getEspecialidad())
                .build();
    }

    public static Laboratorio fromUpdateLaboratorioDto(UpdateLaboratorioDto dto, Laboratorio laboratorio) {
        if (dto.getNombre() != null) {
            laboratorio.setNombre(dto.getNombre());
        }
        if (dto.getDireccion() != null) {
            laboratorio.setDireccion(dto.getDireccion());
        }
        if (dto.getTelefono() != null) {
            laboratorio.setTelefono(dto.getTelefono());
        }
        if (dto.getCorreo() != null) {
            laboratorio.setCorreo(dto.getCorreo());
        }
        if (dto.getEspecialidad() != null) {
            laboratorio.setEspecialidad(dto.getEspecialidad());
        }
        return laboratorio;
    }

    public static ResponseLaboratorioDto toResponseLaboratorioDto(Laboratorio laboratorio) {
        return ResponseLaboratorioDto.builder()
                .id(laboratorio.getId())
                .nombre(laboratorio.getNombre())
                .direccion(laboratorio.getDireccion())
                .telefono(laboratorio.getTelefono())
                .correo(laboratorio.getCorreo())
                .especialidad(laboratorio.getEspecialidad())
                .build();
    }
}