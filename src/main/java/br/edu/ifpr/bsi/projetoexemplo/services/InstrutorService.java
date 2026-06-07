package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.mappers.InstrutorMapper;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorDetailDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorSummaryDTO;
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

    @Autowired
    private InstrutorMapper instrutorMapper;

    public List<InstrutorSummaryDTO> listar() {
        return instrutorRepository.findAll()
                .stream()
                .map(instrutorMapper::toDto2)
                .toList();
    }

    public InstrutorDetailDTO salvar(InstrutorRequestDTO dto) {
        Instrutor instrutor = instrutorMapper.toEntity(dto);
        return instrutorMapper.toDto(instrutorRepository.save(instrutor));
    }

    public InstrutorDetailDTO atualizar(Long codigo, InstrutorRequestDTO dto) {
        Instrutor instrutor = instrutorRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        instrutorMapper.partialUpdate(dto, instrutor);

        return instrutorMapper.toDto(instrutorRepository.save(instrutor));
    }

    @Transactional
    public void excluir(Long codigo) {
        instrutorRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        instrutorRepository.deleteById(codigo);
    }

    public InstrutorDetailDTO buscarPorId(Long codigo) {
        Instrutor instrutor = instrutorRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrutor não encontrado"));

        return instrutorMapper.toDto(instrutor);
    }
    
}
