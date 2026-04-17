package br.edu.ifpr.bsi.projetoexemplo.model.aluno;

import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.AvaliacaoResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.MatriculaResponseDTO;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link Aluno}
 */
@Value
public class AlunoDetailDTO implements Serializable {
    Long codigo;
    String nome;
    String email;
    LocalDate dataNascimento;
    LocalDateTime dataCadastro;
    List<MatriculaResponseDTO> matriculas;
    String nivelAprendizado;
    List<AvaliacaoResponseDTO> avaliacoes;
}