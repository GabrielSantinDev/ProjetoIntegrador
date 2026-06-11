package br.edu.ifpr.bsi.projetoexemplo.model.curso;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Curso}
 */
@Value
public class CursoRequestDTO implements Serializable {
    Long instrutorCodigo;
    String titulo;
    String categoria;
    Double horasDuracao;
    Double preco;
    String descricao;
    Boolean publicado;
    String urlImagem;
}