package com.doe.doeconect.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Entity
@Table(name = "Soliticao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataCadastro;
    @CreationTimestamp
    // usuario_id
    // anuncio_id
    @Column(length = 20, nullable = false)
    private String statusSoliitacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusSoliitacao() {
        return statusSoliitacao;
    }

    public void setStatusSoliitacao(String statusSoliitacao) {
        this.statusSoliitacao = statusSoliitacao;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
