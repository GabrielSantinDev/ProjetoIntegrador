package br.edu.ifpr.bsi.projetoexemplo.model.matricula;

import br.edu.ifpr.bsi.projetoexemplo.model.GenericModel;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_matricula")
public class Matricula extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "porcentagem_progresso")
    private Double porcentagemProgresso;

    @Column(name = "concluido")
    private Boolean concluido;

    @Column(name = "data_matricula")
    private LocalDateTime dataMatricula;
}
