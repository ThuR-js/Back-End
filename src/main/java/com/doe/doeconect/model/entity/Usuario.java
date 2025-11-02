package com.doe.doeconect.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id  // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)  // length: tamanho MAX, nullble: false (NOT NULL) true: (NULL)
    private String nome;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String senha;

    @Column(length = 20, nullable = true)
    private String nivelAcesso;

    //private foto de perfil
    // @Column(length = 200, nullable = true)
    //  private String caminhoFoto;

    @Column(name = "data_cadastro",nullable = false)
    @CreationTimestamp
    private LocalDateTime dataCadastro;


    @Column(name = "status_usuario", length = 20, nullable = false)
    private String statusUsuario;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    // public String getCaminhoFoto() {
    //  return caminhoFoto;
    //}
    // public void setCaminhoFoto(String caminhoFoto) {
    //   this.caminhoFoto = caminhoFoto;
    // }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getStatusUsuario() {
        return statusUsuario;
    }

    public void setStatusUsuario(String statusUsuario) {
        this.statusUsuario = statusUsuario;
    }
}
