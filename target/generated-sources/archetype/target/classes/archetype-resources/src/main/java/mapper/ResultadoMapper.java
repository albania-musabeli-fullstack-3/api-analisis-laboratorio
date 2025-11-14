#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.dtos.CreateResultadoDto;
import ${package}.dtos.ResponseLaboratorioDto;
import ${package}.dtos.ResponseResultadoDto;
import ${package}.entities.Laboratorio;
import ${package}.entities.ResultadoAnalisis;

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
