package com.doe.doeconect.model.services;

import com.doe.doeconect.model.entity.Doador;
import com.doe.doeconect.model.entity.Usuario;
import com.doe.doeconect.model.repository.DoadorRepository;
import com.doe.doeconect.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Doador> findAll() {
        return doadorRepository.findAll();
    }

    public Doador findById(Long id) {
        return doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado com o id: " + id));
    }

    public Doador save(Doador doador) {
        doador.setStatusDoador("ATIVO");
        doador.setDataCadastro(LocalDate.now());
        return doadorRepository.save(doador);
    }

    public Doador update(Long id, Doador doador) {
        Doador existente = findById(id);
        if (doador.getNome() != null) {
            existente.setNome(doador.getNome());
            Usuario usuario = existente.getUsuario();
            usuario.setNome(doador.getNome());
            usuarioRepository.save(usuario);
        }
        if (doador.getCpf() != null) existente.setCpf(doador.getCpf());
        if (doador.getCep() != null) existente.setCep(doador.getCep());
        if (doador.getDataNascimento() != null) existente.setDataNascimento(doador.getDataNascimento());
        if (doador.getFoto() != null) existente.setFoto(doador.getFoto());
        return doadorRepository.save(existente);
    }

    public Doador findByUsuarioId(Long usuarioId) {
        return doadorRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado para o usuário: " + usuarioId));
    }

    public void delete(Long id) {
        doadorRepository.delete(findById(id));
    }
}
