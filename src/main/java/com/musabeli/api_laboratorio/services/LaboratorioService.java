package com.musabeli.api_laboratorio.services;

import com.musabeli.api_laboratorio.dtos.CreateLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.ResponseLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.UpdateLaboratorioDto;
import java.util.List;


public interface LaboratorioService {

    ResponseLaboratorioDto createLaboratorio(CreateLaboratorioDto laboratorioDto);
    List<ResponseLaboratorioDto> getLaboratorios();
    ResponseLaboratorioDto getLaboratorioById(Long id);
    ResponseLaboratorioDto updateLaboratorio(Long id, UpdateLaboratorioDto laboratorioDto);
    ResponseLaboratorioDto deleteLaboratorio(Long id);

}