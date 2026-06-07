package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.Instrutor;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorDetailDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.instrutor.InstrutorSummaryDTO;
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
    public ResponseEntity<List<InstrutorSummaryDTO>> listar() {
        return ResponseEntity.ok(instrutorService.listar());
    }

    @PostMapping
    public ResponseEntity<InstrutorDetailDTO> criar(@RequestBody InstrutorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(instrutorService.salvar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<InstrutorDetailDTO> atualizar(@PathVariable Long codigo,
                                                        @RequestBody InstrutorRequestDTO dto) {
        return ResponseEntity.ok(instrutorService.atualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        instrutorService.excluir(codigo);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<InstrutorDetailDTO> buscarPorId(@PathVariable Long codigo) {
        return ResponseEntity.ok(instrutorService.buscarPorId(codigo));
    }
}
