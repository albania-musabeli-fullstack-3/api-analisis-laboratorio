package it.pkg.services;


import it.pkg.dtos.CreateResultadoDto;
import it.pkg.dtos.ResponseResultadoDto;
import it.pkg.dtos.UpdateResultadoDto;

import java.util.List;

public interface ResultadoAnalisisService {

    ResponseResultadoDto createResultadoAnalisis(CreateResultadoDto resultadoDto);
    List<ResponseResultadoDto> getResultadosAnalisis();
    ResponseResultadoDto getResultadoAnalisisById(Long id);
    ResponseResultadoDto updateResultadoAnalisis(Long id, UpdateResultadoDto resultadoDto);
    ResponseResultadoDto deleteResultadoAnalisis(Long id);
}
