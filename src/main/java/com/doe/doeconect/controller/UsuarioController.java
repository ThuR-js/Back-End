package com.doe.doeconect.controller;

import com.doe.doeconect.model.entity.Usuario;
import com.doe.doeconect.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
          Usuario novoUsuario = usuarioService.save(usuario);
          return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
     }
     @GetMapping("/{id}")
     public ResponseEntity<Object> findById(@PathVariable String id) {
          try {
               return ResponseEntity.ok(usuarioService.findById(Long.parseLong(id)));
          } catch (NumberFormatException e) {
               return ResponseEntity.badRequest().body(
                       Map.of(
                               "status", 404,
                               "error", "Bad Request",
                               "message", "O id informado não é válido: " + id
                       )
               );
          } catch (RuntimeException e ) {
               return ResponseEntity.status(404).body(
                       Map.of(
                               "status", "404",
                               "error", "Not Found",
                               "message", "Produto não encontrado com o id: " + id
                       )
               );
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Object> excluirProduto(@PathVariable String id) {
          try {
               usuarioService.delete(Long.parseLong(id));
               return ResponseEntity.ok().body(
                       Map.of(
                               "status", 200,
                               "message",
                               "Produto excluido com sucesso!")
               );
          } catch (NumberFormatException e) {
               return ResponseEntity.badRequest().body(
                       Map.of(
                               "status", 400,
                               "error", "Bad Request",
                               "message", "Produto não encontrado com o id: " + id
                       )
               );
          }
     }
}


