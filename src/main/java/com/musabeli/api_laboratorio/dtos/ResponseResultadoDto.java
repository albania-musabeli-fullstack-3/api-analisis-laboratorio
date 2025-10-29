package com.musabeli.api_laboratorio.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ResponseResultadoDto(

        Long id,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaAnalisis,

        String resultado,

        String observaciones,

        ResponseLaboratorioDto laboratorio
){}