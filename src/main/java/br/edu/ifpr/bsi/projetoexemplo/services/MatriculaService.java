package br.edu.ifpr.bsi.projetoexemplo.services;

import br.edu.ifpr.bsi.projetoexemplo.mappers.MatriculaMapper;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.MatriculaRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.MatriculaResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.repositories.AlunoRepository;
import br.edu.ifpr.bsi.projetoexemplo.repositories.CursoRepository;
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

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private MatriculaMapper matriculaMapper;

    public List<MatriculaResponseDTO> listar() {
        return matriculaRepository.findAll()
                .stream()
                .map(matriculaMapper::toDto)
                .toList();
    }

    public MatriculaResponseDTO salvar(MatriculaRequestDTO dto) {
        Matricula matricula = matriculaMapper.toEntity(dto);

        Aluno aluno = alunoRepository.findById(dto.getAlunoCodigo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Curso curso = cursoRepository.findById(dto.getCursoCodigo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        matricula.setAluno(aluno);
        matricula.setCurso(curso);

        return matriculaMapper.toDto(matriculaRepository.save(matricula));
    }

    public MatriculaResponseDTO atualizar(Long codigo, MatriculaRequestDTO dto) {
        Matricula matricula = matriculaRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        matriculaMapper.partialUpdate(dto, matricula);

        return matriculaMapper.toDto(matriculaRepository.save(matricula));
    }

    @Transactional
    public void excluir(Long codigo) {
        matriculaRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        matriculaRepository.deleteById(codigo);
    }

    public MatriculaResponseDTO buscarPorId(Long codigo) {
        Matricula matricula = matriculaRepository.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Matrícula não encontrada"));

        return matriculaMapper.toDto(matricula);
    }


    public List<MatriculaResponseDTO> listarPorAlunoCodigo(Long alunoCodigo) {
        return matriculaRepository.findByAlunoCodigo(alunoCodigo)
                .stream()
                .map(matriculaMapper::toDto)
                .toList();
    }



}
