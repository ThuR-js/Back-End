package com.doe.doeconect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

@Entity
@Table(name = "Solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_cadastro", nullable = false)
    @CreationTimestamp
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties({"password", "hibernateLazyInitializer", "handler"})
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "anuncio_id", nullable = false)
    @JsonIgnoreProperties({"doador", "hibernateLazyInitializer", "handler"})
    private Anuncio anuncio;

    @Column(length = 20, nullable = true)
    private String telefone;

    @Column(name = "status_solicitacao", length = 20, nullable = false)
    private String statusSolicitacao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Anuncio getAnuncio() { return anuncio; }
    public void setAnuncio(Anuncio anuncio) { this.anuncio = anuncio; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getStatusSolicitacao() { return statusSolicitacao; }
    public void setStatusSolicitacao(String statusSolicitacao) { this.statusSolicitacao = statusSolicitacao; }
}
