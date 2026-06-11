package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.MatriculaRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.matricula.MatriculaResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<MatriculaResponseDTO>> listar() {
        return ResponseEntity.ok(matriculaService.listar());
    }

    @PostMapping
    public ResponseEntity<MatriculaResponseDTO> criar(@RequestBody MatriculaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(matriculaService.salvar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<MatriculaResponseDTO> atualizar(@PathVariable Long codigo,
                                                          @RequestBody MatriculaRequestDTO dto) {
        return ResponseEntity.ok(matriculaService.atualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        matriculaService.excluir(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<MatriculaResponseDTO> buscarPorId(@PathVariable Long codigo) {
        return ResponseEntity.ok(matriculaService.buscarPorId(codigo));
    }

    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaResponseDTO>> listarPorAluno(@PathVariable Long alunoId) {
        return ResponseEntity.ok(matriculaService.listarPorAlunoCodigo(alunoId));
    }



}
