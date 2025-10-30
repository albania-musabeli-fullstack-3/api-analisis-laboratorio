package com.musabeli.api_laboratorio.services;

import com.musabeli.api_laboratorio.dtos.CreateResultadoDto;
import com.musabeli.api_laboratorio.dtos.ResponseResultadoDto;
import com.musabeli.api_laboratorio.entities.Laboratorio;
import com.musabeli.api_laboratorio.entities.ResultadoAnalisis;
import com.musabeli.api_laboratorio.exceptions.ResourceNotFoundException;
import com.musabeli.api_laboratorio.mapper.ResultadoMapper;
import com.musabeli.api_laboratorio.repositories.LaboratorioRepository;
import com.musabeli.api_laboratorio.repositories.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultadoAnalisisServiceImpl implements ResultadoAnalisisService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;


    @Override
    public ResponseResultadoDto createResultadoAnalisis(CreateResultadoDto resultadoDto) {
        // buscar laboratorio
        Laboratorio laboratorio = laboratorioRepository.findById(resultadoDto.getIdLaboratorio())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Laboratorio no encontrado con ID: " + resultadoDto.getIdLaboratorio()));

        // dto a entidad
        ResultadoAnalisis resultado = ResultadoMapper.fromCreateResultado(resultadoDto, laboratorio);


        ResultadoAnalisis resultadoBd = this.resultadoRepository.save(resultado);

        return ResultadoMapper.toResponseResultadoDto(resultadoBd);
    }

    @Override
    public List<ResponseResultadoDto> getResultadosAnalisis() {
        // transformar entidad a dto usando mapper
        return resultadoRepository.findAll().stream()
                .map(ResultadoMapper::toResponseResultadoDto)
                .collect(Collectors.toList());

    }
}