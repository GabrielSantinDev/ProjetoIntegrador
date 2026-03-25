package br.edu.ifpr.bsi.projetoexemplo.model.aluno;

import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_aluno")
public class Aluno extends Usuario {

    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas = new ArrayList<>();;

    @Column(name = "nivel_prendizado")
    private String nivelAprendizado;

    @OneToMany(mappedBy = "aluno")
    private List<Avaliacao> avaliacoes = new ArrayList<>();;

}
