package com.doe.doeconect.model.repository;

import com.doe.doeconect.model.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByCategoriaIdAndStatusAnuncio(Long categoriaId, String statusAnuncio);
    List<Anuncio> findByDoadorId(Long doadorId);
    List<Anuncio> findByStatusAnuncio(String statusAnuncio);

    @Query("SELECT a FROM Anuncio a WHERE a.categoria.id = :categoriaId AND a.statusAnuncio = :status ORDER BY a.dataCadastro DESC")
    List<Anuncio> findTopByCategoriaLimitado(Long categoriaId, String status, org.springframework.data.domain.Pageable pageable);
}
