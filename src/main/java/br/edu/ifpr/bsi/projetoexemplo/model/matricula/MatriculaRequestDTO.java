package br.edu.ifpr.bsi.projetoexemplo.model.matricula;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Matricula}
 */
@Value
public class MatriculaRequestDTO implements Serializable {
    Long cursoCodigo;
    Long alunoCodigo;
    Double porcentagemProgresso;
    Boolean concluido;
    LocalDateTime dataMatricula;
}