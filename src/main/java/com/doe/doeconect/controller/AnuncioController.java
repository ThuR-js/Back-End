package com.doe.doeconect.controller;

import com.doe.doeconect.model.entity.Anuncio;
import com.doe.doeconect.model.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/anuncio")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public ResponseEntity<List<Anuncio>> findAll() {
        return ResponseEntity.ok(anuncioService.findAll());
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Anuncio>> findAllAdmin() {
        return ResponseEntity.ok(anuncioService.findAllAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(anuncioService.findById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Anuncio>> findPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(anuncioService.findPorCategoria(categoriaId));
    }

    @GetMapping("/doador/{doadorId}")
    public ResponseEntity<List<Anuncio>> findByDoador(@PathVariable Long doadorId) {
        return ResponseEntity.ok(anuncioService.findByDoador(doadorId));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Anuncio anuncio) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(anuncioService.save(anuncio));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao criar anuncio: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Anuncio anuncio) {
        try {
            return ResponseEntity.ok(anuncioService.update(id, anuncio));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<Object> inativar(@PathVariable Long id, @RequestParam Long doadorId) {
        try {
            return ResponseEntity.ok(anuncioService.inativar(id, doadorId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            anuncioService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Anuncio deletado com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    // Favoritos
    @PostMapping("/{id}/favoritar")
    public ResponseEntity<Object> favoritar(@PathVariable Long id, @RequestParam Long usuarioId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(anuncioService.favoritar(usuarioId, id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}/favoritar")
    public ResponseEntity<Object> desfavoritar(@PathVariable Long id, @RequestParam Long usuarioId) {
        try {
            anuncioService.desfavoritar(usuarioId, id);
            return ResponseEntity.ok(Map.of("message", "Removido dos favoritos!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/favoritos/{usuarioId}")
    public ResponseEntity<List<Anuncio>> listarFavoritos(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(anuncioService.listarFavoritos(usuarioId));
    }
}
