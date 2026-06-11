package br.edu.ifpr.bsi.projetoexemplo.model.usuario;

import br.edu.ifpr.bsi.projetoexemplo.enums.Role;
import br.edu.ifpr.bsi.projetoexemplo.model.GenericModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class Usuario extends GenericModel {

    @Column(name = "username", unique = true)
    private String username;

    //  define se é instrutor ou aluno
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public String getPassword() {
        return this.senha;
    }

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

}
