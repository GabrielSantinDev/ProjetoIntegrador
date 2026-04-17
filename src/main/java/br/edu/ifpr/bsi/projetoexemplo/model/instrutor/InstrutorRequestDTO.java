package br.edu.ifpr.bsi.projetoexemplo.model.instrutor;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link Instrutor}
 */
@Value
public class InstrutorRequestDTO implements Serializable {
    String nome;
    String email;
    String senha;
    LocalDate dataNascimento;
    LocalDateTime dataCadastro;
    String especializacao;
    String descricao;
    Double avaliacao;
    Double ganhosTotais;
}