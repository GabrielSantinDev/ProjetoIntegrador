package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
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
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

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

    @Test
    public void testInsert() {
        Aluno aluno = criarAluno("Gabriel", "gabriel@email.com");

        aluno = alunoRepository.save(aluno);

        Aluno alunoInserido = alunoRepository.findById(aluno.getCodigo()).get();

        Assertions.assertNotNull(alunoInserido, "Aluno nao foi inserido.");
    }

    @Test
    public void testUpdate() {
        Aluno aluno = criarAluno("Gabriel", "gabriel@email.com");
        Aluno alunoAlterar = alunoRepository.save(aluno);

        alunoAlterar.setNivelAprendizado("Avancado");

        alunoAlterar = alunoRepository.save(alunoAlterar);

        Aluno alunoAlterado = alunoRepository.findById(alunoAlterar.getCodigo()).get();

        Assertions.assertEquals("Avancado", alunoAlterado.getNivelAprendizado());
    }

    @Test
    public void testDelete() {
        Aluno aluno = criarAluno("Gabriel", "gabriel@email.com");
        Aluno alunoDeletar = alunoRepository.save(aluno);

        alunoRepository.delete(alunoDeletar);

        Aluno alunoDeletado = alunoRepository.findById(aluno.getCodigo()).orElse(null);
        Assertions.assertNull(alunoDeletado, "O aluno ainda esta no banco de dados.");
    }

    @Test
    public void testFindAll() {
        alunoRepository.save(criarAluno("Gabriel", "gabriel1@email.com"));
        alunoRepository.save(criarAluno("Maria", "maria@email.com"));
        alunoRepository.save(criarAluno("Joao", "joao@email.com"));

        List<Aluno> alunos = alunoRepository.findAll();

        Assertions.assertFalse(alunos.isEmpty(), "A consulta não retornou resultados.");
    }

    @Test
    public void testGetAllByNomeLike() {
        alunoRepository.save(criarAluno("Gabriel", "gabriel@email.com"));
        alunoRepository.save(criarAluno("Gabriela", "gabriela@email.com"));
        alunoRepository.save(criarAluno("Maria", "maria@email.com"));

        List<Aluno> alunos = alunoRepository.getAllByNomeLike("Gabri");

        Assertions.assertNotNull(alunos);
        Assertions.assertEquals(2, alunos.size());
    }


    // Teste com relacionamentos (aluno com matricula/avaliacao em curso)

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    private Instrutor criarInstrutor(String nome, String email) {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome(nome);
        instrutor.setEmail(email);
        instrutor.setSenha("123456");
        instrutor.setDataNascimento(LocalDate.of(1990, 1, 1));
        instrutor.setDataCadastro(LocalDateTime.now());
        instrutor.setEspecializacao("Java");
        instrutor.setDescricao("Instrutor de Java");
        instrutor.setAvaliacao(5.0);
        instrutor.setGanhosTotais(1000.0);
        return instrutor;
    }

    private Curso criarCurso(String titulo, Instrutor instrutor) {
        Curso curso = new Curso();
        curso.setTitulo(titulo);
        curso.setCategoria("Programacao");
        curso.setHorasDuracao(20.0);
        curso.setPreco(199.90);
        curso.setDescricao("Curso de Java");
        curso.setInstrutor(instrutor);
        return curso;
    }

    @Test
    public void testInsertAlunoMatriculaAvaliacao() {

        // Criando o instrutor do curso
        Instrutor instrutor = criarInstrutor("Eduardo", "eduardo@instrutor.com");
        instrutor = instrutorRepository.save(instrutor);

        // Criando o curso
        Curso curso = criarCurso("Banco de Dados", instrutor);
        curso = cursoRepository.save(curso);

        // Criando o aluno
        Aluno aluno = criarAluno("Gabriel", "gabriel@email.com");
        aluno = alunoRepository.save(aluno);

        // criando a matricula
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setCurso(curso);
        matricula.setPorcentagemProgresso(50.0);
        matricula.setConcluido(false);
        matricula.setDataMatricula(LocalDateTime.now());

        matriculaRepository.save(matricula);
        aluno.getMatriculas().add(matricula);

        // Criando avaliacao
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setAluno(aluno);
        avaliacao.setCurso(curso);
        avaliacao.setDescricao("Curso ótimo!");
        avaliacao.setValor(4.5);

        avaliacaoRepository.save(avaliacao);
        aluno.getAvaliacoes().add(avaliacao);

        // Buscar o aluno inserido
        Aluno alunoInserido = alunoRepository.findById(aluno.getCodigo()).get();

        Assertions.assertNotNull(alunoInserido, "Aluno nao foi inserido.");
        Assertions.assertNotNull(alunoInserido.getMatriculas(), "Lista de matriculas nao foi carregada.");
        Assertions.assertNotNull(alunoInserido.getAvaliacoes(), "Lista de avaliacoes nao foi carregada.");

        Assertions.assertEquals(1, alunoInserido.getMatriculas().size(), "Aluno deveria ter 1 matricula.");
        Assertions.assertEquals(1, alunoInserido.getAvaliacoes().size(), "Aluno deveria ter 1 avaliacao.");

        Assertions.assertEquals("Banco de Dados", alunoInserido.getMatriculas().get(0).getCurso().getTitulo());
        Assertions.assertEquals("Curso ótimo!", alunoInserido.getAvaliacoes().get(0).getDescricao());
    }
}
