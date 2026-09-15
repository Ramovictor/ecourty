package com.ecourty.ecourty.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quadra")
public class Quadra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipoquadra_id", nullable = false)
    private TipoQuadra tipoQuadra;

    @Column(name = "valor_hora")
    private Double valorPorHora;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Quadra() {
    }

    public Quadra(String nome, TipoQuadra tipoQuadra, Double valorPorHora) {
        this.nome = nome;
        this.tipoQuadra = tipoQuadra;
        this.valorPorHora = valorPorHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoQuadra getTipoQuadra() {
        return tipoQuadra;
    }

    public void setTipoQuadra(TipoQuadra tipoQuadra) {
        this.tipoQuadra = tipoQuadra;
    }

    public Double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(Double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}