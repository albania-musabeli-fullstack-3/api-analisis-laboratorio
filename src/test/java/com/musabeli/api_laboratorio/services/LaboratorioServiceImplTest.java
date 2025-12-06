package com.musabeli.api_laboratorio.services;

import com.musabeli.api_laboratorio.dtos.CreateLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.ResponseLaboratorioDto;
import com.musabeli.api_laboratorio.dtos.UpdateLaboratorioDto;
import com.musabeli.api_laboratorio.entities.Laboratorio;
import com.musabeli.api_laboratorio.entities.ResultadoAnalisis;
import com.musabeli.api_laboratorio.exceptions.ResourceNotFoundException;
import com.musabeli.api_laboratorio.repositories.LaboratorioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaboratorioServiceImplTest {

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @InjectMocks
    private LaboratorioServiceImpl laboratorioService;

    private static final Long ID = 1L;
    private static final Long ID_INEXISTENTE = 999L;

    @Test
    void createLaboratorio_debeCrearYRetornarDtoConTodosLosCampos() {
        CreateLaboratorioDto dto = new CreateLaboratorioDto();
        dto.setNombre("Laboratorio Sur");
        dto.setDireccion("Calle Sur 456");
        dto.setTelefono("555666777");
        dto.setCorreo("sur@lab.com");
        dto.setEspecialidad("Microbiología");

        Laboratorio entidadGuardada = new Laboratorio();
        entidadGuardada.setId(ID);
        entidadGuardada.setNombre(dto.getNombre());
        entidadGuardada.setDireccion(dto.getDireccion());
        entidadGuardada.setTelefono(dto.getTelefono());
        entidadGuardada.setCorreo(dto.getCorreo());
        entidadGuardada.setEspecialidad(dto.getEspecialidad());

        when(laboratorioRepository.save(any(Laboratorio.class))).thenReturn(entidadGuardada);

        ResponseLaboratorioDto resultado = laboratorioService.createLaboratorio(dto);

        assertNotNull(resultado);
        assertEquals(ID, resultado.getId());
        assertEquals("Laboratorio Sur", resultado.getNombre());
        assertEquals("sur@lab.com", resultado.getCorreo());
        assertEquals("Microbiología", resultado.getEspecialidad());
        verify(laboratorioRepository).save(any(Laboratorio.class));
    }

    @Test
    void getLaboratorios_debeRetornarListaCompleta() {
        Laboratorio lab = new Laboratorio();
        lab.setId(10L);
        lab.setNombre("Lab Norte");
        lab.setCorreo("norte@lab.com");
        lab.setEspecialidad("Hematología");

        when(laboratorioRepository.findAll()).thenReturn(List.of(lab));

        List<ResponseLaboratorioDto> lista = laboratorioService.getLaboratorios();

        assertEquals(1, lista.size());
        assertEquals("Lab Norte", lista.get(0).getNombre());
        assertEquals("norte@lab.com", lista.get(0).getCorreo());
    }

    @Test
    void getLaboratorioById_existente_debeRetornarDtoCompleto() {
        Laboratorio entidad = new Laboratorio();
        entidad.setId(ID);
        entidad.setNombre("Lab Central");
        entidad.setCorreo("central@lab.com");
        entidad.setEspecialidad("Bioquímica");

        when(laboratorioRepository.findById(ID)).thenReturn(Optional.of(entidad));

        ResponseLaboratorioDto dto = laboratorioService.getLaboratorioById(ID);

        assertEquals(ID, dto.getId());
        assertEquals("central@lab.com", dto.getCorreo());
        assertEquals("Bioquímica", dto.getEspecialidad());
    }

    @Test
    void getLaboratorioById_inexistente_debeLanzarResourceNotFoundException() {
        when(laboratorioRepository.findById(ID_INEXISTENTE)).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> laboratorioService.getLaboratorioById(ID_INEXISTENTE)
        );

        assertTrue(ex.getMessage().contains(String.valueOf(ID_INEXISTENTE)));
    }

    @Test
    void updateLaboratorio_debeActualizarCamposNoNulos() {
        Laboratorio existente = new Laboratorio();
        existente.setId(ID);
        existente.setNombre("Nombre viejo");
        existente.setCorreo("viejo@correo.com");
        existente.setEspecialidad("Vieja");

        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();
        dto.setNombre("Nombre nuevo");
        dto.setTelefono("111222333");
        dto.setEspecialidad("Nueva especialidad");
        // correo y dirección se dejan null → no se tocan

        when(laboratorioRepository.findById(ID)).thenReturn(Optional.of(existente));
        when(laboratorioRepository.save(any(Laboratorio.class))).thenAnswer(i -> i.getArgument(0));

        ResponseLaboratorioDto actualizado = laboratorioService.updateLaboratorio(ID, dto);

        assertEquals("Nombre nuevo", actualizado.getNombre());
        assertEquals("111222333", actualizado.getTelefono());
        assertEquals("Nueva especialidad", actualizado.getEspecialidad());
        assertEquals("viejo@correo.com", actualizado.getCorreo()); // se mantiene
    }

    @Test
    void updateLaboratorio_inexistente_debeLanzarExcepcion() {
        UpdateLaboratorioDto dto = new UpdateLaboratorioDto();

        when(laboratorioRepository.findById(ID_INEXISTENTE)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> laboratorioService.updateLaboratorio(ID_INEXISTENTE, dto));
    }

    @Test
    void deleteLaboratorio_sinResultados_debeEliminarCorrectamente() {
        Laboratorio lab = new Laboratorio();
        lab.setId(ID);
        lab.setResultados(List.of()); // vacío

        when(laboratorioRepository.findById(ID)).thenReturn(Optional.of(lab));

        ResponseLaboratorioDto eliminado = laboratorioService.deleteLaboratorio(ID);

        assertEquals(ID, eliminado.getId());
        verify(laboratorioRepository).deleteById(ID);
    }

    @Test
    void deleteLaboratorio_conResultados_debeLanzarIllegalStateException() {
        Laboratorio lab = Laboratorio.builder()
                .id(ID)
                .resultados(List.of(new ResultadoAnalisis())) // tiene al menos uno
                .build();

        when(laboratorioRepository.findById(ID)).thenReturn(Optional.of(lab));

        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> laboratorioService.deleteLaboratorio(ID)
        );

        assertEquals("No se puede eliminar el laboratorio porque tiene resultados asociados", ex.getMessage());
        verify(laboratorioRepository, never()).deleteById(anyLong());
    }

    @Test
    void deleteLaboratorio_inexistente_debeLanzarResourceNotFoundException() {
        when(laboratorioRepository.findById(ID_INEXISTENTE)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> laboratorioService.deleteLaboratorio(ID_INEXISTENTE));
    }

}