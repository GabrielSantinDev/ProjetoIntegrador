package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    private Curso criarCurso(String titulo, String categoria) {
        Curso curso = new Curso();
        curso.setTitulo(titulo);
        curso.setCategoria(categoria);
        curso.setHorasDuracao(20.0);
        curso.setPreco(199.90);
        curso.setDescricao("Curso de " + titulo);
        return curso;
    }

    @Test
    public void testInsert() {
        Curso curso = criarCurso("Java Completo", "Programacao");

        curso = cursoRepository.save(curso);

        Curso cursoInserido = cursoRepository.findById(curso.getCodigo()).get();

        Assertions.assertNotNull(cursoInserido, "Curso nao foi inserido.");
    }

    @Test
    public void testUpdate() {
        Curso curso = criarCurso("Java Completo", "Programacao");
        Curso cursoAlterar = cursoRepository.save(curso);

        cursoAlterar.setPreco(299.90);

        cursoAlterar = cursoRepository.save(cursoAlterar);

        Curso cursoAlterado = cursoRepository.findById(cursoAlterar.getCodigo()).get();

        Assertions.assertEquals(299.90, cursoAlterado.getPreco());
    }

    @Test
    public void testDelete() {
        Curso curso = criarCurso("Java Completo", "Programacao");
        Curso cursoDeletar = cursoRepository.save(curso);

        cursoRepository.delete(cursoDeletar);

        Curso cursoDeletado = cursoRepository.findById(curso.getCodigo()).orElse(null);

        Assertions.assertNull(cursoDeletado, "O curso ainda esta no banco de dados.");
    }

    @Test
    public void testFindAll() {
        cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        cursoRepository.save(criarCurso("Spring Boot", "Programacao"));
        cursoRepository.save(criarCurso("Banco de Dados", "Tecnologia"));

        List<Curso> cursos = cursoRepository.findAll();

        Assertions.assertFalse(cursos.isEmpty(), "A consulta nao retornou resultados.");
    }

    @Test
    public void testGetAllByNomeLike() {
        cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        cursoRepository.save(criarCurso("Java Web", "Programacao"));
        cursoRepository.save(criarCurso("Banco de Dados", "Tecnologia"));

        List<Curso> cursos = cursoRepository.getAllByTituloLike("Java");

        Assertions.assertNotNull(cursos);
        Assertions.assertEquals(2, cursos.size());
    }
}