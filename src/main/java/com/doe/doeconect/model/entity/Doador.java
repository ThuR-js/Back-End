package com.doe.doeconect.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "Doador")
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;
    @CreationTimestamp

    @Column(length = 12, nullable = false)
    private String cpf;

    @Column(length = 9, nullable = false)
    private String cep;

    //private foto de perfil

    // private usuario_id

    @Column(nullable = false)
    private LocalDate dataCadastro;
    @CreationTimestamp

    @Column(length = 20, nullable = false)
    private String statusDoador;

    public String getStatusDoador() {
        return statusDoador;
    }

    public void setStatusDoador(String statusDoador) {
        this.statusDoador = statusDoador;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataNascimento() {return dataNascimento;}

    public void setDataNascimento(LocalDate dataNascimento) {this.dataNascimento = dataNascimento;}

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
