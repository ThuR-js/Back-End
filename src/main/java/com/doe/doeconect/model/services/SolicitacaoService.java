package com.doe.doeconect.model.services;

import com.doe.doeconect.model.entity.Solicitacao;
import com.doe.doeconect.model.repository.SolicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    public List<Solicitacao> findAll() {
        return solicitacaoRepository.findAll();
    }

    public Solicitacao findById(Long id) {
        return solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada com o id: " + id));
    }

    public List<Solicitacao> findByUsuarioId(Long usuarioId) {
        return solicitacaoRepository.findByUsuarioId(usuarioId);
    }

    public List<Solicitacao> findByAnuncioId(Long anuncioId) {
        return solicitacaoRepository.findByAnuncioId(anuncioId);
    }

    public Solicitacao save(Solicitacao solicitacao) {
        solicitacao.setStatusSolicitacao("EM_ANALISE");
        return solicitacaoRepository.save(solicitacao);
    }

    public Solicitacao updateStatus(Long id, String status) {
        Solicitacao s = findById(id);
        s.setStatusSolicitacao(status);
        return solicitacaoRepository.save(s);
    }

    public void delete(Long id) {
        solicitacaoRepository.delete(findById(id));
    }
}
