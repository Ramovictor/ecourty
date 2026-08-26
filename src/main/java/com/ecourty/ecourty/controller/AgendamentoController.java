package com.ecourty.ecourty.controller;

import com.ecourty.ecourty.model.Agendamento;
import com.ecourty.ecourty.model.Cliente;
import com.ecourty.ecourty.model.Quadra;
import com.ecourty.ecourty.model.Usuario;

import com.ecourty.ecourty.service.AgendamentoService;
import com.ecourty.ecourty.service.ClienteService;
import com.ecourty.ecourty.service.QuadraService;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;
    private final QuadraService quadraService;

    public AgendamentoController(
            AgendamentoService agendamentoService,
            ClienteService clienteService,
            QuadraService quadraService) {

        this.agendamentoService = agendamentoService;
        this.clienteService = clienteService;
        this.quadraService = quadraService;
    }

    @GetMapping("/agendamentos")
    public String listarAgendamentos(
            HttpSession session,
            Model model) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        List<Agendamento> agendamentos = agendamentoService.listarPorUsuario(usuario);

        List<Cliente> clientes = clienteService.listarPorUsuario(usuario);

        List<Quadra> quadras = quadraService.listarPorUsuario(usuario);

        model.addAttribute("agendamentos", agendamentos);
        model.addAttribute("clientes", clientes);
        model.addAttribute("quadras", quadras);
        model.addAttribute("usuario", usuario);

        return "agendamentos";
    }

    @PostMapping("/agendamentos/cadastro")
    public String cadastrar(
            Agendamento agendamento,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        Cliente cliente = clienteService.buscarPorId(
                agendamento.getCliente().getId());

        Quadra quadra = quadraService.buscarPorId(
                agendamento.getQuadra().getId());

        if (cliente == null || quadra == null) {
            return "redirect:/agendamentos";
        }

        agendamento.setCliente(cliente);
        agendamento.setQuadra(quadra);

        agendamentoService.cadastrar(
                agendamento,
                usuario);

        return "redirect:/agendamentos";
    }

    @PostMapping("/agendamentos/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Agendamento agendamento,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        Cliente cliente = clienteService.buscarPorId(
                agendamento.getCliente().getId());

        Quadra quadra = quadraService.buscarPorId(
                agendamento.getQuadra().getId());

        if (cliente == null || quadra == null) {
            return "redirect:/agendamentos";
        }

        agendamento.setCliente(cliente);
        agendamento.setQuadra(quadra);

        agendamentoService.atualizar(
                id,
                agendamento,
                usuario);

        return "redirect:/agendamentos";
    }

    @GetMapping("/agendamentos/excluir/{id}")
    public String excluir(
            @PathVariable Long id,
            HttpSession session) {

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            return "redirect:/";
        }

        agendamentoService.excluir(
                id,
                usuario);

        return "redirect:/agendamentos";
    }
}