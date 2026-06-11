package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.adapters.UserAdapter;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.LoginRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.LoginResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.Usuario;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.UsuarioDetailDTO;
import br.edu.ifpr.bsi.projetoexemplo.repositories.AlunoRepository;
import br.edu.ifpr.bsi.projetoexemplo.repositories.InstrutorRepository;
import br.edu.ifpr.bsi.projetoexemplo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private TokenService tokenService;

    // @Lazy evita dependência circular com SecurityConfig
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) {

        System.out.println("PROCURANDO: " + username);

        Aluno aluno = alunoRepository.findByUsername(username);

        if (aluno != null) {
            System.out.println("ALUNO ENCONTRADO");
            return new UserAdapter(aluno);
        }

        Instrutor instrutor = instrutorRepository.findByUsername(username);

        if (instrutor != null) {
            System.out.println("INSTRUTOR ENCONTRADO");
            return new UserAdapter(instrutor);
        }

        System.out.println("NAO ENCONTROU");

        throw new UsernameNotFoundException(
                "Usuário não encontrado: " + username
        );
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        // 1. Cria o token de autenticação com username + senha em texto plano
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                request.username(),
                request.senha()
        );

        // 2. O AuthenticationManager valida contra o banco (usa loadUserByUsername + BCrypt)
        Authentication auth = authenticationManager.authenticate(authToken);
        UserAdapter adapter = (UserAdapter) auth.getPrincipal();

        // 3. Gera o JWT
        String token = tokenService.gerarToken(adapter);

        // 4. Monta o DTO de resposta com os dados do usuário e o token
        Usuario usuario = adapter.getUsuario();
        UsuarioDetailDTO usuarioDTO = new UsuarioDetailDTO(
                usuario.getCodigo(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getRole()
        );

        return new LoginResponseDTO(usuarioDTO, token);
    }
}