package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.repositories.CursoRepository;
import br.edu.ifpr.bsi.projetoexemplo.repositories.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    public List<Curso> listar() {
        return this.cursoRepository.findAll();
    }

    public Curso salvar(Curso curso) {

        System.out.println("Objeto Instrutor: " + curso.getInstrutor());

        if (curso.getInstrutor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Objeto instrutor não chegou no JSON");
        }

        if (curso.getInstrutor().getCodigo() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O código do instrutor está vindo nulo");
        }

        Instrutor instrutor = instrutorRepository.findById(curso.getInstrutor().getCodigo()).get();

        // 2. Associa o instrutor real (encontrado no banco) ao curso
        curso.setInstrutor(instrutor);

        if (curso.getMatriculas() != null && !curso.getMatriculas().isEmpty()) {
            curso.getMatriculas().forEach(matricula-> matricula.setCurso(curso));
        }

        if (curso.getAvaliacoes() != null && !curso.getAvaliacoes().isEmpty()) {
            curso.getAvaliacoes().forEach(av-> av.setCurso(curso));
        }

        return this.cursoRepository.save(curso);
    }

    public Curso atualizar(Long codigo, Curso curso){
        this.cursoRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        curso.setCodigo(codigo);

        if (curso.getMatriculas() != null && !curso.getMatriculas().isEmpty()) {
            curso.getMatriculas().forEach(matricula-> matricula.setCurso(curso));
        }

        if (curso.getAvaliacoes() != null && !curso.getAvaliacoes().isEmpty()) {
            curso.getAvaliacoes().forEach(av-> av.setCurso(curso));
        }
        return this.cursoRepository.save(curso);
    }

    @Transactional
    public void excluir(Long codigo){
        this.cursoRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        this.cursoRepository.deleteById(codigo);
    }

    
}
