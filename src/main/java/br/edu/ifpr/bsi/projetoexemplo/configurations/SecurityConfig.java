package br.edu.ifpr.bsi.projetoexemplo.configurations;

import br.edu.ifpr.bsi.projetoexemplo.components.JwtAuthenticationFilter;
import br.edu.ifpr.bsi.projetoexemplo.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {

                    // ── Rotas públicas ──────────────────────────────────────────────
                    req.requestMatchers("/error").permitAll();
                    req.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();

                    // Cadastro e login são públicos
                    req.requestMatchers(HttpMethod.POST, "/auth").permitAll();
                    req.requestMatchers(HttpMethod.POST, "/instrutores", "/alunos").permitAll();

                    // ── Rotas do INSTRUTOR ──────────────────────────────────────────
                    // Instrutores gerenciam seus próprios cursos
                    req.requestMatchers(HttpMethod.POST, "/cursos").hasAuthority(Role.INSTRUTOR.name());
                    req.requestMatchers(HttpMethod.PUT, "/cursos/**").hasAuthority(Role.INSTRUTOR.name());
                    req.requestMatchers(HttpMethod.DELETE, "/cursos/**").hasAuthority(Role.INSTRUTOR.name());

                    // ── Rotas do ALUNO ──────────────────────────────────────────────
                    // Alunos fazem matrículas e avaliações
                    req.requestMatchers(HttpMethod.POST, "/matriculas").hasAuthority(Role.ALUNO.name());
                    req.requestMatchers(HttpMethod.POST, "/avaliacoes").hasAuthority(Role.ALUNO.name());

                    // ── Rotas compartilhadas (qualquer autenticado) ─────────────────
                    // Listar cursos é público; detalhes exigem login
                    req.requestMatchers(HttpMethod.GET, "/cursos", "/cursos/**").authenticated();
                    req.requestMatchers(HttpMethod.GET, "/instrutores", "/instrutores/**").authenticated();
                    req.requestMatchers(HttpMethod.GET, "/alunos/**").authenticated();

                    // Qualquer rota não mapeada acima exige autenticação
                    req.anyRequest().authenticated();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://localhost:5173",
                "https://skill-up-do98.onrender.com"
        ));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}