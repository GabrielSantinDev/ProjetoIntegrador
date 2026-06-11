package br.edu.ifpr.bsi.projetoexemplo.model.usuario;

// dto de entrada do login: o front envia username e senha
public record LoginRequestDTO(
        String username,
        String senha
) {}