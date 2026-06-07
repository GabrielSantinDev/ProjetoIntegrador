package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.services.InstrutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService instrutorService;

    @GetMapping
    public ResponseEntity<List<Instrutor>> listar() {
        return ResponseEntity.ok(instrutorService.listar());
    }

    @PostMapping
    public ResponseEntity<Instrutor> criar(@RequestBody Instrutor instrutor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instrutorService.salvar(instrutor));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Instrutor> atualizar(@PathVariable Long codigo, @RequestBody Instrutor instrutor) {
        return ResponseEntity.ok(instrutorService.atualizar(codigo, instrutor));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        instrutorService.excluir(codigo);
    }

}
