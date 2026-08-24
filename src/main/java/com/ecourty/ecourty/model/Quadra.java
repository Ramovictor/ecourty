package com.ecourty.ecourty.model;

public class Quadra {

    private Long id;
    private String nome;
    private String tipo;
    private Double valorPorHora;
    private Usuario usuario;

    public Quadra() {
    }

    public Quadra(Long id, String nome, String tipo, Double valorPorHora, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valorPorHora = valorPorHora;
        this.usuario = usuario;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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