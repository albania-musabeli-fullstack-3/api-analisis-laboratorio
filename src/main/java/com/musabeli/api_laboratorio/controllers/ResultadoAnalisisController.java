package com.musabeli.api_laboratorio.controllers;

import com.musabeli.api_laboratorio.dtos.CreateResultadoDto;
import com.musabeli.api_laboratorio.dtos.ResponseResultadoDto;
import com.musabeli.api_laboratorio.entities.ResultadoAnalisis;
import com.musabeli.api_laboratorio.services.ResultadoAnalisisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultado")
public class ResultadoAnalisisController {

    @Autowired
    private ResultadoAnalisisService resultadoService;

    @PostMapping
    public ResponseEntity<ResponseResultadoDto> createResultado(@Valid @RequestBody CreateResultadoDto dto){
        ResponseResultadoDto nuevoResultado = this.resultadoService.createResultadoAnalisis(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoResultado);
    }

    @GetMapping
    public ResponseEntity<List<ResponseResultadoDto>> getResultadosAnalisis(){
        List<ResponseResultadoDto> resultados = this.resultadoService.getResultadosAnalisis();

        return ResponseEntity.status(HttpStatus.OK).body(resultados);
    }
}
