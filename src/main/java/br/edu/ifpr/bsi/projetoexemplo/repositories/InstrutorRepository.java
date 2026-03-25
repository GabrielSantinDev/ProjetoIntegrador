package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    List<Instrutor> findByNome(String nome);
    List<Instrutor> findByEmail(String email);

    @Query(value="SELECT i FROM Instrutor i WHERE i.nome LIKE %:nome%")
    List<Instrutor> getAllByNomeLike(@Param("nome") String nome);

}
