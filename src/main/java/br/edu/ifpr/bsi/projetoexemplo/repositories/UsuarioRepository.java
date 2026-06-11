package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}