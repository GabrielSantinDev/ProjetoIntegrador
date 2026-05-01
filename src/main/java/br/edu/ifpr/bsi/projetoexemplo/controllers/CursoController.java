package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.curso.Curso;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.CursoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.curso.CursoResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @PostMapping
    public ResponseEntity<CursoResponseDTO> criar(@RequestBody CursoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cursoService.salvar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<CursoResponseDTO> atualizar(@PathVariable Long codigo,
                                                      @RequestBody CursoRequestDTO dto) {
        return ResponseEntity.ok(cursoService.atualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        cursoService.excluir(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<CursoResponseDTO> buscarPorId(@PathVariable Long codigo) {
        return ResponseEntity.ok(cursoService.buscarPorId(codigo));
    }
}
