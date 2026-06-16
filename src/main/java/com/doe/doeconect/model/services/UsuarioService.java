package com.doe.doeconect.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doe.doeconect.model.entity.Usuario;
import com.doe.doeconect.model.repository.AnuncioRepository;
import com.doe.doeconect.model.repository.DoadorRepository;
import com.doe.doeconect.model.repository.FavoritoRepository;
import com.doe.doeconect.model.repository.SolicitacaoRepository;
import com.doe.doeconect.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DoadorRepository doadorRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private SolicitacaoRepository solicitacaoRepository;
    @Autowired
    private FavoritoRepository favoritoRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setStatusUsuario("ATIVO");
        if (usuario.getNivelAcesso() == null || usuario.getNivelAcesso().isBlank()) {
            usuario.setNivelAcesso("DONATARIO");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o id: " + id));
    }

    public Usuario login(String username, String senhaRaw) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas"));
        if (!encoder.matches(senhaRaw, usuario.getPassword())) {
            throw new RuntimeException("Credenciais inválidas");
        }
        if (!"ATIVO".equals(usuario.getStatusUsuario())) {
            throw new RuntimeException("Conta inativa");
        }
        return usuario;
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario existente = findById(id);
        if (usuario.getNome() != null) existente.setNome(usuario.getNome());
        if (usuario.getUsername() != null) existente.setUsername(usuario.getUsername());
        if (usuario.getNivelAcesso() != null) existente.setNivelAcesso(usuario.getNivelAcesso());
        if (usuario.getFoto() != null) existente.setFoto(usuario.getFoto());
        if (usuario.getRegiao() != null) existente.setRegiao(usuario.getRegiao());

        return usuarioRepository.save(existente);
    }

    public void atualizarSenha(Long id, String senhaAtual, String novaSenha) {
        Usuario usuario = findById(id);
        if (!encoder.matches(senhaAtual, usuario.getPassword())) {
            throw new RuntimeException("Senha atual incorreta");
        }
        usuario.setPassword(encoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }

    public void excluirConta(Long id) {
        Usuario usuario = findById(id);
        // Inativa solicitações
        solicitacaoRepository.findByUsuarioId(id).forEach(s -> {
            s.setStatusSolicitacao("CANCELADA");
            solicitacaoRepository.save(s);
        });
        // Remove favoritos
        favoritoRepository.findByUsuarioIdAndTipo(id, "FAVORITO")
                .forEach(favoritoRepository::delete);
        // Inativa anúncios do doador vinculado
        doadorRepository.findByUsuarioId(id).ifPresent(doador -> {
            anuncioRepository.findByDoadorId(doador.getId()).forEach(a -> {
                a.setStatusAnuncio("INATIVO");
                anuncioRepository.save(a);
            });
            doador.setStatusDoador("INATIVO");
            doadorRepository.save(doador);
        });
        usuario.setStatusUsuario("INATIVO");
        usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.delete(findById(id));
    }

    public Usuario inativar(Long id) {
        Usuario u = findById(id);
        u.setStatusUsuario("INATIVO");
        return usuarioRepository.save(u);
    }

    public Usuario reativarPorCredenciais(String email, String senha) {
        Usuario u = usuarioRepository.findByUsername(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        if (!encoder.matches(senha, u.getPassword())) {
            throw new RuntimeException("Credenciais inválidas.");
        }
        u.setStatusUsuario("ATIVO");
        return usuarioRepository.save(u);
    }

    public Usuario reativar(Long id) {
        Usuario u = findById(id);
        u.setStatusUsuario("ATIVO");
        return usuarioRepository.save(u);
    }
}
