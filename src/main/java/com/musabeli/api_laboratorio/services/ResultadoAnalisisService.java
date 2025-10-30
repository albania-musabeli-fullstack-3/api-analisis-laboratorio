package com.musabeli.api_laboratorio.services;


import com.musabeli.api_laboratorio.dtos.CreateResultadoDto;
import com.musabeli.api_laboratorio.dtos.ResponseResultadoDto;
import com.musabeli.api_laboratorio.dtos.UpdateResultadoDto;

import java.util.List;

public interface ResultadoAnalisisService {

    ResponseResultadoDto createResultadoAnalisis(CreateResultadoDto resultadoDto);
    List<ResponseResultadoDto> getResultadosAnalisis();
    ResponseResultadoDto getResultadoAnalisisById(Long id);
    ResponseResultadoDto updateResultadoAnalisis(Long id, UpdateResultadoDto resultadoDto);
}
