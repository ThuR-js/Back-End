package com.doe.doeconect.model.services;


import com.doe.doeconect.model.entity.Usuario;
import com.doe.doeconect.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired     // Injeção de dependência automática
    private UsuarioRepository usuarioRepository;

    // Método responsável em listar todos os produtos cadastrados no banco de dados

    public List<Usuario> findAll() {

        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        usuario.setStatusUsuario("ATIVO");
        return usuarioRepository.save(usuario);
    }

   public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produto não encontrado com o id" + id));
   }

   public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioExistente = findById(id);
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setTelefone(usuario.getTelefone());
        usuarioExistente.setCpf(usuario.getCpf());
        usuarioExistente.setSenha(usuario.getSenha());
        usuarioExistente.setNivelAcesso(usuario.getNivelAcesso());
        ///usuarioExistente.setCaminhoFoto(usuario.getCaminhoFoto());
        usuarioExistente.setDataCadastro(usuario.getDataCadastro());
        usuarioExistente.setStatusUsuario(usuario.getStatusUsuario());
        return usuarioRepository.save(usuarioExistente);


   }

   public void delete(Long id) {
        Usuario usuarioExistente = findById(id);
        usuarioRepository.delete(usuarioExistente);
   }


}
