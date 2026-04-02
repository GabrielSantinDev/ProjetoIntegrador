package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.matricula.Matricula;
import br.edu.ifpr.bsi.projetoexemplo.services.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public ResponseEntity<List<Matricula>> listar() {
        return ResponseEntity.ok(matriculaService.listar());
    }

    @PostMapping
    public ResponseEntity<Matricula> criar(@RequestBody Matricula matricula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaService.salvar(matricula));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Matricula> atualizar(@PathVariable Long codigo, @RequestBody Matricula matricula) {
        return ResponseEntity.ok(matriculaService.atualizar(codigo, matricula));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        matriculaService.excluir(codigo);
    }

}
