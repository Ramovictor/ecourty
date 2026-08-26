package com.ecourty.ecourty.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {

    private Long id;

    private LocalDate data;

    private LocalTime horarioInicio;

    private LocalTime horarioFim;

    private Double valor;

    private String statusPagamento;

    private Cliente cliente;

    private Quadra quadra;

    private Usuario usuario;

    public Agendamento() {
    }

    public Agendamento(
            Long id,
            LocalDate data,
            LocalTime horarioInicio,
            LocalTime horarioFim,
            Double valor,
            String statusPagamento,
            Cliente cliente,
            Quadra quadra,
            Usuario usuario) {

        this.id = id;
        this.data = data;
        this.horarioInicio = horarioInicio;
        this.horarioFim = horarioFim;
        this.valor = valor;
        this.statusPagamento = statusPagamento;
        this.cliente = cliente;
        this.quadra = quadra;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(LocalTime horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}