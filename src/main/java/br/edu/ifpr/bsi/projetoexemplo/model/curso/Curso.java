package br.edu.ifpr.bsi.projetoexemplo.model.curso;

import br.edu.ifpr.bsi.projetoexemplo.model.GenericModel;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_curso")
public class Curso extends GenericModel {

    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

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

}
