package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.repositories.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InstrutorService {

    @Autowired
    private InstrutorRepository instrutorRepository;

    public List<Instrutor> listar() {
        return this.instrutorRepository.findAll();
    }

    public Instrutor salvar(Instrutor instrutor) {
        if (instrutor.getCursosCriados() != null && !instrutor.getCursosCriados().isEmpty()) {
            instrutor.getCursosCriados().forEach(c-> c.setInstrutor(instrutor));
        }

        return this.instrutorRepository.save(instrutor);
    }

    public Instrutor atualizar(Long codigo, Instrutor instrutor){
        this.instrutorRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrutor não encontrado"));
        instrutor.setCodigo(codigo);

        if (instrutor.getCursosCriados() != null && !instrutor.getCursosCriados().isEmpty()) {
            instrutor.getCursosCriados().forEach(c-> c.setInstrutor(instrutor));
        }

        return this.instrutorRepository.save(instrutor);
    }

    @Transactional
    public void excluir(Long codigo){
        this.instrutorRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrutor não encontrado"));
        this.instrutorRepository.deleteById(codigo);
    }
    
}
