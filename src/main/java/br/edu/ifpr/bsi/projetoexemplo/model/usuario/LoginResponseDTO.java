package br.edu.ifpr.bsi.projetoexemplo.model.usuario;

// O que o back devolve após login bem-sucedido:
// { usuario: { id, nome, email, role }, token: "eyJ..." }
public record LoginResponseDTO(
        UsuarioDetailDTO usuario,
        String token
) {}