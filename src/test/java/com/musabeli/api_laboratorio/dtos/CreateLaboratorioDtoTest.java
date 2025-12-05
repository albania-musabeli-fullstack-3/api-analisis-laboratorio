package com.musabeli.api_laboratorio.dtos;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateLaboratorioDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("01 - DTO con todos los campos correctos")
    void dtoConDatosCorrectos_esValido() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("Av. Libertad 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("02 - Nombre vacío o null")
    void nombreVacioONull() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre(null);
        dto.setDireccion("Av. Libertad 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre es obligatorio");
    }

    @Test
    @DisplayName("03 - Nombre con solo espacios)")
    void nombreSoloEspacios() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("     ");    // 5 espacios en blanco
        dto.setDireccion("Av. Libertad 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre es obligatorio");
    }


    @Test
    @DisplayName("04 - Nombre con más de 100 caracteres")
    void nombreExcede100Caracteres() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        // 101 caracteres (100 'A' + 1 extra)
        dto.setNombre("A".repeat(101));
        dto.setDireccion("Av. Libertad 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El nombre no puede exceder 100 caracteres");
    }


    @Test
    @DisplayName("05 - Nombre con exactamente 100 caracteres")
    void nombreCon100Caracteres_esValido() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("A".repeat(100));
        dto.setDireccion("Av. Libertad 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations).isEmpty();
    }

    @Test
    @DisplayName("06 - Dirección null")
    void direccionNull() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion(null);
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("La dirección es obligatoria");
    }


    @Test
    @DisplayName("07 - Dirección con más de 200 caracteres")
    void direccionExcede200Caracteres() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("B".repeat(201));
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("La dirección no puede exceder 200 caracteres");
    }

    @Test
    @DisplayName("08 - Teléfono null")
    void telefonoNull() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("Av. Siempre Viva 742");
        dto.setTelefono(null);
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El teléfono es obligatorio");
    }


    @Test
    @DisplayName("09 - Teléfono con más de 20 caracteres")
    void telefonoExcede20caracteres() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("Av. Siempre Viva 742");
        dto.setTelefono("123456789012345678901"); // 21 caracteres
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El teléfono no puede exceder 20 caracteres");
    }

    @Test
    @DisplayName("10 - Correo null")
    void correoNull() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("Av. Siempre Viva 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo(null);
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El correo es obligatorio");
    }

    @Test
    @DisplayName("11 - Correo con más de 100 caracteres")
    void correoExcede100caracteres() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("Av. Siempre Viva 742");
        dto.setTelefono("+56987654321");
        // 101 caracteres en total
        dto.setCorreo("a".repeat(92) + "@dominio.com"); // 92 + 10 = 102 caracteres
        dto.setEspecialidad("Análisis Clínicos");

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("El correo no puede exceder 100 caracteres");
    }


    @Test
    @DisplayName("12 - Especialidad null")
    void especialidadNull() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("Av. Siempre Viva 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad(null);

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("La especialidad es obligatoria");
    }

    @Test
    @DisplayName("13 - Especialidad con más de 100 caracteres")
    void especialidadExcede100_debeFallar() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Central");
        dto.setDireccion("Av.Siempre Viva 742");
        dto.setTelefono("+56987654321");
        dto.setCorreo("contacto@labcentral.cl");
        dto.setEspecialidad("X".repeat(101));

        Set<ConstraintViolation<CreateLaboratorioDto>> violations = validator.validate(dto);

        assertThat(violations)
                .hasSize(1)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("La especialidad no puede exceder 100 caracteres");
    }

}