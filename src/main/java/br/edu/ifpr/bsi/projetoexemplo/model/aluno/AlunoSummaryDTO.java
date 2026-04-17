package br.edu.ifpr.bsi.projetoexemplo.model.aluno;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Aluno}
 */
@Value
public class AlunoSummaryDTO implements Serializable {
    Long codigo;
    String nome;
    String email;
    LocalDate dataNascimento;
    LocalDateTime dataCadastro;
    String nivelAprendizado;
}