package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Agendamento;
import com.ecourty.ecourty.model.Usuario;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoService {

    private final List<Agendamento> agendamentos = new ArrayList<>();

    private Long proximoId = 1L;

    public List<Agendamento> listarPorUsuario(Usuario usuario) {

        List<Agendamento> agendamentosDoUsuario = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {

            if (agendamento.getUsuario() != null
                    && agendamento.getUsuario().getEmail() != null
                    && agendamento.getUsuario().getEmail()
                            .equalsIgnoreCase(usuario.getEmail())) {

                agendamentosDoUsuario.add(agendamento);
            }
        }

        return agendamentosDoUsuario;
    }

    // ======================================================
    // VERIFICAR CONFLITO DE HORÁRIO
    // ======================================================

    public boolean existeConflito(
            Agendamento novoAgendamento,
            Long idIgnorar) {

        for (Agendamento existente : agendamentos) {

            // Ignora o próprio agendamento quando estiver editando
            if (idIgnorar != null
                    && existente.getId().equals(idIgnorar)) {

                continue;
            }

            // Verifica se é a mesma quadra
            if (existente.getQuadra() == null
                    || novoAgendamento.getQuadra() == null) {

                continue;
            }

            if (!existente.getQuadra().getId()
                    .equals(novoAgendamento.getQuadra().getId())) {

                continue;
            }

            // Verifica se é a mesma data
            if (existente.getData() == null
                    || novoAgendamento.getData() == null) {

                continue;
            }

            if (!existente.getData()
                    .equals(novoAgendamento.getData())) {

                continue;
            }

            // Verifica conflito de horário
            boolean conflito = novoAgendamento.getHorarioInicio()
                    .isBefore(existente.getHorarioFim())

                    &&

                    novoAgendamento.getHorarioFim()
                            .isAfter(existente.getHorarioInicio());

            if (conflito) {
                return true;
            }
        }

        return false;
    }

    // ======================================================
    // CADASTRAR
    // ======================================================

    public boolean cadastrar(
            Agendamento agendamento,
            Usuario usuario) {

        // Verifica se o horário é válido
        if (agendamento.getHorarioInicio() == null
                || agendamento.getHorarioFim() == null) {

            return false;
        }

        if (!agendamento.getHorarioInicio()
                .isBefore(agendamento.getHorarioFim())) {

            return false;
        }

        // Verifica conflito
        if (existeConflito(agendamento, null)) {

            return false;
        }

        agendamento.setId(proximoId);

        proximoId++;

        agendamento.setUsuario(usuario);

        agendamentos.add(agendamento);

        return true;
    }

    // ======================================================
    // BUSCAR POR ID
    // ======================================================

    public Agendamento buscarPorId(Long id) {

        for (Agendamento agendamento : agendamentos) {

            if (agendamento.getId().equals(id)) {
                return agendamento;
            }
        }

        return null;
    }

    // ======================================================
    // ATUALIZAR
    // ======================================================

    public boolean atualizar(
            Long id,
            Agendamento dados,
            Usuario usuario) {

        Agendamento agendamento = buscarPorId(id);

        if (agendamento == null) {
            return false;
        }

        // Verifica se pertence ao usuário
        if (agendamento.getUsuario() == null
                || !agendamento.getUsuario().getEmail()
                        .equalsIgnoreCase(usuario.getEmail())) {

            return false;
        }

        // Verifica horário
        if (dados.getHorarioInicio() == null
                || dados.getHorarioFim() == null) {

            return false;
        }

        if (!dados.getHorarioInicio()
                .isBefore(dados.getHorarioFim())) {

            return false;
        }

        // Verifica conflito ignorando o próprio agendamento
        if (existeConflito(dados, id)) {

            return false;
        }

        // Atualiza os dados
        agendamento.setData(
                dados.getData());

        agendamento.setHorarioInicio(
                dados.getHorarioInicio());

        agendamento.setHorarioFim(
                dados.getHorarioFim());

        agendamento.setValor(
                dados.getValor());

        agendamento.setStatusPagamento(
                dados.getStatusPagamento());

        agendamento.setCliente(
                dados.getCliente());

        agendamento.setQuadra(
                dados.getQuadra());

        return true;
    }

    // ======================================================
    // EXCLUIR
    // ======================================================

    public boolean excluir(
            Long id,
            Usuario usuario) {

        Agendamento agendamento = buscarPorId(id);

        if (agendamento == null) {
            return false;
        }

        if (agendamento.getUsuario() == null
                || !agendamento.getUsuario().getEmail()
                        .equalsIgnoreCase(usuario.getEmail())) {

            return false;
        }

        agendamentos.remove(agendamento);

        return true;
    }
}