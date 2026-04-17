package br.edu.ifpr.bsi.projetoexemplo.model.instrutor;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor}
 */
@Value
public class InstrutorDetailDTO implements Serializable {
    Long codigo;
    String nome;
    String email;
    String senha;
    LocalDate dataNascimento;
    LocalDateTime dataCadastro;
    List<Long> cursosCriadoCodigos;
    String especializacao;
    String descricao;
    Double avaliacao;
    Double ganhosTotais;
}