package com.doe.doeconect.model.repository;

import com.doe.doeconect.model.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioIdAndTipo(Long usuarioId, String tipo);
    List<Favorito> findByAnuncioId(Long anuncioId);
    Optional<Favorito> findByUsuarioIdAndAnuncioIdAndTipo(Long usuarioId, Long anuncioId, String tipo);
    boolean existsByUsuarioIdAndAnuncioIdAndTipo(Long usuarioId, Long anuncioId, String tipo);
}
