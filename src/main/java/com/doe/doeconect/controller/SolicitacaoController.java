package com.doe.doeconect.controller;

import com.doe.doeconect.model.entity.Solicitacao;
import com.doe.doeconect.model.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/solicitacao")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public ResponseEntity<List<Solicitacao>> findAll() {
        return ResponseEntity.ok(solicitacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(solicitacaoService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Solicitacao>> findByUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(solicitacaoService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/anuncio/{anuncioId}")
    public ResponseEntity<List<Solicitacao>> findByAnuncio(@PathVariable Long anuncioId) {
        return ResponseEntity.ok(solicitacaoService.findByAnuncioId(anuncioId));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Solicitacao solicitacao) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoService.save(solicitacao));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao criar solicitação: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Object> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(solicitacaoService.updateStatus(id, body.get("status")));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            solicitacaoService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Solicitação deletada com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }
}
