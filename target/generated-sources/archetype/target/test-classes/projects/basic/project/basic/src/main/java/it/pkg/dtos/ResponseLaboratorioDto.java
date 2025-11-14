package it.pkg.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseLaboratorioDto {
    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String especialidad;
}
