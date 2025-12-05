package com.musabeli.api_laboratorio.dtos;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;


public class UpdateResultadoDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("01 - DTO completamente vacío")
    void dtoVacio_esValido() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        // todos los campos en null
        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("02 - nombreAnalisis con menos de 4 caracteres")
    void nombreAnalisisMenosDe4() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setNombreAnalisis("ABC"); // 3 caracteres

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre del análisis debe tener entre 4 y 150 caracteres");
    }

    @Test
    @DisplayName("03 - nombreAnalisis con más de 150 caracteres")
    void nombreAnalisisMasDe150() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setNombreAnalisis("A".repeat(151)); // 151 caracteres

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre del análisis debe tener entre 4 y 150 caracteres");
    }

    @Test
    @DisplayName("04 - nombreAnalisis con exactamente 4 caracteres")
    void nombreAnalisisCon4Caracteres() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setNombreAnalisis("ABCD");

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("05 - nombreAnalisis con exactamente 150 caracteres")
    void nombreAnalisisCon150Caracteres() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setNombreAnalisis("A".repeat(150)); // justo 150

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("06 - resultado con menos de 4 caracteres")
    void resultadoMenosDe4_debeFallar() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setResultado("ABC"); // 3 caracteres

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El resultado debe tener entre 4 y 500 caracteres");
    }

    @Test
    @DisplayName("07 - resultado con más de 500 caracteres")
    void resultadoMasDe500() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setResultado("B".repeat(501)); // 501 caracteres

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El resultado debe tener entre 4 y 500 caracteres");
    }

    @Test
    @DisplayName("08 - resultado con exactamente 4 caracteres")
    void resultadoCon4Caracteres() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setResultado("ABCD");

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("09 - resultado con exactamente 500 caracteres")
    void resultadoCon500Caracteres() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setResultado("B".repeat(500));

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("10 - observaciones con más de 1000 caracteres")
    void observacionesMasDe1000() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setObservaciones("X".repeat(1001)); // 1001 caracteres

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);
        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("Las observaciones no pueden exceder 1000 caracteres");
    }


    @Test
    @DisplayName("11 - observaciones con exactamente 1000 caracteres")
    void observacionesCon1000Caracteres() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setObservaciones("X".repeat(1000));

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("12 - idLaboratorio negativo o cero")
    void idLaboratorioNoPositivo() {
        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setIdLaboratorio(0L);

        Set<ConstraintViolation<UpdateResultadoDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El ID del laboratorio debe ser un número positivo");
    }


}
