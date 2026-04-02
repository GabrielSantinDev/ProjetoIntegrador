package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.repositories.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Aluno> listar() {
        return this.alunoRepository.findAll();
    }

    public Aluno salvar(Aluno aluno) {
        if (aluno.getMatriculas() != null && !aluno.getMatriculas().isEmpty()) {
            aluno.getMatriculas().forEach(matricula-> matricula.setAluno(aluno));
        }

        if (aluno.getAvaliacoes() != null && !aluno.getAvaliacoes().isEmpty()) {
            aluno.getAvaliacoes().forEach(av-> av.setAluno(aluno));
        }

        return this.alunoRepository.save(aluno);
    }

    public Aluno atualizar(Long codigo, Aluno aluno){
        this.alunoRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        aluno.setCodigo(codigo);

        if (aluno.getMatriculas() != null && !aluno.getMatriculas().isEmpty()) {
            aluno.getMatriculas().forEach(matricula-> matricula.setAluno(aluno));
        }

        if (aluno.getAvaliacoes() != null && !aluno.getAvaliacoes().isEmpty()) {
            aluno.getAvaliacoes().forEach(av-> av.setAluno(aluno));
        }
        return this.alunoRepository.save(aluno);
    }

    @Transactional
    public void excluir(Long codigo){
        this.alunoRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        this.alunoRepository.deleteById(codigo);
    }

}
