package com.musabeli.api_laboratorio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateResultadoDto {

    @NotNull(message = "La fecha de análisis no puede ser nula")
    private LocalDateTime fechaAnalisis;

    @NotBlank(message = "El nombre del análisis es obligatorio")
    private String nombreAnalisis;

    @NotBlank(message = "El campo resultado es obligatorio")
    private String resultado;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    private String observaciones;

    @NotNull(message = "El ID del laboratorio es obligatorio")
    @Positive(message = "El ID del laboratorio debe ser un número positivo")
    private Long idLaboratorio;
}
