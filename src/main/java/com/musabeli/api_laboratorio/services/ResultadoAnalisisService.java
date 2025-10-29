package com.musabeli.api_laboratorio.services;


import com.musabeli.api_laboratorio.dtos.CreateResultadoDto;
import com.musabeli.api_laboratorio.dtos.ResponseResultadoDto;
import com.musabeli.api_laboratorio.entities.ResultadoAnalisis;

public interface ResultadoAnalisisService {

    ResponseResultadoDto createResultadoAnalisis(CreateResultadoDto resultadoDto);
}
