package com.doe.doeconect.model.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

@Entity
@Table(name = "ANALISE_ADMINISTRADOR")
public class AnaliseAdministrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "anuncio_id", nullable = false)
    private Anuncio anuncio;

    @Column(name = "data_cadastro", nullable = false)
    @CreationTimestamp
    private LocalDate dataCadastro;

    @Column(name = "status_analise", length = 20, nullable = false)
    private String statusAnalise; // EM_ANALISE, APROVADO, REJEITADO

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Anuncio getAnuncio() { return anuncio; }
    public void setAnuncio(Anuncio anuncio) { this.anuncio = anuncio; }
    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
    public String getStatusAnalise() { return statusAnalise; }
    public void setStatusAnalise(String statusAnalise) { this.statusAnalise = statusAnalise; }
}
