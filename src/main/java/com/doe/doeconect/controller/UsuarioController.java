package com.doe.doeconect.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doe.doeconect.model.entity.Usuario;
import com.doe.doeconect.model.services.UsuarioService;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Erro ao criar usuário: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> body) {
        try {
            Usuario usuario = usuarioService.login(body.get("email"), body.get("senha"));
            return ResponseEntity.ok(Map.of(
                    "id", usuario.getId(),
                    "nome", usuario.getNome(),
                    "email", usuario.getUsername(),
                    "nivelAcesso", usuario.getNivelAcesso() != null ? usuario.getNivelAcesso() : "",
                    "foto", usuario.getFoto() != null ? usuario.getFoto() : "",
                    "regiao", usuario.getRegiao() != null ? usuario.getRegiao() : ""
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.findById(id));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Id inválido: " + id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/{id}/perfil")
    public ResponseEntity<Object> perfilPublico(@PathVariable Long id) {
        try {
            Usuario u = usuarioService.findById(id);
            return ResponseEntity.ok(Map.of(
                    "id", u.getId(),
                    "nome", u.getNome(),
                    "foto", u.getFoto() != null ? u.getFoto() : "",
                    "regiao", u.getRegiao() != null ? u.getRegiao() : "",
                    "dataCadastro", u.getDataCadastro()
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody Usuario usuario, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.update(id, usuario));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Id inválido: " + id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Object> atualizarSenha(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            usuarioService.atualizarSenha(id, body.get("senhaAtual"), body.get("novaSenha"));
            return ResponseEntity.ok(Map.of("message", "Senha atualizada com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirConta(@PathVariable Long id) {
        try {
            usuarioService.excluirConta(id);
            return ResponseEntity.ok(Map.of("message", "Conta excluída com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/inativar")
    public ResponseEntity<Object> inativar(@PathVariable Long id) {
        try {
            usuarioService.inativar(id);
            return ResponseEntity.ok(Map.of("message", "Usuário inativado com sucesso!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/reativar")
    public ResponseEntity<Object> reativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.reativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
