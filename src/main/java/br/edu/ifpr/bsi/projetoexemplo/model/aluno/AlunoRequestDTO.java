package br.edu.ifpr.bsi.projetoexemplo.model.aluno;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Aluno}
 */
@Value
public class AlunoRequestDTO implements Serializable {
    String nome;
    String email;
    String senha;
    LocalDate dataNascimento;
    LocalDateTime dataCadastro;
    String nivelAprendizado;
    String username;
}