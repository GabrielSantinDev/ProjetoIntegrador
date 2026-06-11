package br.edu.ifpr.bsi.projetoexemplo.controllers;

import br.edu.ifpr.bsi.projetoexemplo.model.usuario.LoginRequestDTO;
import br.edu.ifpr.bsi.projetoexemplo.model.usuario.LoginResponseDTO;
import br.edu.ifpr.bsi.projetoexemplo.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    // POST /auth  →  { username, senha }  →  { usuario, token }
    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(autenticacaoService.login(request));
    }
}