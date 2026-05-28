package com.doe.doeconect.model.repository;

import com.doe.doeconect.model.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    List<Solicitacao> findByUsuarioId(Long usuarioId);
    List<Solicitacao> findByAnuncioId(Long anuncioId);
}
