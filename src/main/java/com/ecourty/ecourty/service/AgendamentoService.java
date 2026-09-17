
package com.ecourty.ecourty.service;

import com.ecourty.ecourty.model.Agendamento;
import com.ecourty.ecourty.model.Usuario;
import com.ecourty.ecourty.repository.AgendamentoRepository;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(
            AgendamentoRepository agendamentoRepository) {

        this.agendamentoRepository = agendamentoRepository;
    }

    // ======================================================
    // LISTAR AGENDAMENTOS DO USUÁRIO
    // ======================================================

    public List<Agendamento> listarPorUsuario(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            return new ArrayList<>();
        }

        return agendamentoRepository
                .findByQuadraUsuarioId(usuario.getId());
    }

    // ======================================================
    // VERIFICAR CONFLITO DE HORÁRIO
    // ======================================================

    public boolean existeConflito(
            Agendamento novoAgendamento,
            Long idIgnorar) {

        if (novoAgendamento.getQuadra() == null
                || novoAgendamento.getQuadra().getId() == null
                || novoAgendamento.getData() == null) {

            return false;
        }

        List<Agendamento> agendamentosDoDia = agendamentoRepository.findByQuadraIdAndData(
                novoAgendamento.getQuadra().getId(),
                novoAgendamento.getData());

        for (Agendamento existente : agendamentosDoDia) {

            // Ignora o próprio agendamento quando estiver editando
            if (idIgnorar != null
                    && existente.getId().equals(idIgnorar)) {

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

        // Verifica data
        if (agendamento.getData() == null) {
            return false;
        }

        // Verifica horário
        if (agendamento.getHorarioInicio() == null
                || agendamento.getHorarioFim() == null) {

            return false;
        }

        // Verifica se horário inicial é antes do final
        if (!agendamento.getHorarioInicio()
                .isBefore(agendamento.getHorarioFim())) {

            return false;
        }

        // Verifica se cliente e quadra existem
        if (agendamento.getCliente() == null
                || agendamento.getQuadra() == null) {

            return false;
        }

        // Verifica conflito
        if (existeConflito(agendamento, null)) {
            return false;
        }

        // Salva no MySQL
        agendamentoRepository.save(agendamento);

        return true;
    }

    // ======================================================
    // BUSCAR POR ID
    // ======================================================

    public Agendamento buscarPorId(Long id) {

        return agendamentoRepository
                .findById(id)
                .orElse(null);
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

        // Verifica se a quadra pertence ao usuário
        if (agendamento.getQuadra() == null
                || agendamento.getQuadra().getUsuario() == null
                || !agendamento.getQuadra()
                        .getUsuario()
                        .getEmail()
                        .equalsIgnoreCase(usuario.getEmail())) {

            return false;
        }

        // Verifica data
        if (dados.getData() == null) {
            return false;
        }

        // Verifica horário
        if (dados.getHorarioInicio() == null
                || dados.getHorarioFim() == null) {

            return false;
        }

        // Verifica horário inicial antes do final
        if (!dados.getHorarioInicio()
                .isBefore(dados.getHorarioFim())) {

            return false;
        }

        // Verifica conflito
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

        agendamentoRepository.save(agendamento);

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

        // Verifica se pertence ao usuário
        if (agendamento.getQuadra() == null
                || agendamento.getQuadra().getUsuario() == null
                || !agendamento.getQuadra()
                        .getUsuario()
                        .getEmail()
                        .equalsIgnoreCase(usuario.getEmail())) {

            return false;
        }

        agendamentoRepository.delete(agendamento);

        return true;
    }

    public List<Agendamento> listarHistorico(Usuario usuario) {

        if (usuario == null || usuario.getId() == null) {
            return new ArrayList<>();
        }

        return agendamentoRepository
                .findByQuadraUsuarioIdAndDataBefore(
                        usuario.getId(),
                        LocalDate.now());
    }
}
