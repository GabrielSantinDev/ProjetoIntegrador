package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.Avaliacao;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.AvaliacaoRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.avaliacao.AvaliacaoResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<List<AvaliacaoResponseDTO>> listar() {
        return ResponseEntity.ok(avaliacaoService.listar());
    }

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criar(@RequestBody AvaliacaoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avaliacaoService.salvar(dto));
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable Long codigo,
                                                          @RequestBody AvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(avaliacaoService.atualizar(codigo, dto));
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long codigo) {
        avaliacaoService.excluir(codigo);
    }


    @GetMapping("/{codigo}")
    public ResponseEntity<AvaliacaoResponseDTO> buscarPorId(@PathVariable Long codigo) {
        return ResponseEntity.ok(avaliacaoService.buscarPorId(codigo));
    }
}
