package br.edu.ifpr.bsi.projetoexemplo.model.usuario;

import br.edu.ifpr.bsi.projetoexemplo.enums.Role;

// Dados do usuário retornados após login
// O front vai guardar isso no Redux (usuarioLogado)
public record UsuarioDetailDTO(
        Long id,
        String nome,
        String email,
        String username,
        Role role          // "INSTRUTOR" ou "ALUNO"
) {}