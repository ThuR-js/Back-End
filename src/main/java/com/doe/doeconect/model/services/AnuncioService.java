package com.doe.doeconect.model.services;

import com.doe.doeconect.model.entity.Anuncio;
import com.doe.doeconect.model.entity.AnaliseAdministrador;
import com.doe.doeconect.model.entity.Favorito;
import com.doe.doeconect.model.repository.AnaliseAdministradorRepository;
import com.doe.doeconect.model.repository.AnuncioRepository;
import com.doe.doeconect.model.repository.FavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnuncioService {

    private static final int LIMITE_POR_CATEGORIA = 10;

    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private FavoritoRepository favoritoRepository;
    @Autowired
    private AnaliseAdministradorRepository analiseRepository;
    @Autowired
    private com.doe.doeconect.model.repository.SolicitacaoRepository solicitacaoRepository;

    public List<Anuncio> findAll() {
        return anuncioRepository.findByStatusAnuncio("ATIVO");
    }

    public List<Anuncio> findAllAdmin() {
        return anuncioRepository.findAll();
    }

    public Anuncio findById(Long id) {
        return anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio não encontrado com o id: " + id));
    }

    public List<Anuncio> findPorCategoria(Long categoriaId) {
        return anuncioRepository.findTopByCategoriaLimitado(
                categoriaId, "ATIVO", PageRequest.of(0, LIMITE_POR_CATEGORIA));
    }

    public List<Anuncio> findByDoador(Long doadorId) {
        return anuncioRepository.findByDoadorId(doadorId);
    }

    public Anuncio save(Anuncio anuncio) {
        anuncio.setStatusAnuncio("PENDENTE");
        anuncio.setDataCadastro(LocalDate.now());
        Anuncio salvo = anuncioRepository.save(anuncio);
        // Registra analise inicial
        AnaliseAdministrador analise = new AnaliseAdministrador();
        analise.setAnuncio(salvo);
        analise.setStatusAnalise("EM_ANALISE");
        analiseRepository.save(analise);
        return salvo;
    }

    public Anuncio update(Long id, Anuncio anuncio) {
        Anuncio existente = findById(id);
        if (anuncio.getNome() != null) existente.setNome(anuncio.getNome());
        if (anuncio.getDescricao() != null) existente.setDescricao(anuncio.getDescricao());
        if (anuncio.getCategoria() != null) existente.setCategoria(anuncio.getCategoria());
        if (anuncio.getTamanho() != null) existente.setTamanho(anuncio.getTamanho());
        if (anuncio.getCondicao() != null) existente.setCondicao(anuncio.getCondicao());
        if (anuncio.getFoto() != null) existente.setFoto(anuncio.getFoto());
        if (anuncio.getFotos() != null) existente.setFotos(anuncio.getFotos());
        if (anuncio.getRegiao() != null) existente.setRegiao(anuncio.getRegiao());
        if (anuncio.getStatusAnuncio() != null) {
            String statusAnterior = existente.getStatusAnuncio();
            existente.setStatusAnuncio(anuncio.getStatusAnuncio());
            // Registra analise se status mudou para ATIVO ou INATIVO
            if (!anuncio.getStatusAnuncio().equals(statusAnterior)) {
                AnaliseAdministrador analise = new AnaliseAdministrador();
                analise.setAnuncio(existente);
                if ("ATIVO".equals(anuncio.getStatusAnuncio())) {
                    analise.setStatusAnalise("APROVADO");
                } else if ("INATIVO".equals(anuncio.getStatusAnuncio())) {
                    analise.setStatusAnalise("REJEITADO");
                } else {
                    analise.setStatusAnalise("EM_ANALISE");
                }
                analiseRepository.save(analise);
            }
        }
        return anuncioRepository.save(existente);
    }

    public Anuncio inativar(Long id, Long doadorId) {
        Anuncio anuncio = findById(id);
        if (!anuncio.getDoador().getId().equals(doadorId)) {
            throw new RuntimeException("Apenas o dono pode inativar este anúncio");
        }
        anuncio.setStatusAnuncio("INATIVO");
        return anuncioRepository.save(anuncio);
    }

    public void delete(Long id) {
        Anuncio anuncio = findById(id);
        // Remove dependências em cascata
        analiseRepository.deleteAll(analiseRepository.findByAnuncioId(id));
        favoritoRepository.deleteAll(favoritoRepository.findByAnuncioId(id));
        solicitacaoRepository.deleteAll(solicitacaoRepository.findByAnuncioId(id));
        anuncioRepository.delete(anuncio);
    }

    // Favoritos
    public Favorito favoritar(Long usuarioId, Long anuncioId) {
        if (favoritoRepository.existsByUsuarioIdAndAnuncioIdAndTipo(usuarioId, anuncioId, "FAVORITO")) {
            throw new RuntimeException("Anúncio já favoritado");
        }
        Anuncio anuncio = findById(anuncioId);
        Favorito fav = new Favorito();
        fav.setAnuncio(anuncio);
        com.doe.doeconect.model.entity.Usuario usuario = new com.doe.doeconect.model.entity.Usuario();
        usuario.setId(usuarioId);
        fav.setUsuario(usuario);
        fav.setTipo("FAVORITO");
        return favoritoRepository.save(fav);
    }

    public void desfavoritar(Long usuarioId, Long anuncioId) {
        Favorito fav = favoritoRepository.findByUsuarioIdAndAnuncioIdAndTipo(usuarioId, anuncioId, "FAVORITO")
                .orElseThrow(() -> new RuntimeException("Favorito não encontrado"));
        favoritoRepository.delete(fav);
    }

    public List<Anuncio> listarFavoritos(Long usuarioId) {
        return favoritoRepository.findByUsuarioIdAndTipo(usuarioId, "FAVORITO")
                .stream().map(Favorito::getAnuncio).collect(Collectors.toList());
    }
}
