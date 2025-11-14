#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;


import ${package}.dtos.CreateResultadoDto;
import ${package}.dtos.ResponseResultadoDto;
import ${package}.dtos.UpdateResultadoDto;

import java.util.List;

public interface ResultadoAnalisisService {

    ResponseResultadoDto createResultadoAnalisis(CreateResultadoDto resultadoDto);
    List<ResponseResultadoDto> getResultadosAnalisis();
    ResponseResultadoDto getResultadoAnalisisById(Long id);
    ResponseResultadoDto updateResultadoAnalisis(Long id, UpdateResultadoDto resultadoDto);
    ResponseResultadoDto deleteResultadoAnalisis(Long id);
}
