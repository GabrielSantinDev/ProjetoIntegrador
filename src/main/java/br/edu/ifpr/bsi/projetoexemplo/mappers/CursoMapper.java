package br.edu.ifpr.bsi.projetoexemplo.mappers;

import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.CursoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.CursoResponseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CursoMapper {
    Curso toEntity(CursoResponseDTO cursoResponseDTO);

    CursoResponseDTO toDto(Curso curso);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Curso partialUpdate(CursoResponseDTO cursoResponseDTO, @MappingTarget Curso curso);

    @Mapping(source = "instrutorCodigo", target = "instrutor.codigo")
    Curso toEntity(CursoRequestDTO cursoRequestDTO);

    @Mapping(source = "instrutor.codigo", target = "instrutorCodigo")
    CursoRequestDTO toDto1(Curso curso);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "instrutorCodigo", target = "instrutor.codigo")
    Curso partialUpdate(CursoRequestDTO cursoRequestDTO, @MappingTarget Curso curso);
}