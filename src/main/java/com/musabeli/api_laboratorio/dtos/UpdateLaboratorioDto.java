package com.musabeli.api_laboratorio.dtos;

import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UpdateLaboratorioDto {

    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String direccion;

    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    private String telefono;

    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    private String correo;

    @Size(max = 100, message = "La especialidad no puede exceder 100 caracteres")
    private String especialidad;
}