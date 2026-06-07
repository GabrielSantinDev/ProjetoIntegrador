package br.edu.ifpr.bsi.projetoexemplo.model.curso;

import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorDetailDTO;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Curso}
 */
@Value
public class CursoResponseDTO implements Serializable {
    Long codigo;
    InstrutorDetailDTO instrutor;
    List<Long> matriculaCodigos;
    String titulo;
    String categoria;
    Double horasDuracao;
    Double preco;
    String descricao;
    List<Long> avaliacaoCodigos;
    Boolean publicado;
}