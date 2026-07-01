package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoDetailDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.aluno.AlunoSummaryDTO;
import br.edu.ifpr.bsi.projetoexemplo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoSummaryDTO>> listar() {
        return ResponseEntity.ok(alunoService.listar());
    }

    @PostMapping
    public ResponseEntity<AlunoDetailDTO> criar(@RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoService.salvar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<AlunoDetailDTO> atualizar(@PathVariable Long codigo,
                                                    @RequestBody AlunoRequestDTO dto) {
        return ResponseEntity.ok(alunoService.atualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        alunoService.excluir(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<AlunoDetailDTO> buscarPorId(@PathVariable Long codigo) {
        return ResponseEntity.ok(alunoService.buscarPorId(codigo));
    }

}
