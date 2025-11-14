package it.pkg.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ResponseResultadoDto(

        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fechaAnalisis,
        String nombreAnalisis,
        String resultado,
        String observaciones,
        ResponseLaboratorioDto laboratorio
){}