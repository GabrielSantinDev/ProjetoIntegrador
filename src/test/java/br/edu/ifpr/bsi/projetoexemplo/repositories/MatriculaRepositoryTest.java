package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class MatriculaRepositoryTest {

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    private Aluno criarAluno(String nome, String email) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setEmail(email);
        aluno.setSenha("123456");
        aluno.setDataNascimento(LocalDate.of(2000, 1, 1));
        aluno.setDataCadastro(LocalDateTime.now());
        aluno.setNivelAprendizado("Iniciante");
        return aluno;
    }

    private Curso criarCurso(String titulo, String categoria) {
        Curso curso = new Curso();
        curso.setTitulo(titulo);
        curso.setCategoria(categoria);
        curso.setHorasDuracao(20.0);
        curso.setPreco(199.90);
        curso.setDescricao("Curso de " + titulo);
        return curso;
    }

    private Matricula criarMatricula(Aluno aluno, Curso curso) {
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        matricula.setPorcentagemProgresso(10.0);
        matricula.setConcluido(false);
        matricula.setDataMatricula(LocalDateTime.now());
        return matricula;
    }

    @Test
    public void testInsert() {
        Aluno aluno = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Curso curso = cursoRepository.save(criarCurso("Java Completo", "Programacao"));

        Matricula matricula = criarMatricula(aluno, curso);

        matricula = matriculaRepository.save(matricula);

        Matricula matriculaInserida = matriculaRepository.findById(matricula.getCodigo()).get();

        Assertions.assertNotNull(matriculaInserida, "Matricula nao foi inserida.");
        Assertions.assertNotNull(matriculaInserida.getAluno(), "Aluno nao foi associado.");
        Assertions.assertNotNull(matriculaInserida.getCurso(), "Curso nao foi associado.");
    }

    @Test
    public void testUpdate() {
        Aluno aluno = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Curso curso = cursoRepository.save(criarCurso("Java Completo", "Programacao"));

        Matricula matricula = matriculaRepository.save(criarMatricula(aluno, curso));

        matricula.setPorcentagemProgresso(75.0);
        matricula.setConcluido(true);

        matricula = matriculaRepository.save(matricula);

        Matricula matriculaAlterada = matriculaRepository.findById(matricula.getCodigo()).get();

        Assertions.assertEquals(75.0, matriculaAlterada.getPorcentagemProgresso(), 0.001);
        Assertions.assertTrue(matriculaAlterada.getConcluido());
    }

    @Test
    public void testDelete() {
        Aluno aluno = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Curso curso = cursoRepository.save(criarCurso("Java Completo", "Programacao"));

        Matricula matricula = matriculaRepository.save(criarMatricula(aluno, curso));

        matriculaRepository.delete(matricula);

        Matricula matriculaDeletada = matriculaRepository.findById(matricula.getCodigo()).orElse(null);

        Assertions.assertNull(matriculaDeletada, "A matricula ainda esta no banco de dados.");
    }

    @Test
    public void testFindAll() {
        Aluno aluno1 = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Aluno aluno2 = alunoRepository.save(criarAluno("Joao", "joao@email.com"));

        Curso curso1 = cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        Curso curso2 = cursoRepository.save(criarCurso("Spring Boot", "Programacao"));

        matriculaRepository.save(criarMatricula(aluno1, curso1));
        matriculaRepository.save(criarMatricula(aluno2, curso2));

        List<Matricula> matriculas = matriculaRepository.findAll();

        Assertions.assertFalse(matriculas.isEmpty(), "A consulta nao retornou resultados.");
    }

    @Test
    public void testFindByCurso() {
        Curso cursoJava = cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        Curso cursoSpring = cursoRepository.save(criarCurso("Spring Boot", "Programacao"));

        Aluno aluno1 = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Aluno aluno2 = alunoRepository.save(criarAluno("Joao", "joao@email.com"));

        matriculaRepository.save(criarMatricula(aluno1, cursoJava));
        matriculaRepository.save(criarMatricula(aluno2, cursoJava));
        matriculaRepository.save(criarMatricula(aluno1, cursoSpring));

        List<Matricula> matriculas = matriculaRepository.findByCurso(cursoJava);

        Assertions.assertNotNull(matriculas);
        Assertions.assertEquals(2, matriculas.size());
    }

    @Test
    public void testFindByAluno() {
        Curso cursoJava = cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        Curso cursoSpring = cursoRepository.save(criarCurso("Spring Boot", "Programacao"));

        Aluno alunoGabriel = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Aluno alunoMaria = alunoRepository.save(criarAluno("Joao", "joao@email.com"));

        matriculaRepository.save(criarMatricula(alunoGabriel, cursoJava));
        matriculaRepository.save(criarMatricula(alunoGabriel, cursoSpring));
        matriculaRepository.save(criarMatricula(alunoMaria, cursoJava));

        List<Matricula> matriculas = matriculaRepository.findByAluno(alunoGabriel);

        Assertions.assertNotNull(matriculas);
        Assertions.assertEquals(2, matriculas.size());
    }
}