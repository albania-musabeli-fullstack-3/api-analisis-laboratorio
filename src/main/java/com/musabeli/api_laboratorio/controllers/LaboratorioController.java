package com.musabeli.api_laboratorio.controllers;

import com.musabeli.api_laboratorio.dtos.CreateLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.ResponseLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.UpdateLaboratorioDto;
import com.musabeli.api_laboratorio.services.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/laboratorio")
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    @PostMapping
    public ResponseEntity<ResponseLaboratorioDto> createLaboratorio(@Valid @RequestBody CreateLaboratorioDto dto) {
        ResponseLaboratorioDto nuevoLaboratorio = laboratorioService.createLaboratorio(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLaboratorio);
    }

    @GetMapping
    public ResponseEntity<List<ResponseLaboratorioDto>> getLaboratorios() {
        List<ResponseLaboratorioDto> laboratorios = laboratorioService.getLaboratorios();
        return ResponseEntity.status(HttpStatus.OK).body(laboratorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseLaboratorioDto> getLaboratorioById(@PathVariable Long id) {
        ResponseLaboratorioDto laboratorio = laboratorioService.getLaboratorioById(id);
        return ResponseEntity.status(HttpStatus.OK).body(laboratorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseLaboratorioDto> updateLaboratorio(@PathVariable Long id, @Valid @RequestBody UpdateLaboratorioDto dto) {
        ResponseLaboratorioDto laboratorioActualizado = laboratorioService.updateLaboratorio(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(laboratorioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseLaboratorioDto> deleteLaboratorio(@PathVariable Long id) {
        ResponseLaboratorioDto laboratorio = laboratorioService.deleteLaboratorio(id);
        return ResponseEntity.status(HttpStatus.OK).body(laboratorio);
    }

}
