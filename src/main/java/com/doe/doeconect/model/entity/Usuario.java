package com.doe.doeconect.model.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    @JsonAlias("email")
    private String username;

    @Column(length = 255, nullable = false)
    @JsonAlias("senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name="nivel_acesso", length = 20, nullable = true)
    private String nivelAcesso;

    @Column(length = 500, nullable = true)
    private String foto;

    @Column(length = 100, nullable = true)
    private String regiao;


    @Column(name = "data_cadastro", nullable = false)
    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @Column(name = "status_usuario", length = 20, nullable = false)
    private String statusUsuario;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNivelAcesso() { return nivelAcesso; }
    public void setNivelAcesso(String nivelAcesso) { this.nivelAcesso = nivelAcesso; }
    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public String getStatusUsuario() { return statusUsuario; }
    public void setStatusUsuario(String statusUsuario) { this.statusUsuario = statusUsuario; }
}
