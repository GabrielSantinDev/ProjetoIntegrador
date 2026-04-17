package br.edu.ifpr.bsi.projetoexemplo.mappers;

import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.AvaliacaoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.AvaliacaoResponseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AvaliacaoMapper {
    Avaliacao toEntity(AvaliacaoResponseDTO avaliacaoResponseDTO);

    AvaliacaoResponseDTO toDto(Avaliacao avaliacao);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Avaliacao partialUpdate(AvaliacaoResponseDTO avaliacaoResponseDTO, @MappingTarget Avaliacao avaliacao);

    @Mapping(source = "alunoCodigo", target = "aluno.codigo")
    @Mapping(source = "cursoCodigo", target = "curso.codigo")
    Avaliacao toEntity(AvaliacaoRequestDTO avaliacaoRequestDTO);

    @InheritInverseConfiguration(name = "toEntity")
    AvaliacaoRequestDTO toDto1(Avaliacao avaliacao);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Avaliacao partialUpdate(AvaliacaoRequestDTO avaliacaoRequestDTO, @MappingTarget Avaliacao avaliacao);
}