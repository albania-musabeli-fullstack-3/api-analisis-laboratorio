#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api_laboratorio.services;


import ${package}.api_laboratorio.dtos.CreateResultadoDto;
import ${package}.api_laboratorio.dtos.ResponseResultadoDto;
import ${package}.api_laboratorio.dtos.UpdateResultadoDto;

import java.util.List;

public interface ResultadoAnalisisService {

    ResponseResultadoDto createResultadoAnalisis(CreateResultadoDto resultadoDto);
    List<ResponseResultadoDto> getResultadosAnalisis();
    ResponseResultadoDto getResultadoAnalisisById(Long id);
    ResponseResultadoDto updateResultadoAnalisis(Long id, UpdateResultadoDto resultadoDto);
    ResponseResultadoDto deleteResultadoAnalisis(Long id);
}
