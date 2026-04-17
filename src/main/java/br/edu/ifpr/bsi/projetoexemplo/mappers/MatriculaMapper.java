package br.edu.ifpr.bsi.projetoexemplo.mappers;

import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.MatriculaRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.MatriculaResponseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MatriculaMapper {
    Matricula toEntity(MatriculaResponseDTO matriculaResponseDTO);

    MatriculaResponseDTO toDto(Matricula matricula);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Matricula partialUpdate(MatriculaResponseDTO matriculaResponseDTO, @MappingTarget Matricula matricula);

    @Mapping(source = "alunoCodigo", target = "aluno.codigo")
    @Mapping(source = "cursoCodigo", target = "curso.codigo")
    Matricula toEntity(MatriculaRequestDTO matriculaRequestDTO);

    @InheritInverseConfiguration(name = "toEntity")
    MatriculaRequestDTO toDto1(Matricula matricula);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Matricula partialUpdate(MatriculaRequestDTO matriculaRequestDTO, @MappingTarget Matricula matricula);
}