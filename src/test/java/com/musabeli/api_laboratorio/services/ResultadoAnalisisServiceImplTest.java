package com.musabeli.api_laboratorio.services;

import com.musabeli.api_laboratorio.dtos.CreateResultadoDto;
import com.musabeli.api_laboratorio.dtos.ResponseResultadoDto;
import com.musabeli.api_laboratorio.dtos.UpdateResultadoDto;
import com.musabeli.api_laboratorio.entities.Laboratorio;
import com.musabeli.api_laboratorio.entities.ResultadoAnalisis;
import com.musabeli.api_laboratorio.exceptions.ResourceNotFoundException;
import com.musabeli.api_laboratorio.repositories.LaboratorioRepository;
import com.musabeli.api_laboratorio.repositories.ResultadoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ResultadoAnalisisServiceImplTest {

    @Mock
    private ResultadoRepository resultadoRepository;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @InjectMocks
    private ResultadoAnalisisServiceImpl service;

    // Objeto reutilizable para evitar repetir código
    private Laboratorio crearLaboratorioMock() {
        return Laboratorio.builder()
                .id(1L)
                .nombre("Lab Central")
                .direccion("Av. Siempre Viva 123")
                .telefono("987654321")
                .correo("contacto@labcentral.cl")
                .especialidad("Análisis Clínicos")
                .build();
    }

    private ResultadoAnalisis crearResultadoConLab(Long id, Laboratorio lab) {
        return ResultadoAnalisis.builder()
                .id(id)
                .fechaAnalisis(LocalDateTime.now())
                .nombreAnalisis("Hemograma completo")
                .resultado("Normal")
                .observaciones("Sin observaciones")
                .laboratorio(lab)
                .build();
    }

    @Test
    void createResultadoAnalisis_ok() {
        // Given
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setIdLaboratorio(1L);
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Hemograma");
        dto.setResultado("Normal");
        dto.setObservaciones("Todo bien");

        Laboratorio lab = crearLaboratorioMock();

        ResultadoAnalisis savedEntity = crearResultadoConLab(10L, lab);

        when(laboratorioRepository.findById(1L)).thenReturn(Optional.of(lab));
        when(resultadoRepository.save(any(ResultadoAnalisis.class))).thenReturn(savedEntity);

        // When
        ResponseResultadoDto result = service.createResultadoAnalisis(dto);

        // Then
        assertNotNull(result);
        assertEquals(10L, result.id());
        assertNotNull(result.laboratorio());
        assertEquals(1L, result.laboratorio().getId());

        verify(laboratorioRepository).findById(1L);
        verify(resultadoRepository).save(any());
    }

    @Test
    void createResultadoAnalisis_laboratorioNoEncontrado() {
        CreateResultadoDto dto = new CreateResultadoDto();
        dto.setIdLaboratorio(99L);
        dto.setFechaAnalisis(LocalDateTime.now());
        dto.setNombreAnalisis("Test");
        dto.setResultado("Test");

        when(laboratorioRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.createResultadoAnalisis(dto));
    }

    @Test
    void getResultadosAnalisis_ok() {
        Laboratorio lab = crearLaboratorioMock();
        ResultadoAnalisis r1 = crearResultadoConLab(1L, lab);
        ResultadoAnalisis r2 = crearResultadoConLab(2L, lab);

        when(resultadoRepository.findAll()).thenReturn(List.of(r1, r2));

        List<ResponseResultadoDto> result = service.getResultadosAnalisis();

        assertEquals(2, result.size());
        assertNotNull(result.get(0).laboratorio());
        verify(resultadoRepository).findAll();
    }

    @Test
    void getResultadoAnalisisById_ok() {
        Laboratorio lab = crearLaboratorioMock();
        ResultadoAnalisis r = crearResultadoConLab(5L, lab);

        when(resultadoRepository.findById(5L)).thenReturn(Optional.of(r));

        ResponseResultadoDto result = service.getResultadoAnalisisById(5L);

        assertNotNull(result);
        assertEquals(5L, result.id());
        assertNotNull(result.laboratorio());
        assertEquals("Lab Central", result.laboratorio().getNombre());
    }

    @Test
    void getResultadoAnalisisById_noExiste() {
        when(resultadoRepository.findById(5L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getResultadoAnalisisById(5L));
    }

    @Test
    void updateResultadoAnalisis_ok() {
        Laboratorio lab = crearLaboratorioMock();
        ResultadoAnalisis existente = crearResultadoConLab(3L, lab);
        existente.setNombreAnalisis("Antiguo");

        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setNombreAnalisis("Nuevo nombre actualizado");

        when(resultadoRepository.findById(3L)).thenReturn(Optional.of(existente));
        when(resultadoRepository.save(existente)).thenReturn(existente);

        ResponseResultadoDto result = service.updateResultadoAnalisis(3L, dto);

        assertEquals("Nuevo nombre actualizado", result.nombreAnalisis());
        assertNotNull(result.laboratorio());
        verify(resultadoRepository).save(existente);
    }

    @Test
    void updateResultadoAnalisis_laboratorioNoExiste() {
        Laboratorio labExistente = crearLaboratorioMock();
        ResultadoAnalisis existente = crearResultadoConLab(3L, labExistente);

        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setIdLaboratorio(999L);

        when(resultadoRepository.findById(3L)).thenReturn(Optional.of(existente));
        when(laboratorioRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.updateResultadoAnalisis(3L, dto));
    }

    @Test
    void deleteResultadoAnalisis_ok() {
        Laboratorio lab = crearLaboratorioMock();
        ResultadoAnalisis existente = crearResultadoConLab(7L, lab);

        when(resultadoRepository.findById(7L)).thenReturn(Optional.of(existente));

        ResponseResultadoDto result = service.deleteResultadoAnalisis(7L);

        assertEquals(7L, result.id());
        assertNotNull(result.laboratorio());
        verify(resultadoRepository).deleteById(7L);
    }

    @Test
    void deleteResultadoAnalisis_noExiste() {
        when(resultadoRepository.findById(7L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.deleteResultadoAnalisis(7L));
    }


    //sdfsdfdsf
    @Test
    void updateResultadoAnalisis_cambiaLaboratorio_ok() {
        // Given
        Laboratorio laboratorioAntiguo = crearLaboratorioMock(); // id = 1L
        Laboratorio laboratorioNuevo = Laboratorio.builder()
                .id(2L)
                .nombre("Lab Nuevo")
                .direccion("Calle Falsa 456")
                .telefono("123456789")
                .correo("nuevo@lab.cl")
                .especialidad("Hematología")
                .build();

        ResultadoAnalisis existente = crearResultadoConLab(3L, laboratorioAntiguo);
        existente.setNombreAnalisis("Antiguo");

        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setNombreAnalisis("Nombre actualizado");
        dto.setIdLaboratorio(2L); // ¡Aquí está la clave!

        when(resultadoRepository.findById(3L)).thenReturn(Optional.of(existente));
        when(laboratorioRepository.findById(2L)).thenReturn(Optional.of(laboratorioNuevo));
        when(resultadoRepository.save(existente)).thenReturn(existente);

        // When
        ResponseResultadoDto result = service.updateResultadoAnalisis(3L, dto);

        // Then
        assertEquals("Nombre actualizado", result.nombreAnalisis());
        assertNotNull(result.laboratorio());
        assertEquals(2L, result.laboratorio().getId());
        assertEquals("Lab Nuevo", result.laboratorio().getNombre());

        verify(laboratorioRepository).findById(2L);
        verify(resultadoRepository).save(existente);
    }

    @Test
    void updateResultadoAnalisis_laboratorioInexistente_lanzaExcepcion() {
        // Given
        Laboratorio labActual = crearLaboratorioMock();
        ResultadoAnalisis existente = crearResultadoConLab(5L, labActual);

        UpdateResultadoDto dto = new UpdateResultadoDto();
        dto.setIdLaboratorio(999L); // Laboratorio que no existe

        when(resultadoRepository.findById(5L)).thenReturn(Optional.of(existente));
        when(laboratorioRepository.findById(999L)).thenReturn(Optional.empty());

        // When & Then
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> service.updateResultadoAnalisis(5L, dto)
        );

        assertTrue(exception.getMessage().contains("Laboratorio"));
        verify(laboratorioRepository).findById(999L);
        verify(resultadoRepository, never()).save(any()); // No debe guardar si falla el lab
    }


    @Test
    void updateResultadoAnalisis_conDtoTotalmenteVacio_noModificaNada() {
        // Given
        Laboratorio labOriginal = crearLaboratorioMock();
        ResultadoAnalisis existente = crearResultadoConLab(8L, labOriginal);
        existente.setFechaAnalisis(LocalDateTime.of(2025, 1, 1, 10, 0));
        existente.setNombreAnalisis("Análisis original");
        existente.setResultado("Resultado original");
        existente.setObservaciones("Obs originales");

        UpdateResultadoDto dtoVacio = new UpdateResultadoDto();
        // ¡Importante! No seteamos NADA → todos los getters devuelven null

        when(resultadoRepository.findById(8L)).thenReturn(Optional.of(existente));
        when(resultadoRepository.save(existente)).thenReturn(existente);

        // When
        ResponseResultadoDto response = service.updateResultadoAnalisis(8L, dtoVacio);

        // Then - NADA debe haber cambiado
        assertEquals("Análisis original", response.nombreAnalisis());
        assertEquals("Resultado original", response.resultado());
        assertEquals("Obs originales", response.observaciones());
        assertEquals(LocalDateTime.of(2025, 1, 1, 10, 0), response.fechaAnalisis());
        assertEquals(1L, response.laboratorio().getId());

        // Verifica que NO se llamó al repositorio de laboratorio
        verify(laboratorioRepository, never()).findById(any());
        verify(resultadoRepository).save(existente); // sí se guarda (por si hay otros cambios)
    }

}
