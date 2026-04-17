package br.edu.ifpr.bsi.projetoexemplo.model.matricula;

import br.edu.ifpr.bsi.projetoexemplo.model.curso.CursoResponseDTO;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Matricula}
 */
@Value
public class MatriculaResponseDTO implements Serializable {
    Long codigo;
    CursoResponseDTO curso;
    Long alunoCodigo;
    Double porcentagemProgresso;
    Boolean concluido;
    LocalDateTime dataMatricula;
}