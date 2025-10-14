package com.doe.doeconect.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Condicao")
public class Condicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 20, nullable = false)
    private String statusCondicao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusCondicao() {
        return statusCondicao;
    }

    public void setStatusCondicao(String statusCondicao) {
        this.statusCondicao = statusCondicao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
