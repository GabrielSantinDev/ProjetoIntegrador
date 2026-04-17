package br.edu.ifpr.bsi.projetoexemplo.mappers;

import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorDetailDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorSummaryDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InstrutorMapper {
    Instrutor toEntity(InstrutorDetailDTO instrutorDetailDTO);

    InstrutorDetailDTO toDto(Instrutor instrutor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Instrutor partialUpdate(InstrutorDetailDTO instrutorDetailDTO, @MappingTarget Instrutor instrutor);

    Instrutor toEntity(InstrutorRequestDTO instrutorRequestDTO);

    InstrutorRequestDTO toDto1(Instrutor instrutor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Instrutor partialUpdate(InstrutorRequestDTO instrutorRequestDTO, @MappingTarget Instrutor instrutor);

    Instrutor toEntity(InstrutorSummaryDTO instrutorSummaryDTO);

    InstrutorSummaryDTO toDto2(Instrutor instrutor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Instrutor partialUpdate(InstrutorSummaryDTO instrutorSummaryDTO, @MappingTarget Instrutor instrutor);
}