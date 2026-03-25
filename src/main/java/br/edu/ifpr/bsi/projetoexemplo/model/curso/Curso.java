package br.edu.ifpr.bsi.projetoexemplo.model.curso;

import br.edu.ifpr.bsi.projetoexemplo.model.GenericModel;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_curso")
public class Curso extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas = new ArrayList<>();;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "horas_duracao")
    private Double horasDuracao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "descricao")
    private String descricao;

    @OneToMany(mappedBy = "curso")
    private List<Avaliacao> avaliacoes = new ArrayList<>();;

}
