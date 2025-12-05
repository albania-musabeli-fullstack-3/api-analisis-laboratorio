package com.musabeli.api_laboratorio.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class ResponseResultadoDtoTest {

    @Test
    @DisplayName("01 - Record con todos los campos → componentes correctos")
    void recordConDatos_componentesCorrectos() {
        LocalDateTime fecha = LocalDateTime.of(2025, 6, 15, 14, 30);
        ResponseLaboratorioDto lab = ResponseLaboratorioDto.builder()
                .id(8L)
                .nombre("Lab Central")
                .build();

        ResponseResultadoDto dto = new ResponseResultadoDto(100L, fecha, "Glucosa", "95 mg/dL", "En ayuno", lab);

        assertThat(dto.id()).isEqualTo(100L);
        assertThat(dto.fechaAnalisis()).isEqualTo(fecha);
        assertThat(dto.nombreAnalisis()).isEqualTo("Glucosa");
        assertThat(dto.resultado()).isEqualTo("95 mg/dL");
        assertThat(dto.observaciones()).isEqualTo("En ayuno");
        assertThat(dto.laboratorio()).isEqualTo(lab);
    }

    @Test
    @DisplayName("02 - equals() y hashCode() → mismos valores son iguales")
    void mismosValores_sonIguales() {
        LocalDateTime fecha = LocalDateTime.of(2025, 12, 24, 10, 0);;
        ResponseLaboratorioDto lab = ResponseLaboratorioDto.builder().id(3L).build();

        ResponseResultadoDto dto1 = new ResponseResultadoDto(50L, fecha, "Hemograma", "Normal", null, lab);
        ResponseResultadoDto dto2 = new ResponseResultadoDto(50L, fecha, "Hemograma", "Normal", null, lab);

        assertThat(dto1).isEqualTo(dto2).hasSameHashCodeAs(dto2);
    }

    @Test
    @DisplayName("03 - Distinto id → NO son iguales")
    void distintoId_noSonIguales() {
        LocalDateTime fecha = LocalDateTime.now();
        ResponseLaboratorioDto lab = ResponseLaboratorioDto.builder().id(1L).build();

        ResponseResultadoDto dto1 = new ResponseResultadoDto(1L, fecha, "Test", "OK", null, lab);
        ResponseResultadoDto dto2 = new ResponseResultadoDto(2L, fecha, "Test", "OK", null, lab);

        assertThat(dto1).isNotEqualTo(dto2);
    }

    @Test
    @DisplayName("04 - Valores null → funciona perfectamente")
    void valoresNull_funciona() {
        ResponseResultadoDto dto = new ResponseResultadoDto(7L, null, null, null, null, null);

        assertThat(dto.id()).isEqualTo(7L);
        assertThat(dto.fechaAnalisis()).isNull();
        assertThat(dto.observaciones()).isNull();
    }

    @Test
    @DisplayName("05 - toString() contiene todos los campos")
    void toString_contieneTodo() {
        LocalDateTime fecha = LocalDateTime.of(2025, 3, 10, 8, 0);
        ResponseLaboratorioDto lab = ResponseLaboratorioDto.builder()
                .id(99L)
                .nombre("Lab Sur")
                .build();

        ResponseResultadoDto dto = new ResponseResultadoDto(33L, fecha, "Colesterol", "210", "Elevado", lab);

        String texto = dto.toString();

        assertThat(texto)
                .contains("id=33")
                .contains("fechaAnalisis=2025-03-10T08:00")
                .contains("nombreAnalisis=Colesterol")
                .contains("resultado=210")
                .contains("observaciones=Elevado")
                .contains("laboratorio=ResponseLaboratorioDto(id=99");
    }
}