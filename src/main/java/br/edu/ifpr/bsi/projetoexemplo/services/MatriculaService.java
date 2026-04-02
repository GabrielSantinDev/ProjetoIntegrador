package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import br.edu.ifpr.bsi.projetoexemplo.repositories.MatriculaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    public List<Matricula> listar() {
        return this.matriculaRepository.findAll();
    }

    public Matricula salvar(Matricula matricula) {

        return this.matriculaRepository.save(matricula);
    }

    public Matricula atualizar(Long codigo, Matricula matricula){
        this.matriculaRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrado"));
        matricula.setCodigo(codigo);

        return this.matriculaRepository.save(matricula);
    }

    @Transactional
    public void excluir(Long codigo){
        this.matriculaRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matricula não encontrado"));
        this.matriculaRepository.deleteById(codigo);
    }
    
}
