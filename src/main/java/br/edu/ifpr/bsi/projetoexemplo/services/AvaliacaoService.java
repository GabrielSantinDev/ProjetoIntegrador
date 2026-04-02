package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.repositories.AvaliacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> listar() {
        return this.avaliacaoRepository.findAll();
    }

    public Avaliacao salvar(Avaliacao avaliacao) {

        return this.avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao atualizar(Long codigo, Avaliacao avaliacao){
        this.avaliacaoRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliacao não encontrado"));
        avaliacao.setCodigo(codigo);

        return this.avaliacaoRepository.save(avaliacao);
    }

    @Transactional
    public void excluir(Long codigo){
        this.avaliacaoRepository.findById(codigo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliacao não encontrado"));
        this.avaliacaoRepository.deleteById(codigo);
    }
    
}
