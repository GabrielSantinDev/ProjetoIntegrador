package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.mappers.CursoMapper;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.CursoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.CursoResponseDTO;
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

    @Autowired
    private CursoMapper cursoMapper;

    public List<CursoResponseDTO> listar() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toDto)
                .toList();
    }

    public CursoResponseDTO salvar(CursoRequestDTO dto) {
        Curso curso = cursoMapper.toEntity(dto);

        Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorCodigo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        curso.setInstrutor(instrutor);

        return cursoMapper.toDto(cursoRepository.save(curso));
    }

    public CursoResponseDTO atualizar(Long codigo, CursoRequestDTO dto) {
        Curso curso = cursoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        cursoMapper.partialUpdate(dto, curso);

        if (dto.getInstrutorCodigo() != null) {
            Instrutor instrutor = instrutorRepository.findById(dto.getInstrutorCodigo())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

            curso.setInstrutor(instrutor);
        }

        return cursoMapper.toDto(cursoRepository.save(curso));
    }

    @Transactional
    public void excluir(Long codigo) {
        cursoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        cursoRepository.deleteById(codigo);
    }

    public CursoResponseDTO buscarPorId(Long codigo) {
        Curso curso = cursoRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));

        return cursoMapper.toDto(curso);
    }
}
