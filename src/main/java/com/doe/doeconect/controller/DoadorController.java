package com.doe.doeconect.controller;

import com.doe.doeconect.model.entity.Doador;
import com.doe.doeconect.model.services.DoadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/doador")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    @GetMapping
    public ResponseEntity<List<Doador>> findAll() {
        return ResponseEntity.ok(doadorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(doadorService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Object> findByUsuarioId(@PathVariable Long usuarioId) {
        try {
            return ResponseEntity.ok(doadorService.findByUsuarioId(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Doador doador) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(doadorService.save(doador));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao criar doador: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Doador doador) {
        try {
            return ResponseEntity.ok(doadorService.update(id, doador));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            doadorService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Doador deletado com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }
}
