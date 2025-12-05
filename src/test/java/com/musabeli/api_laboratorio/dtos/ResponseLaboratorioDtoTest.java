package com.musabeli.api_laboratorio.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ResponseLaboratorioDtoTest {

    @Test
    @DisplayName("01 - Los campos se asignan correctamente")
    void construirConBuilder() {
        ResponseLaboratorioDto dto = ResponseLaboratorioDto.builder()
                .id(99L)
                .nombre("Laboratorio Central")
                .direccion("Av. Siempre Viva 123")
                .telefono("+56987654321")
                .correo("contacto@lab.cl")
                .especialidad("Análisis Clínicos")
                .build();

        assertThat(dto.getId()).isEqualTo(99L);
        assertThat(dto.getNombre()).isEqualTo("Laboratorio Central");
        assertThat(dto.getDireccion()).isEqualTo("Av. Siempre Viva 123");
        assertThat(dto.getTelefono()).isEqualTo("+56987654321");
        assertThat(dto.getCorreo()).isEqualTo("contacto@lab.cl");
        assertThat(dto.getEspecialidad()).isEqualTo("Análisis Clínicos");
    }


    @Test
    @DisplayName("02 - Dos objetos DTOs con mismos valores")
    void dtosConMismosValores_sonIguales() {
        ResponseLaboratorioDto dto1 = ResponseLaboratorioDto.builder()
                .id(1L)
                .nombre("Lab Norte")
                .direccion("Calle 123")
                .telefono("+56911111111")
                .correo("norte@lab.cl")
                .especialidad("Hematología")
                .build();

        ResponseLaboratorioDto dto2 = ResponseLaboratorioDto.builder()
                .id(1L)
                .nombre("Lab Norte")
                .direccion("Calle 123")
                .telefono("+56911111111")
                .correo("norte@lab.cl")
                .especialidad("Hematología")
                .build();

        assertThat(dto1)
                .isEqualTo(dto2)
                .hasSameHashCodeAs(dto2);
    }


    @Test
    @DisplayName("03 - Dos DTOs con ID diferentes")
    void dtosConIdDiferentes() {
        ResponseLaboratorioDto dto1 = ResponseLaboratorioDto.builder()
                .id(1L)
                .nombre("Lab A")
                .build();

        ResponseLaboratorioDto dto2 = ResponseLaboratorioDto.builder()
                .id(2L)
                .nombre("Lab A")
                .build();

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("04 - DTO con todos los campos null")
    void dtoConTodosLosCamposNull_esValidoYEqualsFunciona() {
        ResponseLaboratorioDto dto1 = ResponseLaboratorioDto.builder().build();
        ResponseLaboratorioDto dto2 = ResponseLaboratorioDto.builder().build();

        assertThat(dto1).isEqualTo(dto2);
        assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());
        assertThat(dto1.toString()).contains("id=null");
    }

    @Test
    @DisplayName("05 - toString() contiene todos los campos")
    void toString_contieneTodosLosCampos() {
        ResponseLaboratorioDto dto = ResponseLaboratorioDto.builder()
                .id(7L)
                .nombre("Lab Sur")
                .direccion("Ruta 5")
                .telefono("22223333")
                .correo("sur@lab.cl")
                .especialidad("Bioquímica")
                .build();

        String texto = dto.toString();

        assertThat(texto)
                .contains("id=7")
                .contains("nombre=Lab Sur")
                .contains("direccion=Ruta 5")
                .contains("telefono=22223333")
                .contains("correo=sur@lab.cl")
                .contains("especialidad=Bioquímica");
    }

}
