package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.mappers.AlunoMapper;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoDetailDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoSummaryDTO;
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

    @Autowired
    private AlunoMapper alunoMapper;

    public List<AlunoSummaryDTO> listar() {
        return alunoRepository.findAll()
                .stream()
                .map(alunoMapper::toDto2)
                .toList();
    }

    public AlunoDetailDTO salvar(AlunoRequestDTO dto) {
        Aluno aluno = alunoMapper.toEntity(dto);
        return alunoMapper.toDto1(alunoRepository.save(aluno));
    }

    public AlunoDetailDTO atualizar(Long codigo, AlunoRequestDTO dto) {
        Aluno aluno = alunoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        alunoMapper.partialUpdate(dto, aluno);

        return alunoMapper.toDto1(alunoRepository.save(aluno));
    }

    @Transactional
    public void excluir(Long codigo) {
        alunoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        alunoRepository.deleteById(codigo);
    }

    public AlunoDetailDTO buscarPorId(Long codigo) {
        Aluno aluno = alunoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));

        return alunoMapper.toDto1(aluno);
    }

}
