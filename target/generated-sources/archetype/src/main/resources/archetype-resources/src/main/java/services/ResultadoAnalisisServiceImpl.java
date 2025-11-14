#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.services;

import ${package}.dtos.CreateResultadoDto;
import ${package}.dtos.ResponseResultadoDto;
import ${package}.dtos.UpdateResultadoDto;
import ${package}.entities.Laboratorio;
import ${package}.entities.ResultadoAnalisis;
import ${package}.exceptions.ResourceNotFoundException;
import ${package}.mapper.ResultadoMapper;
import ${package}.repositories.LaboratorioRepository;
import ${package}.repositories.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultadoAnalisisServiceImpl implements ResultadoAnalisisService {

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private LaboratorioRepository laboratorioRepository;


    private ResultadoAnalisis findResultadoById(Long id){
        Optional<ResultadoAnalisis> resultado = this.resultadoRepository.findById(id);

        if (resultado.isPresent()){
            return resultado.get();
        }
        else {
            throw new ResourceNotFoundException("No existen registro de análisis con id " + id);
        }
    }


    @Override
    public ResponseResultadoDto createResultadoAnalisis(CreateResultadoDto resultadoDto) {
        // buscar laboratorio
        Laboratorio laboratorio = laboratorioRepository.findById(resultadoDto.getIdLaboratorio())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Laboratorio no encontrado con ID: " + resultadoDto.getIdLaboratorio()));

        // dto a entidad
        ResultadoAnalisis resultado = ResultadoMapper.fromCreateResultado(resultadoDto, laboratorio);


        ResultadoAnalisis resultadoBd = this.resultadoRepository.save(resultado);

        return ResultadoMapper.toResponseResultadoDto(resultadoBd);
    }

    @Override
    public List<ResponseResultadoDto> getResultadosAnalisis() {
        // transformar entidad a dto usando mapper
        return resultadoRepository.findAll().stream()
                .map(ResultadoMapper::toResponseResultadoDto)
                .collect(Collectors.toList());

    }

    @Override
    public ResponseResultadoDto getResultadoAnalisisById(Long id) {
        // buscar resultado
        ResultadoAnalisis resultado = this.findResultadoById(id);

        return ResultadoMapper.toResponseResultadoDto(resultado);
    }

    @Override
    public ResponseResultadoDto updateResultadoAnalisis(Long id, UpdateResultadoDto resultadoDto) {
        ResultadoAnalisis resultado = resultadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado con id " + id + "no encontrado"));

        if (resultadoDto.getFechaAnalisis() != null){
            resultado.setFechaAnalisis(resultadoDto.getFechaAnalisis());
        }
        if (resultadoDto.getNombreAnalisis() != null){
            resultado.setNombreAnalisis(resultadoDto.getNombreAnalisis());
        }
        if (resultadoDto.getResultado() != null){
            resultado.setResultado(resultadoDto.getResultado());
        }
        if (resultadoDto.getObservaciones() != null){
            resultado.setObservaciones(resultadoDto.getObservaciones());
        }
        if (resultadoDto.getIdLaboratorio() != null){
            // encontrar un laboratorio
            Laboratorio laboratorio = laboratorioRepository.findById(resultadoDto.getIdLaboratorio())
                    .orElseThrow(() -> new ResourceNotFoundException("Laboratorio con id " + resultadoDto.getIdLaboratorio() + " no encontrado"));

            resultado.setLaboratorio(laboratorio);
        }

        // guardar en bd
        ResultadoAnalisis resultadoActualizado = resultadoRepository.save(resultado);

        // debe devolver un dto
        return ResultadoMapper.toResponseResultadoDto(resultadoActualizado);
    }

    @Override
    public ResponseResultadoDto deleteResultadoAnalisis(Long id) {
        ResultadoAnalisis deleteResultado = this.findResultadoById(id);

        this.resultadoRepository.deleteById(id);
        return ResultadoMapper.toResponseResultadoDto(deleteResultado);
    }
}