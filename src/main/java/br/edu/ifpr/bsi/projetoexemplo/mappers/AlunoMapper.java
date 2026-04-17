package br.edu.ifpr.bsi.projetoexemplo.mappers;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoDetailDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoSummaryDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AlunoMapper {
    Aluno toEntity(AlunoRequestDTO alunoRequestDTO);

    AlunoRequestDTO toDto(Aluno aluno);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Aluno partialUpdate(AlunoRequestDTO alunoRequestDTO, @MappingTarget Aluno aluno);

    Aluno toEntity(AlunoDetailDTO alunoDetailDTO);

    @AfterMapping
    default void linkMatriculas(@MappingTarget Aluno aluno) {
        aluno.getMatriculas().forEach(matricula -> matricula.setAluno(aluno));
    }

    @AfterMapping
    default void linkAvaliacoes(@MappingTarget Aluno aluno) {
        aluno.getAvaliacoes().forEach(avaliacoes -> avaliacoes.setAluno(aluno));
    }

    AlunoDetailDTO toDto1(Aluno aluno);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Aluno partialUpdate(AlunoDetailDTO alunoDetailDTO, @MappingTarget Aluno aluno);

    Aluno toEntity(AlunoSummaryDTO alunoSummaryDTO);

    AlunoSummaryDTO toDto2(Aluno aluno);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Aluno partialUpdate(AlunoSummaryDTO alunoSummaryDTO, @MappingTarget Aluno aluno);
}