package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.aluno.Aluno;
import br.edu.ifpr.bsi.projetoexemplo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    // READ - Listar todos os alunos (GET)
    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = this.alunoService.listar();
        return ResponseEntity.ok(alunos);
    }

    // CREATE - Criar um novo aluno (POST)
    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno request) {
        Aluno alunoSalvo = alunoService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }

    // UPDATE - Atualizar um aluno existente (PUT)
    @PutMapping("/{codigo}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long codigo, @RequestBody Aluno request){
        Aluno alunoAtualizado = alunoService.atualizar(codigo, request);
        return ResponseEntity.ok(alunoAtualizado);
    }

    // DELETE - Excluir um aluno pelo Codigo (DELETE)
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo){
        alunoService.excluir(codigo);
    }

}
