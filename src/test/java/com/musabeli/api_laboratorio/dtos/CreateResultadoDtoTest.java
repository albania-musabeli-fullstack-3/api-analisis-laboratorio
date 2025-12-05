package com.musabeli.api_laboratorio.dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class CreateResultadoDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("01 - DTO con todos los campos correctos")
    void dtoConDatosCorrectos_esValido() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Hemograma completo");
        dto.setResultado("Normal");
        dto.setObservaciones("Sin observaciones relevantes");
        dto.setIdLaboratorio(5L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("02 - fechaAnalisis null")
    void fechaAnalisisNull() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(null);
        dto.setNombreAnalisis("Hemograma completo");
        dto.setResultado("Normal");
        dto.setObservaciones("Todo ok");
        dto.setIdLaboratorio(10L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("La fecha de análisis no puede ser nula");
    }

    @Test
    @DisplayName("03 - nombreAnalisis null")
    void nombreAnalisisNull() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis(null);
        dto.setResultado("Normal");
        dto.setObservaciones("Todo ok");
        dto.setIdLaboratorio(10L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre del análisis es obligatorio");
    }


    @Test
    @DisplayName("04 - nombreAnalisis con solo espacios")
    void nombreAnalisisSoloEspacios() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("     ");
        dto.setResultado("Normal");
        dto.setObservaciones("Todo ok");
        dto.setIdLaboratorio(10L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre del análisis es obligatorio");
    }


    @Test
    @DisplayName("05 - resultado null")
    void resultadoNull() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Hemograma completo");
        dto.setResultado(null);
        dto.setObservaciones("Todo ok");
        dto.setIdLaboratorio(10L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El campo resultado es obligatorio");
    }


    @Test
    @DisplayName("06 - resultado con solo espacios")
    void resultadoSoloEspacios() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Hemograma completo");
        dto.setResultado("        ");
        dto.setObservaciones("Todo ok");
        dto.setIdLaboratorio(10L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El campo resultado es obligatorio");
    }


    @Test
    @DisplayName("07 - observaciones con más de 1000 caracteres")
    void observacionesExcede1000() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Hemograma completo");
        dto.setResultado("Normal");
        dto.setObservaciones("X".repeat(1001));
        dto.setIdLaboratorio(10L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("Las observaciones no pueden exceder 1000 caracteres");
    }


    @Test
    @DisplayName("08 - idLaboratorio null")
    void idLaboratorioNull() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Hemograma completo");
        dto.setResultado("Normal");
        dto.setObservaciones("Todo ok");
        dto.setIdLaboratorio(null);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El ID del laboratorio es obligatorio");
    }


    @Test
    @DisplayName("09 - idLaboratorio cero o negativo")
    void idLaboratorioNoPositivo_debeFallar() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Hemograma completo");
        dto.setResultado("Normal");
        dto.setObservaciones("Todo ok");
        dto.setIdLaboratorio(0L);

        Set<ConstraintViolation<CreateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El ID del laboratorio debe ser un número positivo");
    }

}
