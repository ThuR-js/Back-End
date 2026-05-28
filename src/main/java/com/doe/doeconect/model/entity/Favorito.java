package com.doe.doeconect.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

@Entity
@Table(name = "Usuario_Anuncio")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "anuncio_id", nullable = false)
    private Anuncio anuncio;

    @Column(length = 20, nullable = false)
    private String tipo;

    @Column(name = "data_cadastro", nullable = false)
    @CreationTimestamp
    private LocalDate dataCadastro;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Anuncio getAnuncio() { return anuncio; }
    public void setAnuncio(Anuncio anuncio) { this.anuncio = anuncio; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
}
