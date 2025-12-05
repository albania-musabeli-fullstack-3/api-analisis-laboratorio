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


public class UpdateLaboratorioDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("01 - DTO completamente vacío")
    void dtoVacio_esValido() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        // todos los campos en null (no se envía nada)

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("02 - nombre con más de 100 caracteres")
    void nombreExcede100Caracteres_debeFallar() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setNombre("A".repeat(101)); // 101 caracteres

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre no puede exceder 100 caracteres");
    }

    @Test
    @DisplayName("03 - nombre con exactamente 100 caracteres")
    void nombreCon100Caracteres_esValido() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setNombre("A".repeat(100)); // justo 100

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("04 - dirección con más de 200 caracteres")
    void direccionExcede200Caracteres_debeFallar() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setDireccion("B".repeat(201)); // 201 caracteres

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("La dirección no puede exceder 200 caracteres");
    }

    @Test
    @DisplayName("05 - dirección con exactamente 200 caracteres")
    void direccionCon200Caracteres_esValido() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setDireccion("B".repeat(200));

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("06 - teléfono con más de 20 caracteres")
    void telefonoExcede20() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setTelefono("1".repeat(21)); // 21 caracteres

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El teléfono no puede exceder 20 caracteres");
    }

    @Test
    @DisplayName("07 - teléfono con exactamente 20 caracteres")
    void telefonoCon20Caracteres_esValido() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setTelefono("1".repeat(20)); // justo 20

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("08 - correo con más de 100 caracteres")
    void correoExcede100() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setCorreo("a".repeat(101)); // 101 caracteres

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El correo no puede exceder 100 caracteres");
    }

    @Test
    @DisplayName("09 - correo con exactamente 100 caracteres")
    void correoCon100Caracteres() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setCorreo("a".repeat(100)); // justo 100

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("10 - especialidad con más de 100 caracteres")
    void especialidadExcede100_debeFallar() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setEspecialidad("X".repeat(101)); // 101 caracteres

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("La especialidad no puede exceder 100 caracteres");
    }

    @Test
    @DisplayName("11 - especialidad con exactamente 100 caracteres")
    void especialidadCon100Caracteres_esValido() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setEspecialidad("X".repeat(100)); // justo 100

        Set<ConstraintViolation<UpdateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }


}
