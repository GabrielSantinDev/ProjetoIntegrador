package br.edu.ifpr.bsi.projetoexemplo.components;

import br.edu.ifpr.bsi.projetoexemplo.adapters.UserAdapter;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.Usuario;
import br.edu.ifpr.bsi.projetoexemplo.repositories.AlunoRepository;
import br.edu.ifpr.bsi.projetoexemplo.repositories.InstrutorRepository;
import br.edu.ifpr.bsi.projetoexemplo.repositories.UsuarioRepository;
import br.edu.ifpr.bsi.projetoexemplo.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {

        String token = recuperarToken(request);

        if (token != null) {
            String username = tokenService.validarToken(token);

            if (username != null && !username.isEmpty()) {

                Aluno aluno = alunoRepository.findByUsername(username);

                if (aluno != null) {
                    setAuth(new UserAdapter(aluno));
                } else {
                    Instrutor instrutor =
                            instrutorRepository.findByUsername(username);

                    if (instrutor != null) {
                        setAuth(new UserAdapter(instrutor));
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;
        return authHeader.replace("Bearer ", "");
    }

    private void setAuth(UserAdapter adapter) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                adapter,
                null,
                adapter.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}