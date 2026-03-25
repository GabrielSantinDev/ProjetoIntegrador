package br.edu.ifpr.bsi.projetoexemplo.model.instrutor;

import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_instrutor")
public class Instrutor extends Usuario {

    @OneToMany(mappedBy = "instrutor")
    private List<Curso> cursosCriados = new ArrayList<>();;

    @Column(name = "especializacao")
    private String especializacao;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "avaliacao")
    private Double avaliacao;

    @Column(name = "ganhos_totais")
    private Double ganhosTotais;

}
