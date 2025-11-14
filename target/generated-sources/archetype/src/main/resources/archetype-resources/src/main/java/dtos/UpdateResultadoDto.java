#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateResultadoDto {

    private LocalDateTime fechaAnalisis;

    @Size(min = 4, max = 150 ,message = "El nombre del análisis debe tener entre 4 y 150 caracteres")
    private String nombreAnalisis;

    @Size(min = 4, max = 500 ,message = "El resultado debe tener entre 4 y 500 caracteres")
    private String resultado;

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    private String observaciones;

    @Positive(message = "El ID del laboratorio debe ser un número positivo")
    private Long idLaboratorio;
}
