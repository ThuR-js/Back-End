package com.doe.doeconect.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Entity
@Table(name = "Anuncio")
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String descricao;

    // private categoria_id

    @Column(length = 2, nullable = false)
    private String tamanho;

    //private condicao_id
    @Column(length = 20, nullable = false)
    private String condicao;

    private String caminhoFoto;
    // private foto
    // caminho da foto para armazenar
    // private doador_id

    @Column(nullable = false)
    private LocalDate dataCadastro;
    @CreationTimestamp

    @Column(length = 20, nullable = false)
    private String statusAnuncio;

    public String getCondicao() {return condicao;}

    public void setCondicao(String condicao) {this.condicao = condicao;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusAnuncio() {
        return statusAnuncio;
    }

    public void setStatusAnuncio(String statusAnuncio) {
        this.statusAnuncio = statusAnuncio;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
