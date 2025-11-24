package com.musabeli.api_laboratorio.services;

import java.util.stream.Collectors;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musabeli.api_laboratorio.dtos.CreateLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.ResponseLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.UpdateLaboratorioDto;
import com.musabeli.api_laboratorio.entities.Laboratorio;
import com.musabeli.api_laboratorio.exceptions.ResourceNotFoundException;
import com.musabeli.api_laboratorio.mapper.LaboratorioMapper;
import com.musabeli.api_laboratorio.repositories.LaboratorioRepository;

@Service
public class LaboratorioServiceImpl implements LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    private Laboratorio findLaboratorioById(Long id) {
        return laboratorioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratorio con id " + id + " no encontrado"));
    }

    @Override
    public ResponseLaboratorioDto createLaboratorio(CreateLaboratorioDto laboratorioDto) {
        Laboratorio laboratorio = LaboratorioMapper.fromCreateLaboratorioDto(laboratorioDto);
        Laboratorio laboratorioBd = laboratorioRepository.save(laboratorio);
        return LaboratorioMapper.toResponseLaboratorioDto(laboratorioBd);
    }

    @Override
    public List<ResponseLaboratorioDto> getLaboratorios() {
        return laboratorioRepository.findAll().stream()
                .map(LaboratorioMapper::toResponseLaboratorioDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseLaboratorioDto getLaboratorioById(Long id) {
        Laboratorio laboratorio = findLaboratorioById(id);
        return LaboratorioMapper.toResponseLaboratorioDto(laboratorio);
    }

    @Override
    public ResponseLaboratorioDto updateLaboratorio(Long id, UpdateLaboratorioDto laboratorioDto) {
        Laboratorio laboratorioById = findLaboratorioById(id);
        Laboratorio laboratorio = LaboratorioMapper.fromUpdateLaboratorioDto(laboratorioDto, laboratorioById);
        Laboratorio laboratorioActualizado = laboratorioRepository.save(laboratorio);
        return LaboratorioMapper.toResponseLaboratorioDto(laboratorioActualizado);
    }

    @Override
    public ResponseLaboratorioDto deleteLaboratorio(Long id) {
        Laboratorio laboratorio = findLaboratorioById(id);
        if (!laboratorio.getResultados().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar el laboratorio porque tiene resultados asociados");
        }
        laboratorioRepository.deleteById(id);
        return LaboratorioMapper.toResponseLaboratorioDto(laboratorio);
    }
}