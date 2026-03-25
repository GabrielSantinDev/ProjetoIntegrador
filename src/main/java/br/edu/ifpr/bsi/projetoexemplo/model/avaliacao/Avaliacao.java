package br.edu.ifpr.bsi.projetoexemplo.model.avaliacao;


import br.edu.ifpr.bsi.projetoexemplo.model.GenericModel;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao  extends GenericModel {

    @OneToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @JoinColumn(name = "descricao")
    private String descricao;

    @JoinColumn(name = "valor")
    private Double valor;
}
