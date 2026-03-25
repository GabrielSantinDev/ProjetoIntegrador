package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    List<Aluno> findByNome(String nome);
    List<Aluno> findByEmail(String email);

    @Query(value="SELECT a FROM Aluno a WHERE a.nome LIKE %:nome%")
    List<Aluno> getAllByNomeLike(@Param("nome") String nome);
}
