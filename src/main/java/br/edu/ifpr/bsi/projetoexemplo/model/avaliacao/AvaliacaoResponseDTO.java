package br.edu.ifpr.bsi.projetoexemplo.model.avaliacao;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Avaliacao}
 */
@Value
public class AvaliacaoResponseDTO implements Serializable {
    Long codigo;
    Long cursoCodigo;
    Long alunoCodigo;
    String descricao;
    Double valor;
}