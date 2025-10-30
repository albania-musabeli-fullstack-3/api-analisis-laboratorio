package com.musabeli.api_laboratorio.mapper;

import com.musabeli.api_laboratorio.dtos.CreateResultadoDto;
import com.musabeli.api_laboratorio.dtos.ResponseLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.ResponseResultadoDto;
import com.musabeli.api_laboratorio.entities.Laboratorio;
import com.musabeli.api_laboratorio.entities.ResultadoAnalisis;

public class ResultadoMapper {

    public static ResultadoAnalisis fromCreateResultado(CreateResultadoDto dto, Laboratorio laboratorio){
        return ResultadoAnalisis.builder()
                .fechaAnalisis(dto.getFechaAnalisis())
                .nombreAnalisis(dto.getNombreAnalisis())
                .resultado(dto.getResultado())
                .observaciones(dto.getObservaciones())
                .laboratorio(laboratorio)
                .build();
    }

    public static ResponseResultadoDto toResponseResultadoDto(ResultadoAnalisis entidad) {

        // objeto laboratorio con la response anidada
        ResponseLaboratorioDto labDto = ResponseLaboratorioDto.builder()
                .id(entidad.getLaboratorio().getId())
                .nombre(entidad.getLaboratorio().getNombre())
                .direccion(entidad.getLaboratorio().getDireccion())
                .telefono(entidad.getLaboratorio().getTelefono())
                .correo(entidad.getLaboratorio().getCorreo())
                .especialidad(entidad.getLaboratorio().getEspecialidad())
                .build();

        return new ResponseResultadoDto(
                entidad.getId(),
                entidad.getFechaAnalisis(),
                entidad.getNombreAnalisis(),
                entidad.getResultado(),
                entidad.getObservaciones(),
                labDto
        );
    }
}
