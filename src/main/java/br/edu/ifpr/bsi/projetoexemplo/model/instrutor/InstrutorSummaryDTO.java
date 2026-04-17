package br.edu.ifpr.bsi.projetoexemplo.model.instrutor;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Instrutor}
 */
@Value
public class InstrutorSummaryDTO implements Serializable {
    Long codigo;
    String nome;
    String email;
    LocalDate dataNascimento;
    LocalDateTime dataCadastro;
    String especializacao;
    String descricao;
    Double avaliacao;
}