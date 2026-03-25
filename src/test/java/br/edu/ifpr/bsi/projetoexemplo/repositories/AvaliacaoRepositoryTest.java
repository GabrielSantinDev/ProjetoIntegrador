package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
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
class AvaliacaoRepositoryTest {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

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

    private Avaliacao criarAvaliacao(Aluno aluno, Curso curso, String descricao, Double valor) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAluno(aluno);
        avaliacao.setCurso(curso);
        avaliacao.setDescricao(descricao);
        avaliacao.setValor(valor);
        return avaliacao;
    }

    @Test
    public void testInsert() {
        Aluno aluno = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Curso curso = cursoRepository.save(criarCurso("Java Completo", "Programacao"));

        Avaliacao avaliacao = criarAvaliacao(aluno, curso, "Curso muito bom", 5.0);

        avaliacao = avaliacaoRepository.save(avaliacao);

        Avaliacao avaliacaoInserida = avaliacaoRepository.findById(avaliacao.getCodigo()).get();

        Assertions.assertNotNull(avaliacaoInserida, "A avaliacao nao foi inserida.");
        Assertions.assertNotNull(avaliacaoInserida.getAluno(), "Aluno nao foi associado.");
        Assertions.assertNotNull(avaliacaoInserida.getCurso(), "Curso nao foi associado.");
    }

    @Test
    public void testUpdate() {
        Aluno aluno = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Curso curso = cursoRepository.save(criarCurso("Java Completo", "Programacao"));

        Avaliacao avaliacao = avaliacaoRepository.save(
                criarAvaliacao(aluno, curso, "Curso bom", 4.0)
        );

        avaliacao.setDescricao("Curso excelente");
        avaliacao.setValor(5.0);

        avaliacao = avaliacaoRepository.save(avaliacao);

        Avaliacao avaliacaoAlterada = avaliacaoRepository.findById(avaliacao.getCodigo()).get();

        Assertions.assertEquals("Curso excelente", avaliacaoAlterada.getDescricao());
        Assertions.assertEquals(5.0, avaliacaoAlterada.getValor(), 0.001);
    }

    @Test
    public void testDelete() {
        Aluno aluno = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Curso curso = cursoRepository.save(criarCurso("Java Completo", "Programacao"));

        Avaliacao avaliacao = avaliacaoRepository.save(
                criarAvaliacao(aluno, curso, "Curso muito bom", 5.0)
        );

        avaliacaoRepository.delete(avaliacao);

        Avaliacao avaliacaoDeletada = avaliacaoRepository.findById(avaliacao.getCodigo()).orElse(null);

        Assertions.assertNull(avaliacaoDeletada, "A avaliacao ainda esta no banco de dados.");
    }

    @Test
    public void testFindAll() {
        Aluno aluno1 = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Aluno aluno2 = alunoRepository.save(criarAluno("Eduardo", "eduardo@email.com"));

        Curso curso1 = cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        Curso curso2 = cursoRepository.save(criarCurso("Spring Boot", "Programacao"));

        avaliacaoRepository.save(criarAvaliacao(aluno1, curso1, "Muito bom", 5.0));
        avaliacaoRepository.save(criarAvaliacao(aluno2, curso2, "Bom", 4.0));

        List<Avaliacao> avaliacoes = avaliacaoRepository.findAll();

        Assertions.assertFalse(avaliacoes.isEmpty(), "A consulta nao retornou resultados.");
    }

    @Test
    public void testFindByCurso() {
        Curso cursoJava = cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        Curso cursoSpring = cursoRepository.save(criarCurso("Spring Boot", "Programacao"));

        Aluno aluno1 = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Aluno aluno2 = alunoRepository.save(criarAluno("Eduardo", "eduardo@email.com"));

        avaliacaoRepository.save(criarAvaliacao(aluno1, cursoJava, "Excelente", 5.0));
        avaliacaoRepository.save(criarAvaliacao(aluno2, cursoJava, "Muito bom", 4.5));
        avaliacaoRepository.save(criarAvaliacao(aluno1, cursoSpring, "Bom", 4.0));

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByCurso(cursoJava);

        Assertions.assertNotNull(avaliacoes);
        Assertions.assertEquals(2, avaliacoes.size());
    }

    @Test
    public void testFindByAluno() {
        Curso cursoJava = cursoRepository.save(criarCurso("Java Completo", "Programacao"));
        Curso cursoSpring = cursoRepository.save(criarCurso("Spring Boot", "Programacao"));

        Aluno alunoGabriel = alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        Aluno alunoMaria = alunoRepository.save(criarAluno("Eduardo", "eduardo@email.com"));

        avaliacaoRepository.save(criarAvaliacao(alunoGabriel, cursoJava, "Excelente", 5.0));
        avaliacaoRepository.save(criarAvaliacao(alunoGabriel, cursoSpring, "Muito bom", 4.5));
        avaliacaoRepository.save(criarAvaliacao(alunoMaria, cursoJava, "Bom", 4.0));

        List<Avaliacao> avaliacoes = avaliacaoRepository.findByAluno(alunoGabriel);

        Assertions.assertNotNull(avaliacoes);
        Assertions.assertEquals(2, avaliacoes.size());
    }
}