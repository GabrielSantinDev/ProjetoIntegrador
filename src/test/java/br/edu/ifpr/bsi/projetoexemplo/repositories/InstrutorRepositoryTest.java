package br.edu.ifpr.bsi.projetoexemplo.repositories;

import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
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
class InstrutorRepositoryTest {

    @Autowired
    private InstrutorRepository instrutorRepository;

    private Instrutor criarInstrutor(String nome, String email) {
        Instrutor instrutor = new Instrutor();
        instrutor.setNome(nome);
        instrutor.setEmail(email);
        instrutor.setSenha("123456");
        instrutor.setDataNascimento(LocalDate.of(1990, 1, 1));
        instrutor.setDataCadastro(LocalDateTime.now());
        instrutor.setEspecializacao("Java");
        instrutor.setDescricao("Instrutor de programação");
        instrutor.setAvaliacao(4.5);
        instrutor.setGanhosTotais(1000.0);
        return instrutor;
    }

    @Test
    public void testInsert() {
        Instrutor instrutor = criarInstrutor("Gabriel", "gabriel@email.com");

        instrutor = instrutorRepository.save(instrutor);

        Instrutor instrutorInserido = instrutorRepository.findById(instrutor.getCodigo()).get();

        Assertions.assertNotNull(instrutorInserido, "Instrutor nao foi inserido.");
    }

    @Test
    public void testUpdate() {
        Instrutor instrutor = criarInstrutor("Gabriel", "gabriel@email.com");
        Instrutor instrutorAlterar = instrutorRepository.save(instrutor);

        instrutorAlterar.setEspecializacao("PHP");

        instrutorAlterar = instrutorRepository.save(instrutorAlterar);

        Instrutor instrutorAlterado = instrutorRepository.findById(instrutorAlterar.getCodigo()).get();

        Assertions.assertEquals("PHP", instrutorAlterado.getEspecializacao());
    }

    @Test
    public void testDelete() {
        Instrutor instrutor = criarInstrutor("Gabriel", "gabriel@email.com");
        Instrutor instrutorDeletar = instrutorRepository.save(instrutor);

        instrutorRepository.delete(instrutorDeletar);

        Instrutor instrutorDeletado = instrutorRepository.findById(instrutor.getCodigo()).orElse(null);
        Assertions.assertNull(instrutorDeletado, "O instrutor ainda esta no banco de dados.");
    }

    @Test
    public void testFindAll() {
        instrutorRepository.save(criarInstrutor("Gabriel", "gabriel@email.com"));
        instrutorRepository.save(criarInstrutor("Marina", "marina@email.com"));
        instrutorRepository.save(criarInstrutor("Joao", "joao@email.com"));

        List<Instrutor> instrutores = instrutorRepository.findAll();

        Assertions.assertFalse(instrutores.isEmpty(), "A consulta não retornou resultados.");
    }

    @Test
    public void testGetAllByNomeLike() {
        instrutorRepository.save(criarInstrutor("Gabriel", "gabriel@email.com"));
        instrutorRepository.save(criarInstrutor("Gabriel Santin", "gabrielsantin@email.com"));
        instrutorRepository.save(criarInstrutor("Marina", "marina@email.com"));

        List<Instrutor> instrutores = instrutorRepository.getAllByNomeLike("Gabri");

        Assertions.assertNotNull(instrutores);
        Assertions.assertEquals(2, instrutores.size());
    }
}